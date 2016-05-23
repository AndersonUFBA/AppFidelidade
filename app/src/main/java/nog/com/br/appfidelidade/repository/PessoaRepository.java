package nog.com.br.appfidelidade.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nog.com.br.appfidelidade.entidade.Pessoa;
import nog.com.br.appfidelidade.entidade.TipoPessoa;
import nog.com.br.appfidelidade.util.Constantes;

/**
 * Created by andersonnogueira on 09/05/16.
 */
public class PessoaRepository extends SQLiteOpenHelper {

    public PessoaRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_PESSOA( ");
        query.append(" ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" ENDERECO TEXT(50),");
        query.append(" CPF TEXT(14),");
        query.append(" CNPJ TEXT(14))");

        db.execSQL(query.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void salvarPessoa(Pessoa pessoa) {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", pessoa.getNome());
        contentValues.put("ENDERECO", pessoa.getEndereco());
        switch (pessoa.getTipoPessoa()) {
            case FISICA:
                contentValues.put("CPF", pessoa.getCpfCpnj());
                break;
            case JURIDICA:
                contentValues.put("CNPJ", pessoa.getCpfCpnj());
                break;
        }

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_PESSOA( ");
        query.append(" ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" ENDERECO TEXT(50),");
        query.append(" CPF TEXT(14),");
        query.append(" CNPJ TEXT(14))");

        db.execSQL(query.toString());

        db.insert("TB_PESSOA", null, contentValues);
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PESSOA", null, null, null, null, null, "NOME");

        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setIdPessoa(cursor.getInt(cursor.getColumnIndex("ID_PESSOA")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            pessoa.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
            String cpf = cursor.getString(cursor.getColumnIndex("CPF"));
            String cnpj = cursor.getString(cursor.getColumnIndex("CNPJ"));
            if (cpf != null) {
                pessoa.setTipoPessoa(TipoPessoa.FISICA);
                pessoa.setCpfCpnj(cpf);
            } else {
                pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
                pessoa.setCpfCpnj(cnpj);
            }

            lista.add(pessoa);
        }

        return lista;
    }
}
