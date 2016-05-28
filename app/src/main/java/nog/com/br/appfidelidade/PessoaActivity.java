package nog.com.br.appfidelidade;

         import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextWatcher;
        import android.view.View;
         import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
         import android.widget.TextView;

         import nog.com.br.appfidelidade.entidade.Pessoa;
         import nog.com.br.appfidelidade.entidade.TipoPessoa;
         import nog.com.br.appfidelidade.sqliterepository.PessoaRepository;
         import nog.com.br.appfidelidade.util.Mask;
         import nog.com.br.appfidelidade.util.Util;

public class PessoaActivity extends AppCompatActivity {


    private TextView txtCpfCnpj;
    private EditText edtNome, edtEndereco, edtCpfCnpj;
    private RadioGroup rbgCpfCnpj;
    private RadioButton rbtCpf;
    private TextWatcher cpfMask, cnpjMask;

    private int cpfCpnfSelecionado;

    private PessoaRepository pessoaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        pessoaRepository = new PessoaRepository(this);


        edtCpfCnpj = (EditText) findViewById(R.id.edtCpfCnpj);
        rbgCpfCnpj = (RadioGroup) findViewById(R.id.rbgCpfCnpj);
        rbtCpf = (RadioButton) findViewById(R.id.rbtCpf);
        txtCpfCnpj = (TextView) findViewById(R.id.txtCpfCnpj);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);

        cpfMask = Mask.insert("###.###.###-##", edtCpfCnpj);
        edtCpfCnpj.addTextChangedListener(cpfMask);

        cnpjMask = Mask.insert("##.###.###/####-##", edtCpfCnpj);

        rbgCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                edtCpfCnpj.setText("");
                edtCpfCnpj.requestFocus();
                cpfCpnfSelecionado = group.getCheckedRadioButtonId();
                if (cpfCpnfSelecionado == rbtCpf.getId()) {
                    edtCpfCnpj.removeTextChangedListener(cnpjMask);
                    edtCpfCnpj.addTextChangedListener(cpfMask);
                    txtCpfCnpj.setText("CPF:");
                } else {
                    edtCpfCnpj.removeTextChangedListener(cpfMask);
                    edtCpfCnpj.addTextChangedListener(cnpjMask);
                    txtCpfCnpj.setText("CNPJ:");
                }
            }
        });

        edtCpfCnpj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (rbgCpfCnpj.getCheckedRadioButtonId() == rbtCpf.getId()) {
                    if (edtCpfCnpj.getText().length() < 14) {
                        edtCpfCnpj.setText("");
                    }
                } else {
                    if (edtCpfCnpj.getText().length() < 18) {
                        edtCpfCnpj.setText("");
                    }
                }
            }
        });

    }




    public void enviarPessoa(View view) {
        Pessoa p = montarPessoa();
        if (!validarPessoa(p)) {
            pessoaRepository.salvarPessoa(p);
            Intent i = new Intent(this, ListaPessoaActivity.class);
            startActivity(i);
            finish();
            Util.showMsgToast(this, "Cadastro ok!");

        }
    }

    private boolean validarPessoa(Pessoa pessoa) {
        boolean erro = false;
        if (pessoa.getNome() == null || "".equals(pessoa.getNome())) {
            erro = true;
            edtNome.setError("Campo Nome obrigatório!");
        }
        if (pessoa.getEndereco() == null || "".equals(pessoa.getEndereco())) {
            erro = true;
            edtEndereco.setError("Campo Endereço obrigatório!");
        }
        if (pessoa.getCpfCpnj() == null || "".equals(pessoa.getCpfCpnj())) {
            erro = true;
            switch (rbgCpfCnpj.getCheckedRadioButtonId()) {
                case R.id.rbtCpf:
                    edtCpfCnpj.setError("Campo CPF obrigatório!");
                    break;
                case R.id.rbtCnpj:
                    edtCpfCnpj.setError("Campo CNPJ obrigatório!");
                    break;
            }
        } else {
            switch (rbgCpfCnpj.getCheckedRadioButtonId()) {
                case R.id.rbtCpf:
                    if (edtCpfCnpj.getText().length() < 14) {
                        erro = true;
                        edtCpfCnpj.setError("Campo CPF deve ter 11 caracteres!");
                    }
                    break;
                case R.id.rbtCnpj:
                    if (edtCpfCnpj.getText().length() < 18) {
                        erro = true;
                        edtCpfCnpj.setError("Campo CPF deve ter 14 caracteres!");
                    }
                    break;
            }
        }

        return erro;
    }

    private Pessoa montarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edtNome.getText().toString());
        pessoa.setEndereco(edtEndereco.getText().toString());
        pessoa.setCpfCpnj(edtCpfCnpj.getText().toString());

        switch (rbgCpfCnpj.getCheckedRadioButtonId()) {
            case R.id.rbtCpf:
                pessoa.setTipoPessoa(TipoPessoa.FISICA);
                break;
            case R.id.rbtCnpj:
                pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
                break;
        }

        return pessoa;
    }
}

