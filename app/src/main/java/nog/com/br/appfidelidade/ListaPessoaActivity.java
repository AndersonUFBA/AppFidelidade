package nog.com.br.appfidelidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nog.com.br.appfidelidade.entidade.Pessoa;
import nog.com.br.appfidelidade.sqliterepository.PessoaRepository;

public class ListaPessoaActivity extends AppCompatActivity {

    private ListView lstPessoas;

    private PessoaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        repository = new PessoaRepository(this);

        lstPessoas = (ListView) findViewById(R.id.lstPessoas);

        List<Pessoa> lista = repository.listarPessoas();

        List<String> valores = new ArrayList<String>();
        for (Pessoa p : lista) {
            valores.add(p.getNome());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        lstPessoas.setAdapter(adapter);
    }

}
