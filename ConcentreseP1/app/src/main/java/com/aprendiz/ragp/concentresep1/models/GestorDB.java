package com.aprendiz.ragp.concentresep1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, "concentrese.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (JUGADOR TEXT, PUNTUACION TEXT, DIFICULTAD TEXT, MODO TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void inputData(Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("JUGADOR",score.getJugador());
        values.put("PUNTUACION",score.getPuntuacion());
        values.put("DIFICULTAD",score.getDifcultad());
        values.put("MODO",score.getModo());
        db.insert("SCORE",null,values);
        db.close();

    }

    public List<Score> scoreList(String modo, String dificultad){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE WHERE MODO='"+modo+"' AND DIFICULTAD='"+dificultad+"' ORDER BY PUNTUACION DESC;",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setJugador(cursor.getString(0));
                score.setPuntuacion(cursor.getString(1));
                score.setDifcultad(cursor.getString(2));
                score.setModo(cursor.getString(3));

                results.add(score);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return results;
    }


}
