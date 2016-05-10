package nog.com.br.appfidelidade.validation;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by andersonnogueira on 03/05/16.
 */
public class LoginValidation {
    private String login;
    private String senha;
    private int modo;
    private EditText edtSenha;
    private RadioGroup rbtmodo;

    private Activity activity;

    private EditText edtLogin;

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public RadioGroup getRbtmodo() {
        return rbtmodo;
    }

    public void setRbtmodo(RadioGroup rbtmodo) {
        this.rbtmodo = rbtmodo;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EditText getEdtLogin() {
        return edtLogin;
    }

    public void setEdtLogin(EditText edtLogin) {
        this.edtLogin = edtLogin;
    }

    public EditText getEdtSenha() {
        return edtSenha;
    }

    public void setEdtSenha(EditText edtSenha) {
        this.edtSenha = edtSenha;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
