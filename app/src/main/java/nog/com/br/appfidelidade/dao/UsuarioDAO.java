package nog.com.br.appfidelidade.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import nog.com.br.appfidelidade.entidade.Usuario;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class UsuarioDAO {
    private static final String URL = "http://192.168.1.100:8080/FidelidadeWS/services/UsuarioDAO?wsdl";
    private static final String NAMESPACE = "http://dao.fidelidadews.com.br";

    private static final String INSERIR = "inserirUsuario";
    private static final String EXCLUIR = "excluirUsuario";
    private static final String ATUALIZAR = "atualizarUsuario";
    private static final String BUSCAR_TODOS = "buscarTodosUsuarios";
    private static final String BUSCAR_POR_ID = "buscarUsuarioPorId";
    private static final String USUARIO_LOGAR = "usuarioLogar";

    public boolean inserirUsuario(Usuario usuario){

        SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"usuario");
        usuarioSoap.addProperty("cpf", usuario.getCpf());
        usuarioSoap.addProperty("senha", usuario.getSenha());
        usuarioSoap.addProperty("nome", usuario.getNome());

        inserirUsuario.addSoapObject(usuarioSoap);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(inserirUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + INSERIR, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Usuario> buscarTodosUsuarios(){

        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_TODOS);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_TODOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

            for (SoapObject soapObject : resposta ) {
                Usuario usuarioSoap = new Usuario();

                usuarioSoap.setCpf(soapObject.getProperty("cpf").toString());
                usuarioSoap.setNome(soapObject.getProperty("nome").toString());
                usuarioSoap.setSenha(soapObject.getProperty("senha").toString());

                lista.add(usuarioSoap);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }

    boolean UsuarioLogar(String cpf, String senha ){
        Usuario usr = null;

        SoapObject buscarUsuario = new SoapObject(NAMESPACE, USUARIO_LOGAR);
        buscarUsuario.addProperty("cpf", cpf);
        buscarUsuario.addProperty("senha", senha);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + USUARIO_LOGAR, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();

            usr = new Usuario();

            usr.setCpf(resposta.getProperty("id").toString());
            usr.setNome(resposta.getProperty("nome").toString());
            usr.setSenha(resposta.getProperty("senha").toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}

