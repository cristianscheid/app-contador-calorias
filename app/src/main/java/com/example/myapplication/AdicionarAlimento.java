package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdicionarAlimento extends AppCompatActivity {

    Alimento alimento;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_alimento);

        ArrayList<Alimento> alimentos = MainActivity.crud.readAllAlimentos();
        ArrayList<String> arrayNomeAlimentos = new ArrayList<>();

        for(Alimento a : alimentos) {
            arrayNomeAlimentos.add(a.getNome());
        }

        TextView textview = findViewById(R.id.textView);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(AdicionarAlimento.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter=new ArrayAdapter<>(AdicionarAlimento.this, android.R.layout.simple_list_item_1,arrayNomeAlimentos);
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        textview.setText(adapter.getItem(position));
                        alimento = alimentos.get(position);
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });

        Button buttonAdicionar = (Button)findViewById(R.id.buttonAdicionar);
        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());

                EditText editTextQuantidade = (EditText)findViewById(R.id.editTextQtd);

                Diario d = new Diario();
                d.setData(date);
                d.setIdAlimento(alimento.getId());
                d.setQuantidade(Double.parseDouble(editTextQuantidade.getText().toString()));

                MainActivity.crud.createDiario(d);

                startActivity(new Intent(AdicionarAlimento.this, DiarioAlimentar.class));
            }
        });

    }
}