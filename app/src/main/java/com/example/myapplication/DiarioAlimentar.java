package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiarioAlimentar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diario_alimentar);

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        TableLayout tableLayout = findViewById(R.id.tableDiario);

        TableRow rowTitulo = new TableRow(this);
        TextView textViewTituloAlimento = new TextView(this);
        textViewTituloAlimento.setText("Alimento");
        textViewTituloAlimento.setTextSize(20);
        rowTitulo.addView(textViewTituloAlimento);
        TextView textViewTituloQtd = new TextView(this);
        textViewTituloQtd.setText("Quantidade");
        textViewTituloQtd.setTextSize(20);
        rowTitulo.addView(textViewTituloQtd);
        tableLayout.addView(rowTitulo);

        ArrayList<Diario> diarios = MainActivity.crud.readDiarioByDate(date);
        for (Diario d : diarios){
            TableRow row = new TableRow(this);

            TextView textNomeAlimento = new TextView(this);
            textNomeAlimento.setText(MainActivity.crud.readAlimentoById(d.getIdAlimento()).getNome());
            textNomeAlimento.setTextSize(20);
            row.addView(textNomeAlimento);

            TextView textQtdAlimento = new TextView(this);
            textQtdAlimento.setText(d.getQuantidade().toString());
            textQtdAlimento.setTextSize(20);
            row.addView(textQtdAlimento);

            tableLayout.addView(row);
        }

//        for (Alimento a : cafeDaManha){
//            if (cafeDaManha.get(0) == a){
//                TableRow rowTitulo = new TableRow(this);
//
//                TextView textView = new TextView(this);
//                textView.setText("Café da Manhã");
//                textView.setTextSize(20);
//                textView.setTypeface(null, Typeface.BOLD);
//                rowTitulo.addView(textView);
//
//                MaterialButton botaoAdicionar = new MaterialButton(this);
//                botaoAdicionar.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_add_black_24dp));
//                botaoAdicionar.setLayoutParams(new TableRow.LayoutParams(100, 100));
//                rowTitulo.addView(botaoAdicionar);
//
//                tableLayout.addView(rowTitulo);
//            }

        Button buttonAdicionarAlimento = (Button)findViewById(R.id.buttonAdicionarAlimento);
        buttonAdicionarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiarioAlimentar.this, AdicionarAlimento.class));
            }
        });

    }
}