package nog.com.br.appfidelidade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo);

        Button btCriarCliente = (Button) findViewById(R.id.btCriarCliente);
        btCriarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModoActivity.this, CadastrarUsuarioActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button btCriarEmpresa = (Button) findViewById(R.id.btCriarEmpresa);
        btCriarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModoActivity.this, CadastrarEmpresa.class);
                startActivity(i);
                finish();
            }
        });
    }
}
