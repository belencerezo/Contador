package com.example.contador1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText resultado;
    private Button inButton;
    private Button deButton;
    private Button reButton;
    private int cont;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont = 0;
        inButton = (Button) findViewById(R.id.inButton);
        deButton = (Button) findViewById(R.id.deButton);
        reButton = (Button) findViewById(R.id.reButton);
    }

    /* @Override
    protected void onSaveInstanceState(@NonNull Bundle estado) {
        estado.putInt("valor", cont);
        super.onSaveInstanceState(estado);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cont = savedInstanceState.getInt("valor",0);
        mostrarContador();
    } */

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences datos = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.putInt("valor", cont);
        miEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        cont = datos.getInt("valor", 0);
        mostrarContador();
    }

    public void Incrementar(View view) {
        cont++;
        mostrarContador();
    }
    
    public void Decrementar(View view) {
        CheckBox negativos=(CheckBox) findViewById(R.id.numNeg);
        cont--;
        if((cont<0) && !(negativos.isChecked())) {
            cont = 0;
        }
        mostrarContador();
    }

    public void Resetear(View view) {
        EditText numeroReset= (EditText) findViewById(R.id.numReset);
        InputMethodManager teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(numeroReset.getWindowToken(),0);

        String variable = numeroReset.getText().toString();

        if (variable.isEmpty()){
            variable=String.valueOf(0);
        }

        cont = Integer.parseInt(variable);
        mostrarContador();
    }
    
    private void mostrarContador() {
        TextView resultado = (TextView) findViewById(R.id.resultado);
        resultado.setText(String.valueOf(cont));
    }

}