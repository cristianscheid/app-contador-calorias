package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.SelectionEvent;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DiarioAlimentar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diario_alimentar);

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dataString = simpleDateFormat.format(MainActivity.data);

        TextView textviewTitulo = findViewById(R.id.textViewTitulo);
        textviewTitulo.setText("Diário Alimentar (" + dataString + ")");

        TableLayout tableLayout = findViewById(R.id.tableDiario);

        TableRow rowTitulo = new TableRow(this);
        TextView textViewTituloAlimento = new TextView(this);
        textViewTituloAlimento.setText("Alimento  ");
        textViewTituloAlimento.setTextSize(20);
        rowTitulo.addView(textViewTituloAlimento);
        TextView textViewTituloQtd = new TextView(this);
        textViewTituloQtd.setText("Quantidade  ");
        textViewTituloQtd.setTextSize(20);
        rowTitulo.addView(textViewTituloQtd);
        MaterialButton botaoAdicionar = new MaterialButton(this);
        botaoAdicionar.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_add_black_24dp));
        botaoAdicionar.setLayoutParams(new TableRow.LayoutParams(100, 100));
        rowTitulo.addView(botaoAdicionar);
        tableLayout.addView(rowTitulo);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiarioAlimentar.this, AdicionarAlimento.class));
            }
        });

        ArrayList<Diario> diarios = MainActivity.crud.readDiarioByDate(dataString);
        ArrayList<Alimento> alimentos = new ArrayList<>();
        Double totalCarboidrato = 0.0;
        Double totalProteina = 0.0;
        Double totalGordura = 0.0;
        Double totalCaloria = 0.0;

        for (Diario d : diarios){
            TableRow row = new TableRow(this);

            Alimento a = MainActivity.crud.readAlimentoById(d.getIdAlimento());
            totalCarboidrato = totalCarboidrato + a.getCarboidratos();
            totalProteina = totalProteina + a.getProteinas();
            totalGordura = totalGordura+ a.getGorduras();
            totalCaloria = totalCaloria+ (a.getCarboidratos() * 4) + (a.getProteinas() * 4) + (a.getGorduras() * 9);

            TextView textNomeAlimento = new TextView(this);
            textNomeAlimento.setText(a.getNome());
            textNomeAlimento.setTextSize(20);
            row.addView(textNomeAlimento);

            TextView textQtdAlimento = new TextView(this);
            textQtdAlimento.setText(d.getQuantidade().toString() + " " + a.getMedida());
            textQtdAlimento.setTextSize(20);
            row.addView(textQtdAlimento);

            MaterialButton botaoExcluir = new MaterialButton(this);
            botaoExcluir.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_delete_black_24dp));
            botaoExcluir.setLayoutParams(new TableRow.LayoutParams(100, 100));
            row.addView(botaoExcluir);
            botaoExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.crud.deleteDiario(d.getId());
                    finish();
                    startActivity(getIntent());
                }
            });

            tableLayout.addView(row);
        }

        TextView textViewInfo = findViewById(R.id.textViewInfo);
        textViewInfo.setTextSize(16);
        String info = "Carboidratos --- " + totalCarboidrato.toString() + "g" +
                "\nProteínas --------- " + totalProteina.toString() + "g" +
                "\nGorduras --------- " + totalGordura.toString() + "g" +
                "\nCalorias ----------- " + totalCaloria.toString() + "kcal";
        textViewInfo.setText(info);

        Button buttonCadastroAlimentos = (Button)findViewById(R.id.buttonCadastroAlimentos);
        buttonCadastroAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiarioAlimentar.this, CadastroAlimentos.class));
            }
        });

        Button buttonSelecionarData = (Button)findViewById(R.id.buttonSelecionarData);
        buttonSelecionarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiarioAlimentar.this, SelecionarData.class));
            }
        });


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

    }
}