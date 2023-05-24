package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ListaAlimentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alimentos);

        ArrayList<Alimento> alimentos = MainActivity.crud.readAllAlimentos();
        TableLayout tableLayout = findViewById(R.id.tableAlimentos);
        for(Alimento a : alimentos){
            TableRow row = new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//            row.setLayoutParams(lp);

            TextView textNomeAlimento = new TextView(this);
            textNomeAlimento.setText(a.getNome());
            textNomeAlimento.setTextSize(20);
            row.addView(textNomeAlimento);

            MaterialButton botaoEditar = new MaterialButton(this);
            botaoEditar.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_edit_black_24dp));
            botaoEditar.setLayoutParams(new TableRow.LayoutParams(100, 100));
            row.addView(botaoEditar);
            botaoEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditarAlimento.alimento = a;
                    startActivity(new Intent(ListaAlimentos.this, EditarAlimento.class));
                }
            });

            MaterialButton botaoExcluir = new MaterialButton(this);
            botaoExcluir.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_delete_black_24dp));
            botaoExcluir.setLayoutParams(new TableRow.LayoutParams(100, 100));
            row.addView(botaoExcluir);
            botaoExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.crud.deleteAlimento(a.getId());
                    finish();
                    startActivity(getIntent());
                }
            });

            tableLayout.addView(row);
        }

        Button buttonAdicionarAlimento = (Button)findViewById(R.id.buttonAdicionarAlimento);
        buttonAdicionarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaAlimentos.this, CadastrarAlimento.class));
            }
        });

    }
}