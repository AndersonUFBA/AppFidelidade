package nog.com.br.appfidelidade.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import nog.com.br.appfidelidade.entidade.Pontuacao;
import nog.com.br.appfidelidade.entidade.PontuacaoNew;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class PontuacaoDAO {
    private static final String URL = "http://192.168.1.100:8080/FidelidadeWS/services/PontuacaoDAO";
    private static final String NAMESPACE = "http://dao.fidelidadews.com.br";

    private static final String INSERIR = "inserirPontuacao";
    private static final String EXCLUIR = "excluirPontuacao";
    private static final String ATUALIZAR = "atualizarPontuacao";
    private static final String BUSCAR_TODOS = "buscarTodosPontuacao";
    private static final String BUSCAR_POR_ID = "buscarPontuacaoPorId";
    private static final String BUSCAR_PONTUACAO_USUARIO = "buscarPontuacaoUsuario";


    public boolean inserirPontuacao(Pontuacao pontuacao){

        SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"pontuacao");
        usuarioSoap.addProperty("id", pontuacao.getId());
        usuarioSoap.addProperty("pontos", pontuacao.getPontos());
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

    public ArrayList<PontuacaoNew> buscarPontuacaoUsuario(String cpf){

        ArrayList<PontuacaoNew> lista = new ArrayList<PontuacaoNew>();

        SoapObject buscarPontuacao = new SoapObject(NAMESPACE, BUSCAR_PONTUACAO_USUARIO);
        buscarPontuacao.addProperty("cpf", cpf);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarPontuacao);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_PONTUACAO_USUARIO, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

            for (SoapObject soapObject : resposta ) {
                PontuacaoNew usuarioSoap = new PontuacaoNew();

                usuarioSoap.setDescricao(soapObject.getProperty("descricao").toString());
                usuarioSoap.setPontos(Double.parseDouble(soapObject.getProperty("pontos").toString()));
                usuarioSoap.setId(0);
                usuarioSoap.setUsuario_cpf(cpf);


                lista.add(usuarioSoap);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }

    public boolean atualizarPontuacao(Pontuacao pontuacao){
        SoapObject atualizarPontuacao = new SoapObject(NAMESPACE, ATUALIZAR);

        SoapObject pontuacaoSoap = new SoapObject(NAMESPACE,"pontuacao");
        pontuacaoSoap.addProperty("id", pontuacao.getId());
        pontuacaoSoap.addProperty("pontos", pontuacao.getPontos());
        pontuacaoSoap.addProperty("usuario_cpf", pontuacao.getUsuario_cpf());
        pontuacaoSoap.addProperty("empresa_cnpj", pontuacao.getEmpresa_cnpj());

        atualizarPontuacao.addSoapObject(pontuacaoSoap);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(atualizarPontuacao);

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
}
