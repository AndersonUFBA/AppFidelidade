package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nog.com.br.appfidelidade.dao.EmpresaDAO;
import nog.com.br.appfidelidade.dao.UsuarioDAO;
import nog.com.br.appfidelidade.entidade.Empresa;
import nog.com.br.appfidelidade.entidade.Usuario;

public class ListarClientesActivity extends AppCompatActivity {

    ListView lvCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        lvCliente = (ListView) findViewById(R.id.lvCliente);
    }

    @Override
    protected void onResume() {
        super.onResume();


        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> lista = usuarioDAO.buscarTodosUsuarios();

        ArrayAdapter<Usuario> adapterUsuario = new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1,lista);
        lvCliente.setAdapter(adapterUsuario);
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
                Intent i = new Intent(ListarClientesActivity.this, LoginActivity.class);
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
