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
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nog.com.br.appfidelidade.dao.EmpresaDAO;
import nog.com.br.appfidelidade.dao.PontuacaoDAO;
import nog.com.br.appfidelidade.entidade.Empresa;
import nog.com.br.appfidelidade.entidade.Pontuacao;
import nog.com.br.appfidelidade.entidade.PontuacaoNew;

public class ListarPontuacaoActivity extends AppCompatActivity {

    EditText edtCpfPt;
    ListView lvEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontuacao);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //edtCpfPt = (EditText) findViewById(R.id.edtCpfPt);
        lvEmpresa = (ListView) findViewById(R.id.lvEmpresa);

    }

    @Override
    protected void onResume() {
        super.onResume();


        PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
        ArrayList<PontuacaoNew> lista = pontuacaoDAO.buscarPontuacaoUsuario("111.111.111-11");

        ArrayAdapter<PontuacaoNew> adapterEmpresa = new ArrayAdapter<PontuacaoNew>(this,android.R.layout.simple_list_item_1,lista);
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
