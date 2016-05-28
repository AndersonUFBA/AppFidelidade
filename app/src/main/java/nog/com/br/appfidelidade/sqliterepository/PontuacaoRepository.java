package nog.com.br.appfidelidade.sqliterepository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nog.com.br.appfidelidade.util.Constantes;

/**
 * Created by andersonnogueira on 10/05/16.
 */
public class PontuacaoRepository extends SQLiteOpenHelper {
    public PontuacaoRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
