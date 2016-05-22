package nog.com.br.appfidelidade.entidade;

/**
 * Created by andersonnogueira on 22/05/16.
 */
public class Empresa {

    private String cnpj;
    private String senha;
    private String descricao;
    private String latitude;
    private String longitude;

    public Empresa(){
    }

    public Empresa(String cnpj, String senha, String descricao, String latitude, String longitude) {
        this.cnpj = cnpj;
        this.senha = senha;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj='" + cnpj + '\'' +
                ", senha='" + senha + '\'' +
                ", descricao='" + descricao + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
