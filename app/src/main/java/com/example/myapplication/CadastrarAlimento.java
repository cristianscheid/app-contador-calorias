package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class CadastrarAlimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_editar_alimento);

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("Adicionar Alimento");

        Button buttonSalvar = (Button)findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNome = (EditText)findViewById(R.id.editTextNome);
                EditText editTextMedida = (EditText)findViewById(R.id.editTextMedida);
                EditText editTextQuantidade = (EditText)findViewById(R.id.editTextQuantidade);
                EditText editTextCarboidratos = (EditText)findViewById(R.id.editTextCarboidratos);
                EditText editTextProteinas = (EditText)findViewById(R.id.editTextProteinas);
                EditText editTextGorduras = (EditText)findViewById(R.id.editTextGorduras);
                String nome = editTextNome.getText().toString();
                String medida = editTextMedida.getText().toString();
                String quantidade = editTextQuantidade.getText().toString();
                String carboidratos = editTextCarboidratos.getText().toString();
                String proteinas = editTextProteinas.getText().toString();
                String gorduras = editTextGorduras.getText().toString();

                List<String> editTexts = Arrays.asList(nome, medida, quantidade, carboidratos, proteinas, gorduras);
                boolean campoVazio = false;
                for(String editText : editTexts){
                    if(editText.trim().isEmpty() || editText.trim().equals(".")){
                        campoVazio = true;
                        break;
                    }
                }

                if(campoVazio){
                    Toast.makeText(CadastrarAlimento.this, "Por favor preencha todos os campos!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Alimento alimento = new Alimento(nome, medida, Double.parseDouble(quantidade),
                            Double.parseDouble(carboidratos), Double.parseDouble(proteinas), Double.parseDouble(gorduras));
                    MainActivity.crud.createAlimento(alimento);
                    startActivity(new Intent(CadastrarAlimento.this, ListaAlimentos.class));
                }
            }
        });

    }
}