package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco_dados.db";
    private static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT,"
                + "senha TEXT"
                +")";
        String sql2 = "CREATE TABLE alimentos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT,"
                + "medida TEXT,"
                + "quantidade REAL,"
                + "carboidratos REAL,"
                + "proteinas REAL,"
                + "gorduras REAL,"
                + "id_usuario INTEGER"
                +")";
        String sql3 = "CREATE TABLE diarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "data TEXT,"
                + "id_alimento INTEGER,"
                + "quantidade REAL"
                +")";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS alimentos");
        db.execSQL("DROP TABLE IF EXISTS diarios");
        onCreate(db);
    }
}
