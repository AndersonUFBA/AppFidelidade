package nog.com.br.appfidelidade.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import nog.com.br.appfidelidade.entidade.Pontuacao;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class PontuacaoDAO {
    private static final String URL = "http://192.168.1.100:8080/FidelidadeWS/services/UsuarioDAO?wsdl";
    private static final String NAMESPACE = "http://dao.fidelidadews.com.br";

    private static final String INSERIR = "inserirUsuario";
    private static final String EXCLUIR = "excluirUsuario";
    private static final String ATUALIZAR = "atualizarUsuario";
    private static final String BUSCAR_TODOS = "buscarTodosUsuarios";
    private static final String BUSCAR_POR_ID = "buscarUsuarioPorId";

    public boolean inserirPontuacao(Pontuacao pontuacao){

        SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"usuario");
        usuarioSoap.addProperty("id", pontuacao.getId());
        usuarioSoap.addProperty("usuario_cpf", pontuacao.getUsuario_cpf());
        usuarioSoap.addProperty("empresa_cnpj", pontuacao.getEmpresa_cnpj());

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

    public ArrayList<Pontuacao> buscarTodosPontuacao(){

        ArrayList<Pontuacao> lista = new ArrayList<Pontuacao>();

        SoapObject buscarPontuacao = new SoapObject(NAMESPACE, BUSCAR_TODOS);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarPontuacao);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_TODOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

            for (SoapObject soapObject : resposta ) {
                Pontuacao usuarioSoap = new Pontuacao();

                usuarioSoap.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                usuarioSoap.setEmpresa_cnpj(soapObject.getProperty("empresa_cnpj").toString());
                usuarioSoap.setUsuario_cpf(soapObject.getProperty("usuario_cnpj").toString());

                lista.add(usuarioSoap);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }
}
