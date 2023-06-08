package com.example.meustributos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private EditText remuneracaoEditText;
    private EditText semeaduraEditText;
    private TextView primiciaTextView;
    private TextView dizimoTextView;
    private TextView socorroTextView;
    private TextView gratidaoTextView;
    private TextView israelTextView;
    private TextView contribuicaoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos componentes da interface do usuário
        remuneracaoEditText = findViewById(R.id.remuneracaoEditText);
        semeaduraEditText = findViewById(R.id.semeaduraEditText);
        primiciaTextView = findViewById(R.id.primiciaTextView);
        dizimoTextView = findViewById(R.id.dizimoTextView);
        socorroTextView = findViewById(R.id.socorroTextView);
        gratidaoTextView = findViewById(R.id.gratidaoTextView);
        israelTextView = findViewById(R.id.israelTextView);
        contribuicaoTextView = findViewById(R.id.contribuicaoTextView);

        // Configuração do botão de cálculo
        Button calcularButton = findViewById(R.id.calcularButton);
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTributos();
            }
        });

        // Configuração do botão de compartilhamento
        FloatingActionButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilharResultados();
            }
        });

        // Configuração do botão de limpar campos
        FloatingActionButton limparButton = findViewById(R.id.limparButton);
        limparButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });

    }


    /**
     * Método responsável por calcular os tributos com base na remuneração e semeadura fornecidas pelo usuário.
     */
    @SuppressLint("SetTextI18n")
    private void calcularTributos() {
        if (remuneracaoEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, digite a remuneração.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtém o valor da remuneração fornecida pelo usuário
        double remuneracao = Double.parseDouble(remuneracaoEditText.getText().toString());

        // Verifica se o campo de semeadura está vazio
        if (semeaduraEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, digite a semeadura.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtém o valor da semeadura fornecida pelo usuário
        double semeadura = Double.parseDouble(semeaduraEditText.getText().toString());

        // Calcula os valores dos tributos
        double primicia = Math.ceil(remuneracao / 30);
        double dizimo = Math.ceil((remuneracao - primicia) / 10);
        double socorro = Math.ceil((remuneracao * 2) / 100);
        double gratidao = Math.ceil(remuneracao / 1000);
        double israel = Math.ceil(remuneracao / 100);

        primiciaTextView.setText(" " + primicia);
        dizimoTextView.setText(" " + dizimo);
        socorroTextView.setText(" " + socorro);
        gratidaoTextView.setText("  " + gratidao);
        israelTextView.setText(" " + israel);

        double contribuicao = primicia + dizimo + socorro + gratidao + semeadura + israel;
        contribuicaoTextView.setText(" " + contribuicao);
    }

    //Compartilha os resultados
    @SuppressLint("QueryPermissionsNeeded")
    private void compartilharResultados() {
        String primicia = primiciaTextView.getText().toString();
        String dizimo = dizimoTextView.getText().toString();
        String socorro = socorroTextView.getText().toString();
        String gratidao = gratidaoTextView.getText().toString();
        String israel = israelTextView.getText().toString();
        String semeadura = semeaduraEditText.getText().toString();
        String contribuicao = contribuicaoTextView.getText().toString();


        String mensagem = "Resultados dos Tributos:" +
                "\nPrimícia: " + primicia +
                "\nDízimo: " + dizimo +
                "\nOferta de Socorro: " + socorro +
                "\nOferta de Gratidão: " + gratidao +
                "\nSemeadura: " + semeadura +
                "\nOferta para Israel: " + israel +
                "\n\nContribuição Total: " + contribuicao;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        Intent chooser = Intent.createChooser(intent, "Compartilhar via");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "Nenhum aplicativo de compartilhamento disponível.", Toast.LENGTH_SHORT).show();
        }
    }

    //Limpa os dados digitados
    private void limparCampos() {
        remuneracaoEditText.setText("");
        semeaduraEditText.setText("");
        primiciaTextView.setText("");
        dizimoTextView.setText("");
        socorroTextView.setText("");
        gratidaoTextView.setText("");
        israelTextView.setText("");
        contribuicaoTextView.setText("");
    }
}