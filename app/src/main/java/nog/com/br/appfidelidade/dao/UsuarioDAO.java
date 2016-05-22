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

    /*public boolean atualizarUsuario(Usuario usuario){
        SoapObject atualizarUsuario = new SoapObject(NAMESPACE, ATUALIZAR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"usuario");
        usuarioSoap.addProperty("id", usuario.getId());
        usuarioSoap.addProperty("idade", usuario.getIdade());
        usuarioSoap.addProperty("nome", usuario.getNome());

        atualizarUsuario.addSoapObject(usuarioSoap);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(atualizarUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + ATUALIZAR, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean excluirUsuario(Usuario usuario){
        SoapObject inserirUsuario = new SoapObject(NAMESPACE, EXCLUIR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"usuario");
        usuarioSoap.addProperty("id", usuario.getId());
        usuarioSoap.addProperty("idade", usuario.getIdade());
        usuarioSoap.addProperty("nome", usuario.getNome());

        inserirUsuario.addSoapObject(usuarioSoap);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(inserirUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + EXCLUIR, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean excluirUsuario(int id){

        return excluirUsuario(new Usuario(id,"", 0));
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

                usuarioSoap.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                usuarioSoap.setNome(soapObject.getProperty("nome").toString());
                usuarioSoap.setIdade(Integer.parseInt(soapObject.getProperty("idade").toString()));

                lista.add(usuarioSoap);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }

    public Usuario buscarUsuarioPorId(int id){
        Usuario usr = null;

        SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
        buscarUsuario.addProperty("id", id);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarUsuario);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_POR_ID, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();

            usr = new Usuario();

            usr.setId(Integer.parseInt(resposta.getProperty("id").toString()));
            usr.setNome(resposta.getProperty("nome").toString());
            usr.setIdade(Integer.parseInt(resposta.getProperty("idade").toString()));





        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return usr;
    }*/


}

