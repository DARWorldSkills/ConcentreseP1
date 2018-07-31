package com.aprendiz.ragp.concentresep1.controllers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aprendiz.ragp.concentresep1.R;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity {
    private int [] imagenesJuego = {
            R.drawable.atleta,
            R.drawable.balon,
            R.drawable.banderacar,
            R.drawable.guante,
            R.drawable.medalla,
            R.drawable.natacion,
            R.drawable.silbato,
            R.drawable.trofeocham,
    };

    private int fondoJuego = R.drawable.signo;
    private int [] imagenesFondo, imagenesAleatorias;
    private List<Integer> imagenesSelect = new ArrayList<>();
    TextView txtJugador1, txtJugador2, txtPuntuacion1, txtPuntuacion2, txtTiempo;
    ProgressBar pTiempo;
    int movimientos, pos1=-1, pos2=-1, salir, nivel=4, canselect;
    int inicioJuego, modo_juego, puntuacionJ1, puntuacionJ2;
    int alto =100, ancho=100;
    GridView contenedorJuego;
    boolean bandera = true;
    ImageView imagen1, imagen2;

    SharedPreferences juegoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        inputValues();
        obtenerMedidas();
        turnos();

    }

    private void inizialite() {
        txtJugador1 = findViewById(R.id.txtJugador1J);
        txtJugador2 = findViewById(R.id.txtJugador2J);
        txtPuntuacion1 = findViewById(R.id.txtPuntuacion1J);
        txtPuntuacion2 = findViewById(R.id.txtPuntuacion2J);
        contenedorJuego = findViewById(R.id.contenedorJuego);
    }


    private void obtenerMedidas() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = (int) getResources().getDisplayMetrics().density;
        int dpH = metrics.heightPixels/density;
        int dpW = metrics.widthPixels/density;

        alto = dpH/5;
        contenedorJuego.setPadding(10,10,10,10);
        salir=nivel;


    }

    private void inputValues() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        //nivel = MenuJ.nivel;
        modo_juego = juegoC.getInt("modo",1);

    }

    private void turnos() {
        inicioJuego = (int) (Math.random()*2)+1;
        if (inicioJuego==1){

        }
    }

}
