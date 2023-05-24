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

    public void createAlimento(Alimento a){
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", a.nome);
        valores.put("medida", a.medida);
        valores.put("quantidade", a.quantidade);
        valores.put("carboidratos", a.carboidratos);
        valores.put("proteinas", a.proteinas);
        valores.put("gorduras", a.gorduras);
        valores.put("id_usuario", "1");
        db.insert("alimentos", null, valores);
        db.close();
    }

    public void createDiario(Diario d){
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("data", d.data);
        valores.put("id_alimento", d.idAlimento);
        valores.put("quantidade", d.quantidade);
        db.insert("diarios", null, valores);
        db.close();
    }

    public ArrayList<Diario> readDiarioByDate(String date){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM diarios WHERE data='"+date+"'", null);
        ArrayList<Diario> diarios = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Diario d = new Diario();
                d.id = cursor.getInt(0);
                d.data = cursor.getString(1);
                d.idAlimento = cursor.getInt(2);
                d.quantidade = cursor.getDouble(3);
                diarios.add(d);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return diarios;
    }

    public ArrayList<Alimento> readAllAlimentos(){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alimentos", null);
        ArrayList<Alimento> alimentos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Alimento a = new Alimento();
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

    public Alimento readAlimentoById(int id){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alimentos WHERE id="+id, null);
        Alimento a = new Alimento();
        if (cursor.moveToFirst()) {
                a.id = cursor.getInt(0);
                a.nome = cursor.getString(1);
                a.medida = cursor.getString(2);
                a.quantidade = cursor.getDouble(3);
                a.carboidratos = cursor.getDouble(4);
                a.proteinas = cursor.getDouble(5);
                a.gorduras = cursor.getDouble(6);
        }
        return a;
    }

    public void updateAlimento(Alimento a){
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", a.nome);
        valores.put("medida", a.medida);
        valores.put("quantidade", a.quantidade);
        valores.put("carboidratos", a.carboidratos);
        valores.put("proteinas", a.proteinas);
        valores.put("gorduras", a.gorduras);
        valores.put("id_usuario", "1");
        db.update("alimentos", valores, "id="+a.id, null);
        db.close();
    }

    public void deleteAlimento(int id){
        db = banco.getReadableDatabase();
        db.delete("alimentos", "id="+id, null);
        db.close();
    }

}
