package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private String operacao;
    private EditText edtResultado;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonVirgula;
    private Button buttonClear;
    private Button buttonVezes;
    private Button buttonDividir;
    private Button buttonMenos;
    private Button buttonMais;
    private Button buttonIgual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operacao = "";
        edtResultado = findViewById(R.id.edtResultado);
        edtResultado.setEnabled(false);
        buscarButtonsPorId();
        addEventListeners();
    }


    private void buscarButtonsPorId() {

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonClear = findViewById(R.id.buttonClear);
        buttonVezes = findViewById(R.id.buttonVezes);
        buttonDividir = findViewById(R.id.buttonDividir);
        buttonMais = findViewById(R.id.buttonMais);
        buttonMenos = findViewById(R.id.buttonMenos);
        buttonVirgula = findViewById(R.id.buttonVirgula);
        buttonIgual = findViewById(R.id.buttonIgual);

    }

    private void addEventListeners() {

        List<Button> buttons = getListButtonsForConcat();
        for (Button btn : buttons)
            setOnClickListenerBtnConcat(btn);
        setOnClickListenerBtnClear();
        setOnClickListenerBtnIgual();

    }

    private void setOnClickListenerBtnIgual() {

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacao = calcularOperacao(operacao);
                edtResultado.setText(operacao);
            }
        });
    }

    private void setOnClickListenerBtnClear() {
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacao = "";
                edtResultado.setText(operacao);
            }
        });
    }

    private void setOnClickListenerBtnConcat(final Button btn) {

        final String btnText = btn.getText().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificarBotoesComRestricao(btnText, operacao)) {
                    operacao = operacao.concat(btnText);
                    edtResultado.setText(operacao);
                }
            }

        });
    }

    private List<Button> getListButtonsForConcat() {
        return Arrays.asList(
                button0, button1,
                button2, button3,
                button4, button5,
                button6, button7,
                button8, button9,
                buttonMais, buttonMenos,
                buttonVezes, buttonDividir,
                buttonVirgula);
    }

    private boolean verificarBotoesComRestricao(String btnText, String operacao) {

        switch (btnText) {
            case "+": return !operacao.endsWith("+") && !operacao.isEmpty();
            case "-": return !operacao.endsWith("-") && !operacao.isEmpty();
            case "*": return !operacao.endsWith("*") && !operacao.isEmpty();
            case "/": return !operacao.endsWith("/") && !operacao.isEmpty();
            case ".": return !operacao.contains(".") && !operacao.isEmpty();
            default: return true;
        }
    }

    private String calcularOperacao(String operacao) {

        String resultado = String.valueOf(new Expression(operacao).calculate());

        if (verificarSeOperacaoNaoFoiValida(resultado)) {
            emitirAlertaDeOperacaoInvalida();
            return operacao;
        } else
            return resultado;
    }

    private boolean verificarSeOperacaoNaoFoiValida(String operacao) { return operacao.trim().toUpperCase().equals("NAN"); }

    private void emitirAlertaDeOperacaoInvalida() { Toast.makeText(MainActivity.this, "OPERAÇÃO INVALIDA", Toast.LENGTH_SHORT).show(); }
}
