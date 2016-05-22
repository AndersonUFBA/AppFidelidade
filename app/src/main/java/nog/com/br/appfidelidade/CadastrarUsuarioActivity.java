package nog.com.br.appfidelidade;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import nog.com.br.appfidelidade.dao.UsuarioDAO;
import nog.com.br.appfidelidade.entidade.Usuario;
import nog.com.br.appfidelidade.util.Mask;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private TextWatcher cpfMask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);


        final EditText edtCpf = (EditText) findViewById(R.id.edtCpf);
        final EditText etSenha = (EditText) findViewById(R.id.etSenha);
        final EditText etNome = (EditText) findViewById(R.id.etNome);

        cpfMask = Mask.insert("###.###.###-##", edtCpf);
        edtCpf.addTextChangedListener(cpfMask);

        Button btCastrarUsr = (Button) findViewById(R.id.btCadastrarUsr);

        btCastrarUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                boolean resultado = usuarioDAO.inserirUsuario(new Usuario (edtCpf.getText().toString(),etSenha.getText().toString(),
                        etNome.getText().toString()));

                if (resultado){
                    finish();
                }else {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Erro no cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
