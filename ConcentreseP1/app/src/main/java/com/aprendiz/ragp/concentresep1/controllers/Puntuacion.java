package com.aprendiz.ragp.concentresep1.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.concentresep1.R;
import com.aprendiz.ragp.concentresep1.models.GestorDB;
import com.aprendiz.ragp.concentresep1.models.Score;

import java.util.List;

public class Puntuacion extends AppCompatActivity implements View.OnClickListener{
    TextView txtPrimero, txtSegundo, txtTercero, txtCuarto;
    RadioButton rbtnFacil, rbtnMedio, rbtnDificil, rbtnTiempo, rbtnIntentos;
    String modo="1", dificultad="4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputData();
        modo="1";
        dificultad="4";
    }

    private void inizialite() {
        txtPrimero = findViewById(R.id.txtPrimero);
        txtSegundo = findViewById(R.id.txtSegundo);
        txtTercero = findViewById(R.id.txtTercero);
        txtCuarto = findViewById(R.id.txtCuarto);
        rbtnFacil = findViewById(R.id.rbtnFacilP);
        rbtnMedio = findViewById(R.id.rbtnMedioP);
        rbtnDificil = findViewById(R.id.rbtnDificilP);
        rbtnTiempo = findViewById(R.id.rbtnTiempoP);
        rbtnIntentos = findViewById(R.id.rbtnIntentosP);

        rbtnFacil.setOnClickListener(this);
        rbtnMedio.setOnClickListener(this);
        rbtnDificil.setOnClickListener(this);
        rbtnTiempo.setOnClickListener(this);
        rbtnIntentos.setOnClickListener(this);

    }


    public void inputData(){
        GestorDB gestorDB = new GestorDB(this);

        List<Score> list =gestorDB.scoreList(modo,dificultad);

        if (list.size()>0){
            txtPrimero.setText(list.get(0).getJugador()+" "+list.get(0).getPuntuacion());
        }else {
            Toast.makeText(this, "No hay puntuaciones", Toast.LENGTH_SHORT).show();
            txtPrimero.setText("");
            txtSegundo.setText("");
            txtTercero.setText("");
            txtCuarto.setText("");
        }

        if (list.size()>1){
            txtSegundo.setText(list.get(1).getJugador()+" "+list.get(1).getPuntuacion());
        }


        if (list.size()>2){
            txtTercero.setText(list.get(2).getJugador()+" "+list.get(2).getPuntuacion());
        }

        if (list.size()>3){
            txtCuarto.setText(list.get(3).getJugador()+" "+list.get(3).getPuntuacion());
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtnFacilP:
                dificultad="4";
                inputData();
                break;

            case R.id.rbtnMedioP:
                dificultad="6";
                inputData();
                break;


            case R.id.rbtnDificilP:
                dificultad="8";
                inputData();
                break;

            case R.id.rbtnTiempoP:
                modo="1";
                inputData();
                break;

            case R.id.rbtnIntentosP:
                modo="2";
                inputData();
                break;

        }
    }
}
