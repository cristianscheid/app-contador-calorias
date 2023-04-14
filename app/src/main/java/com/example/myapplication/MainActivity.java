package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alimento alimento = new Alimento();
        alimento.nome = "arroz";
        alimento.medida = "un";
        alimento.quantidade = 20.5;
        alimento.carboidratos = 20.0;
        alimento.proteinas = 20.0;
        alimento.gorduras = 20.1;

        BancoController crud = new BancoController(getBaseContext());
        crud.create(alimento);

        ArrayList<Alimento> alimentos = crud.readAllAlimentos();

        TableLayout tableLayout = findViewById(R.id.tableAlimentos);
        for(Alimento a : alimentos){

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView textNomeAlimento = new TextView(this);
            textNomeAlimento.setText(a.getNome());
            textNomeAlimento.setTextSize(20);
            row.addView(textNomeAlimento);

            MaterialButton botaoEditar = new MaterialButton(this);
            botaoEditar.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_edit_black_24dp));
            botaoEditar.setWidth(24);
            botaoEditar.setId(a.getId());
            row.addView(botaoEditar);

            MaterialButton botaoExcluir = new MaterialButton(this);
            botaoExcluir.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_delete_black_24dp));
            botaoExcluir.setWidth(24);
            botaoExcluir.setId(a.getId());
            botaoExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    crud.delete(a.getId());
                    finish();
                    startActivity(getIntent());
                }
            });
            row.addView(botaoExcluir);

            tableLayout.addView(row);
        }

        Button buttonAdicionarAlimento = (Button)findViewById(R.id.buttonAdicionarAlimento);
        buttonAdicionarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdicionarAlimento.class));
            }
        });

    }
}