package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nog.com.br.appfidelidade.dao.PontuacaoDAO;
import nog.com.br.appfidelidade.entidade.Pontuacao;
import nog.com.br.appfidelidade.util.Mask;

public class InserirPontuacaoActivity extends AppCompatActivity {

    private TextWatcher cpfMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_pontuacao);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        final EditText edtCpf = (EditText) findViewById(R.id.etCpfPt);
        final EditText etCnpjPt = (EditText) findViewById(R.id.etCnpjPt);
        final EditText etValor = (EditText) findViewById(R.id.etValor);

        cpfMask = Mask.insert("###.###.###-##", edtCpf);
        edtCpf.addTextChangedListener(cpfMask);

        TextWatcher cnpjMask = Mask.insert("##.###.###/####-## ", etCnpjPt);
        etCnpjPt.addTextChangedListener(cnpjMask);

        Button btCastrarUsr = (Button) findViewById(R.id.buttonPt);

        btCastrarUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
                boolean resultado = pontuacaoDAO.inserirPontuacao(new Pontuacao(0, Integer.parseInt(etValor.getText().toString()),
                        edtCpf.getText().toString(), etCnpjPt.getText().toString()));
                Toast.makeText(InserirPontuacaoActivity.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();
                if (resultado) {
                    finish();
                } else {
                    Toast.makeText(InserirPontuacaoActivity.this, "Erro no cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override //cria o menu na tela
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sair:
                finish();
                Intent i = new Intent(InserirPontuacaoActivity.this, LoginActivity.class);
                startActivity(i);

                SharedPreferences.Editor editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("login");
                editor.remove("senha");
                editor.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
