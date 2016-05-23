package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BonificacaoActivity extends AppCompatActivity {

    private TextWatcher cpfMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonificacao);

        final EditText etCnpjBn = (EditText) findViewById(R.id.etCnpjBn);
        final EditText etCpfBn = (EditText) findViewById(R.id.etCpfBn);

        cpfMask = Mask.insert("###.###.###-##", etCpfBn);
        etCpfBn.addTextChangedListener(cpfMask);

        TextWatcher cnpjMask = Mask.insert("##.###.###/####-## ", etCnpjBn);
        etCnpjBn.addTextChangedListener(cnpjMask);

        Button btnBon1 = (Button) findViewById(R.id.btnBon1);
        btnBon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
                boolean resultado = pontuacaoDAO.atualizarPontuacao(new Pontuacao(0, 0,
                        etCpfBn.getText().toString(), etCnpjBn.getText().toString()));


                if(resultado) {
                    Toast.makeText(BonificacaoActivity.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(BonificacaoActivity.this, "Erro no cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override //cria o menu na tela
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_sair:
                finish();
                Intent i = new Intent(BonificacaoActivity.this, LoginActivity.class);
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
