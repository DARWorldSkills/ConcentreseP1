package com.aprendiz.ragp.concentresep1.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aprendiz.ragp.concentresep1.R;

public class MenuJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_j);
    }

    public void jugar(View view) {
        Intent intent = new Intent(MenuJ.this,Juego.class);
        startActivity(intent);

    }
}
