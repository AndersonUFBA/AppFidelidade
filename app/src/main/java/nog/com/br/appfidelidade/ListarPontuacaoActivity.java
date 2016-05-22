package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
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

public class ListarPontuacaoActivity extends AppCompatActivity {

    ListView lvEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontuacao);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        lvEmpresa = (ListView) findViewById(R.id.lvEmpresa);

        Button btTelaCadastro = (Button) findViewById(R.id.btTelaCadastro);

        btTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListarPontuacaoActivity.this, LoginActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        EmpresaDAO empresaDAO = new EmpresaDAO();
        ArrayList<Empresa> lista = empresaDAO.buscarTodosEmpresas();

        ArrayAdapter<Empresa> adapterEmpresa = new ArrayAdapter<Empresa>(this,android.R.layout.simple_list_item_1,lista);
        lvEmpresa.setAdapter(adapterEmpresa);
    }
   /* @Override //cria o menu na tela
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
                Intent i = new Intent(ListarPontuacaoActivity.this, LoginActivity.class);
                startActivity(i);

                SharedPreferences.Editor editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("login");
                editor.remove("senha");
                editor.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
