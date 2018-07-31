package com.aprendiz.ragp.concentresep1.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.aprendiz.ragp.concentresep1.R;

public class Splash extends AppCompatActivity {
    public static String [] nombres = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CountDownTimer countDownTimer = new CountDownTimer(1000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.item_inicio,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
                final EditText txtNombre1 = view.findViewById(R.id.txtJugador1I);
                final EditText txtNombre2 = view.findViewById(R.id.txtJugador2I);
                builder.setTitle("Jugadores");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nombres[0] = txtNombre1.getText().toString();
                        nombres[1] = txtNombre2.getText().toString();
                        Intent intent = new Intent(Splash.this, MenuJ.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setCancelable(false);
                builder.setView(view);
                builder.show();


            }
        }.start();





    }


}
