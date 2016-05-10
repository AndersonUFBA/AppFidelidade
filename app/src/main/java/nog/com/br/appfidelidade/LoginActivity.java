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

import nog.com.br.appfidelidade.bo.LoginBO;
import nog.com.br.appfidelidade.entidade.Modo;
import nog.com.br.appfidelidade.validation.LoginValidation;

public class LoginActivity extends AppCompatActivity {


    private EditText edtLogin;
    private EditText edtSenha;


    private Button btnLogar, btnNova;
    private RadioGroup rbgModo;
    private SharedPreferences preferences;

    private LoginBO loginBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBO = new LoginBO(this);
        getSupportActionBar().hide();
        rbgModo = (RadioGroup) findViewById(R.id.rbgModo);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha", null);
        int modo = preferences.getInt("modo", 0);


        //caso exista login e senha salvos é altorizado a passar para proxima activity
        if (login != null && senha != null && modo == 0){
            Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
            startActivity(i);
            finish();
        }else if (login != null && senha != null && modo == 1){
            Intent i = new Intent(LoginActivity.this, EmpresaActivity.class);
            startActivity(i);
            finish();
        }

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);


        btnLogar = (Button) findViewById(R.id.btnLogar);


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                int modo = 0;

                switch (rbgModo.getCheckedRadioButtonId()) {
                    case R.id.rbtCliente:
                      modo = 0;
                        break;
                    case R.id.rbtEmpresa:
                        modo = 1;
                        break;
                }



                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivity.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtSenha);
                validation.setRbtmodo(rbgModo);
                validation.setLogin(login);
                validation.setSenha(senha);
                validation.setModo(rbgModo.getCheckedRadioButtonId());



                boolean isValido = loginBO.validarCamposLogin(validation);
                if (isValido && modo == 0){
                    Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
                    startActivity(i);
                    finish(); //mata a activity atual
                }else if (isValido && modo == 1){
                    Intent i = new Intent(LoginActivity.this, EmpresaActivity.class);
                    startActivity(i);
                    finish(); //mata a activity atual
                }
            }
        });

        btnNova = (Button) findViewById(R.id.btnNova);
        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, PessoaActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

}
