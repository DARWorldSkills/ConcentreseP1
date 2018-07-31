package com.aprendiz.ragp.concentresep1.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, "concentrese.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (JUGADOR TEXT, PUNTUACION TEXT, DIFICULTAD TEXT, MODO TEXT)");;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void inputData(){
        SQLiteDatabase db = getWritableDatabase();
        
    }


}
