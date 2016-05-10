package nog.com.br.appfidelidade.entidade;

/**
 * Created by andersonnogueira on 10/05/16.
 */
public class Pontuacao {
    private int idPontuacao;
    private Pessoa cliente;
    private Pessoa Empresa;
    private double pontuacao;


    public int getIdPontuacao() {
        return idPontuacao;
    }

    public void setIdPontuacao(int idPontuacao) {
        this.idPontuacao = idPontuacao;
    }
    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Pessoa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Pessoa empresa) {
        Empresa = empresa;
    }
}
