package nog.com.br.appfidelidade.validation;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by andersonnogueira on 03/05/16.
 */
public class LoginValidation {
    private String login;
    private String senha;
    private String modo;

    private EditText edtLogin;

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public RadioButton getRbtmodo() {
        return rbtmodo;
    }

    public void setRbtmodo(RadioButton rbtmodo) {
        this.rbtmodo = rbtmodo;
    }

    private EditText edtSenha;
    private RadioButton rbtmodo;

    private Activity activity;

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
