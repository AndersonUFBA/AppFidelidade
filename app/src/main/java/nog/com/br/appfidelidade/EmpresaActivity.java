package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import nog.com.br.appfidelidade.dao.EmpresaDAO;
import nog.com.br.appfidelidade.entidade.Empresa;

public class EmpresaActivity extends AppCompatActivity {
    private Button btnListarClientes;
    private Button btnInserirPontos;
    private Button btnBonificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        btnBonificar = (Button) findViewById(R.id.btnBonificar);
        btnInserirPontos = (Button) findViewById(R.id.btnInserirPontos);
        btnListarClientes = (Button) findViewById(R.id.btnListarClientes);

        btnBonificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmpresaActivity.this, BonificacaoActivity.class);
                startActivity(i);
            }
        });
        btnInserirPontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmpresaActivity.this, InserirPontuacaoActivity.class);
                startActivity(i);
            }
        });
        btnListarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmpresaActivity.this, ListarClientesActivity.class);
                startActivity(i);
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
                Intent i = new Intent(EmpresaActivity.this, LoginActivity.class);
                startActivity(i);

                SharedPreferences.Editor editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("login");
                editor.remove("senha");
                editor.remove("modo");
                editor.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
