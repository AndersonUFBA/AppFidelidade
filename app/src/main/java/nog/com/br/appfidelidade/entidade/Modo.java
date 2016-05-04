package nog.com.br.appfidelidade.entidade;

/**
 * Created by andersonnogueira on 04/05/16.
 */
public enum Modo {
    CLIENTE("Cliente"), EMPRESA("Empresa");


    private Modo(String descricao){
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
