package nog.com.br.appfidelidade.entidade;

import java.util.Date;

/**
 * Created by andersonnogueira on 09/05/16.
 */
public class Pessoa {

    private int idPessoa;

    private String nome;

    private String endereco;

    private String cpfCpnj;

    private TipoPessoa tipoPessoa;

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpfCpnj() {
        return cpfCpnj;
    }

    public void setCpfCpnj(String cpfCpnj) {
        this.cpfCpnj = cpfCpnj;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpfCpnj='" + cpfCpnj + '\'' +
                ", tipoPessoa=" + tipoPessoa +
                '}';
    }
}


