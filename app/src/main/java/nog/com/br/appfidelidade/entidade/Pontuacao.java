package nog.com.br.appfidelidade.entidade;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class Pontuacao {
    private int id;
    private double pontos;
    private String usuario_cpf;
    private String empresa_cnpj;

    public Pontuacao(){
    }

    public Pontuacao(int id, double pontos, String usuario_cpf, String empresa_cnpj) {
        this.id = id;
        this.pontos = pontos;
        this.usuario_cpf = usuario_cpf;
        this.empresa_cnpj = empresa_cnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public String getUsuario_cpf() {
        return usuario_cpf;
    }

    public void setUsuario_cpf(String usuario_cpf) {
        this.usuario_cpf = usuario_cpf;
    }

    public String getEmpresa_cnpj() {
        return empresa_cnpj;
    }

    public void setEmpresa_cnpj(String empresa_cnpj) {
        this.empresa_cnpj = empresa_cnpj;
    }

    @Override
    public String toString() {
        return "Pontuacao{" +
                "id=" + id +
                ", pontos=" + pontos +
                ", usuario_cpf='" + usuario_cpf + '\'' +
                ", empresa_cnpj='" + empresa_cnpj + '\'' +
                '}';
    }
}
