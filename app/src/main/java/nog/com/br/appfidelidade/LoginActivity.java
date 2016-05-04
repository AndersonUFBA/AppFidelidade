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

    private Spinner spnModo;
    private EditText edtLogin;
    private EditText edtSenha;


    private Button btnLogar;
    private SharedPreferences preferences;

    private LoginBO loginBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBO = new LoginBO(this);

        getSupportActionBar().hide();

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        //capturando o login e senha salvos no dispositivo
        //spnModo = (Spinner) findViewById(R.id.spnModo);
        //final String modo = spnModo.toString();
        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha", null);

        //caso exista login e senha salvos é altorizado a passar para proxima activity
        if (login != null && senha != null){
            Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
            startActivity(i);
            finish();
        }

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);


        btnLogar = (Button) findViewById(R.id.btnLogar);

       /* btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modo == "Clinete){
                    Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
                    startActivity(i);
                }else {

                    Intent i = new Intent(LoginActivity.this, EmpresaActivity.class);
                    startActivity(i);
                }
            }
        });*/

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



                boolean isValido = loginBO.validarCamposLogin(validation);
                if (isValido){
                    Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
                    startActivity(i);
                    finish(); //mata a activity atual
                }
            }
        });
       // this.initModo();
    }
   /* private void initModo(){

        ArrayList<String> modos = new ArrayList<>();

        for (Modo p : Modo.values()){
            modos.add(p.getDescricao());
        }

        ArrayAdapter adapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, modos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnModo.setAdapter(adapter);
    }*/
}
