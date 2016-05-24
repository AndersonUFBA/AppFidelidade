package nog.com.br.appfidelidade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import nog.com.br.appfidelidade.MarcaMapa;
import nog.com.br.appfidelidade.util.Constantes;

/**
 * Created by andersonnogueira on 09/05/16.
 */
public class MarcaMapaRepository extends SQLiteOpenHelper {

    public MarcaMapaRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    /*
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
    } */

    public List<MarcaMapa> listarMarcaMapa() {
        List<MarcaMapa> lista = new ArrayList<MarcaMapa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PESSOA", null, null, null, null, null, "NOME");

        while (cursor.moveToNext()) {
          MarcaMapa marca =new MarcaMapa(0.0,0.0,"a");

            marca.setLatitude(cursor.getDouble(cursor.getColumnIndex("LATITUDE")));
            marca.setLongitude(cursor.getDouble(cursor.getColumnIndex("LONGITUDE")));
            marca.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            lista.add(marca);
        }

        return lista;
    }
}
