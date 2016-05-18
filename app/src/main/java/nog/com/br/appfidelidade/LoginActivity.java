package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import nog.com.br.appfidelidade.async.AsyncUsuario;
import nog.com.br.appfidelidade.bo.LoginBO;
import nog.com.br.appfidelidade.entidade.Modo;
import nog.com.br.appfidelidade.service.LoginService;
import nog.com.br.appfidelidade.validation.LoginValidation;

public class LoginActivity extends AppCompatActivity {


    private EditText edtLogin;
    private EditText edtSenha;


    private Button btnLogar, btnNova;
    private RadioGroup rbgModo;
    private SharedPreferences preferences;

    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginService = new LoginService();
        getSupportActionBar().hide();
        rbgModo = (RadioGroup) findViewById(R.id.rbgModo);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha", null);
        int modo = preferences.getInt("modo", 0);



        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);


        btnLogar = (Button) findViewById(R.id.btnLogar);


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivity.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtSenha);
                validation.setLogin(login);
                validation.setSenha(senha);

                loginService.validarCamposLogin(validation);
            }
        });

        btnNova = (Button) findViewById(R.id.btnNova);
        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, PessoaActivity.class);
                startActivity(i);
            }
        });


    }

}
