package nog.com.br.appfidelidade.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import nog.com.br.appfidelidade.entidade.Empresa;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class EmpresaDAO {
    private static final String URL = "http://192.168.1.100:8080/FidelidadeWS/services/EmpresaDAO?wsdl";
    private static final String NAMESPACE = "http://dao.fidelidadews.com.br";

    private static final String INSERIR = "inserirEmpresa";
    private static final String EXCLUIR = "excluirEmpresa";
    private static final String ATUALIZAR = "atualizarEmpresa";
    private static final String BUSCAR_TODOS = "buscarTodosEmpresas";
    private static final String BUSCAR_POR_ID = "buscarEmpresaPorId";

    public boolean inserirEmpresa(Empresa empresa){

        SoapObject inserirEmpresa = new SoapObject(NAMESPACE, INSERIR);

        SoapObject usuarioSoap = new SoapObject(NAMESPACE,"empresa");
        usuarioSoap.addProperty("cnpj", empresa.getCnpj());
        usuarioSoap.addProperty("senha", empresa.getSenha());
        usuarioSoap.addProperty("descricao", empresa.getDescricao());
        usuarioSoap.addProperty("latitude", empresa.getLatitude());
        usuarioSoap.addProperty("longitude", empresa.getLongitude());


        inserirEmpresa.addSoapObject(usuarioSoap);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(inserirEmpresa);

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

    public ArrayList<Empresa> buscarTodosEmpresas(){

        ArrayList<Empresa> lista = new ArrayList<Empresa>();

        SoapObject buscarEmpresa = new SoapObject(NAMESPACE, BUSCAR_TODOS);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(buscarEmpresa);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_TODOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

            for (SoapObject soapObject : resposta ) {
                Empresa empSoap = new Empresa();

                empSoap.setCnpj(soapObject.getProperty("cnpj").toString());
                empSoap.setDescricao(soapObject.getProperty("descricao").toString());
                empSoap.setSenha(soapObject.getProperty("senha").toString());
                empSoap.setLatitude(Double.parseDouble(soapObject.getProperty("latitude").toString()));
                empSoap.setLongitude(Double.parseDouble(soapObject.getProperty("latitude").toString()));

                lista.add(empSoap);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }
}
