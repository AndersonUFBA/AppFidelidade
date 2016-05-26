package nog.com.br.appfidelidade;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import nog.com.br.appfidelidade.async.AsyncUsuario;
import nog.com.br.appfidelidade.bo.LoginBO;
import nog.com.br.appfidelidade.entidade.Modo;
import nog.com.br.appfidelidade.service.LoginService;
import nog.com.br.appfidelidade.util.Mask;
import nog.com.br.appfidelidade.validation.LoginValidation;

public class LoginActivity extends AppCompatActivity {


    private EditText edtLogin;
    private EditText edtSenha;
    private TextWatcher cpfMask, cnpjMask;
    private Button btnLogar, btnNova;
    private RadioGroup rbgModo;
    private RadioButton rbtCliente;
    private SharedPreferences preferences;
    private int modoSelecionado;
    private ImageView imageView;

    private LoginBO loginBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rbgModo = (RadioGroup) findViewById(R.id.rbgModo);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        imageView = (ImageView) findViewById(R.id.imageView);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        cpfMask = Mask.insert("###.###.###-##", edtLogin);
        edtLogin.addTextChangedListener(cpfMask);

        cnpjMask = Mask.insert("##.###.###/####-##", edtLogin);

        rbgModo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                edtLogin.setText("");
                edtLogin.requestFocus();
                modoSelecionado = group.getCheckedRadioButtonId();

                if (modoSelecionado == rbtCliente.getId()){
                    edtLogin.removeTextChangedListener(cnpjMask);
                    edtLogin.addTextChangedListener(cpfMask);
                }else {
                    edtLogin.removeTextChangedListener(cpfMask);
                    edtLogin.addTextChangedListener(cnpjMask);
                }
            }
        });

        loginBO = new LoginBO(this);
        getSupportActionBar().hide();
        rbgModo = (RadioGroup) findViewById(R.id.rbgModo);
        rbtCliente = (RadioButton) findViewById(R.id.rbtCliente);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha", null);







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


                if (loginBO.validarCamposLogin(validation)) {
                    if (rbgModo.getCheckedRadioButtonId() == R.id.rbtEmpresa){
                        Intent i = new Intent(LoginActivity.this, EmpresaActivity.class);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(LoginActivity.this, ClienteActivity.class);
                        startActivity(i);
                    }

                }
            }
        });

        btnNova = (Button) findViewById(R.id.btnNova);
        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, ModoActivity.class);
                startActivity(i);
            }
        });


    }

}
