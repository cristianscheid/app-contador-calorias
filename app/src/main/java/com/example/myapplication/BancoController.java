package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String create(Alimento a){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", a.nome);
        valores.put("medida", a.medida);
        valores.put("quantidade", a.quantidade);
        valores.put("carboidratos", a.carboidratos);
        valores.put("proteinas", a.proteinas);
        valores.put("gorduras", a.gorduras);
        valores.put("id_usuario", "1");
        resultado = db.insert("alimentos", null, valores);
        db.close();
        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void delete(int id){
        db = banco.getReadableDatabase();
        db.delete("alimentos", "id="+id, null);
        db.close();
    }

    public ArrayList<Alimento> readAllAlimentos(){
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM alimentos", null);
        ArrayList<Alimento> alimentos = new ArrayList<>();
        Alimento a = new Alimento();
        if (cursor.moveToFirst()) {
            do {
                a.id = cursor.getInt(0);
                a.nome = cursor.getString(1);
                a.medida = cursor.getString(2);
                a.quantidade = cursor.getDouble(3);
                a.carboidratos = cursor.getDouble(4);
                a.proteinas = cursor.getDouble(5);
                a.gorduras = cursor.getDouble(6);
                alimentos.add(a);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alimentos;
    }
}
