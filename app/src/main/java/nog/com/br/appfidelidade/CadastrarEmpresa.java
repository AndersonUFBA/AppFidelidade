package nog.com.br.appfidelidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nog.com.br.appfidelidade.dao.EmpresaDAO;
import nog.com.br.appfidelidade.entidade.Empresa;
import nog.com.br.appfidelidade.entidade.Usuario;
import nog.com.br.appfidelidade.util.Mask;

public class CadastrarEmpresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_empresa);

        final EditText edtCnpj = (EditText) findViewById(R.id.edtCnpj);
        final EditText etSenha = (EditText) findViewById(R.id.etSenhaE);
        final EditText etDescricao = (EditText) findViewById(R.id.etDescricao);
        final EditText etLatitude = (EditText) findViewById(R.id.etLat);
        final EditText etLongitude = (EditText) findViewById(R.id.etLong);

        TextWatcher cnpjMask = Mask.insert("##.###.###/####-##", edtCnpj);
        edtCnpj.addTextChangedListener(cnpjMask);

        Button btCadastrarEmp = (Button) findViewById(R.id.btCadastrarEmp);
        btCadastrarEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmpresaDAO empresaDAO = new EmpresaDAO();
                boolean resultado = empresaDAO.inserirEmpresa(new Empresa(edtCnpj.getText().toString(),
                        etSenha.getText().toString(),
                        etDescricao.getText().toString(), etLatitude.getText().toString(), etLongitude.getText().toString()));

                if (resultado) {
                    finish();
                } else {
                    Toast.makeText(CadastrarEmpresa.this, "Erro no cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
