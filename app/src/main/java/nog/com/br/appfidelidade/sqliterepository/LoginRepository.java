package nog.com.br.appfidelidade.sqliterepository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nog.com.br.appfidelidade.util.Constantes;
import nog.com.br.appfidelidade.util.Util;

/**
 * Created by andersonnogueira on 03/05/16.
 */
public class LoginRepository extends SQLiteOpenHelper{

    public LoginRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_LOGIN(");
        query.append("ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("USUARIO TEXT(15) NOT NULL,");
        query.append("SENHA TEXT(15) NOT NULL);");

        query.append("CREATE TABLE  TB_PESSOA( ");
        query.append(" ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" ENDERECO TEXT(50),");
        query.append(" CPF TEXT(14),");
        query.append(" CNPJ TEXT(14),");
        query.append(" LATITUDE DOUBLE,");
        query.append(" LONGITUDE DOUBLE);");

        query.append("CREATE TABLE  TB_PONTUACAO( ");
        query.append(" ID_PONTUACAO INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" CPF_COMP TEXT(14),");
        query.append(" CNPJ_VEND TEXT(14),");
        query.append(" PONTUACAO DOUBLE)");

        db.execSQL(query.toString());
        popularBD(db);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private void popularBD(SQLiteDatabase db){
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA) VALUES(?,?)");


        db.execSQL(query.toString(), new String[] {"admin","admin"});

    }

    public void listarLogins(Activity activity) {
        SQLiteDatabase db = getReadableDatabase();//método para ler
        Cursor cursor = db.query("TB_LOGIN", null, null, null, null, null, "USUARIO");


        while (cursor.moveToNext()) {

            String txt = "ID do usuário: " + String.valueOf(cursor.getInt(cursor.getColumnIndex("ID_LOGIN")));
            Util.showMsgToast(activity, txt);

        }
    }

    public void addLogin(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.insert("TB_LOGIN", null, contentValues);
    }

    public void updateLogin(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.update("TB_LOGIN", contentValues, "id_login >1", null);

    }

    public void deleteLogin(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TB_LOGIN","usuario = ? or senha = ?", new String[] {login, senha});

    }
}
