package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import android.os.AsyncTask;
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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final String response = new HttpConnection().requestGET("http://192.168.1.16:3000/users/show/123", "");
        System.out.println(response);
        System.out.println("eu");


        loginBO = new LoginBO(this);
        getSupportActionBar().hide();
        rbgModo = (RadioGroup) findViewById(R.id.rbgModo);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        String login = preferences.getString("login", null);
        String senha = preferences.getString("senha", null);
        int modo = preferences.getInt("modo", 0);


        //caso exista login e senha salvos é autorizado a passar para proxima activity
        if (login != null && senha != null && modo == 0) {
            Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
            startActivity(i);
            finish();
        }
            else if (modo == 1) {
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
                if (isValido && modo == 0) {
                    Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
                    startActivity(i);
                    finish(); //mata a activity atual
                } else if (isValido && modo == 1) {
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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://nog.com.br.appfidelidade/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://nog.com.br.appfidelidade/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
