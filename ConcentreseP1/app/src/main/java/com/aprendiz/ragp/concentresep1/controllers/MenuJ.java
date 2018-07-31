package com.aprendiz.ragp.concentresep1.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.aprendiz.ragp.concentresep1.R;

public class MenuJ extends AppCompatActivity {
    public static int nivel=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_j);
    }

    public void jugar(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dificultad,null);
        final RadioButton rbtnFacil = v.findViewById(R.id.rbtnFacil);
        final RadioButton rbtnMedio = v.findViewById(R.id.rbtnMedio);
        final RadioButton rbtnDificil = v.findViewById(R.id.rbtnDificil);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setTitle("Seleccione Dificultad");
        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (rbtnFacil.isChecked()){
                    nivel=4;
                }

                if (rbtnMedio.isChecked()){
                    nivel=6;
                }

                if (rbtnDificil.isChecked()){
                    nivel=8;
                }
                Intent intent = new Intent(MenuJ.this,Juego.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();



    }
}
