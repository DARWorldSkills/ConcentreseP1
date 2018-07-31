package com.aprendiz.ragp.concentresep1.controllers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.aprendiz.ragp.concentresep1.R;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnTiempo, rbtnIntentos;
    SharedPreferences juegoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();
        inputData();

    }



    private void inizialite() {
        rbtnTiempo = findViewById(R.id.rbtnTiempoC);
        rbtnIntentos = findViewById(R.id.rbtnIntentosC);
    }



    private void inputData() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        int modo = juegoC.getInt("modo",1);
        if (modo==1){
            rbtnTiempo.setChecked(true);
        }

        if (modo==2){
            rbtnIntentos.setChecked(true);
        }

    }

    public void guardar(View view) {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        SharedPreferences.Editor editor = juegoC.edit();
        if (rbtnIntentos.isChecked()) {
            editor.putInt("modo",2);
        }

        if (rbtnTiempo.isChecked()) {
            editor.putInt("modo",1);
        }

        editor.commit();


    }
}
