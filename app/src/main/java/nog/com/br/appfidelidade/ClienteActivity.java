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
import android.widget.Button;

import nog.com.br.appfidelidade.util.TipoMsg;
import nog.com.br.appfidelidade.util.Util;

public class ClienteActivity extends AppCompatActivity {

    private Button btnListPonto;
   private Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
       // getSupportActionBar().setTitle("Bem vindo!");
        btnMapa = (Button) findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClienteActivity.this, MapaActivity.class);
                startActivity(i);
                finish();
            }
        });

         btnListPonto  = (Button) findViewById(R.id.btnListPonto);
         btnListPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClienteActivity.this, ListarPontuacaoActivity.class);
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
                Intent i = new Intent(ClienteActivity.this, LoginActivity.class);
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
