package com.aprendiz.ragp.concentresep1.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.concentresep1.R;
import com.aprendiz.ragp.concentresep1.models.AdapterJ;
import com.aprendiz.ragp.concentresep1.models.GestorDB;
import com.aprendiz.ragp.concentresep1.models.Score;

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

    private int fondoJuego = R.drawable.juego;
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
    int [] segundos={0,0};
    SharedPreferences juegoC;

    MediaPlayer up1;
    MediaPlayer pipe;
    MediaPlayer finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        inputValues();
        obtenerMedidas();
        turnos();
        go_game();
        AdapterJ adapterJ = new AdapterJ(imagenesFondo,alto,ancho,this);
        contenedorJuego.setAdapter(adapterJ);
        contenedorJuego.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                canselect++;
                ImageView item = (ImageView) view;
                if (pos1==position || pos2==position){
                    canselect--;
                }

                if (canselect==1){
                    pos1=position;
                    imagen1=item;
                }


                if (canselect==2){
                    pos2=position;
                    imagen2=item;
                }

                mostrarImagen(item, position);

            }
        });
    }

    private void mostrarImagen(ImageView item, int position) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize=3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imagenesAleatorias[position],op);
        item.setImageBitmap(bitmap);
        if (canselect==2){
            movimientos++;
            if (modo_juego==2){
                txtTiempo.setText("Movimientos: "+movimientos);
            }
            canselect=0;

            new ValidarJuego().execute();
        }


    }

    public class ValidarJuego extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            contenedorJuego.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (inicioJuego==1){
                txtJugador1.setTextColor(getColor(R.color.colorVerde));
                txtJugador2.setTextColor(getColor(R.color.colorGris));
                inicioJuego=2;
            }else {

                if (inicioJuego == 2) {
                    txtJugador2.setTextColor(getColor(R.color.colorVerde));
                    txtJugador1.setTextColor(getColor(R.color.colorGris));
                    inicioJuego = 1;
                }
            }

            if (imagenesAleatorias[pos1]==imagenesAleatorias[pos2]) {
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imagen1 = null;
                imagen2 = null;

                up1.start();

                if (inicioJuego == 2) {
                    puntuacionJ2 += 100;
                    txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
                }

                if (inicioJuego == 1) {
                    puntuacionJ1 += 100;
                    txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
                }
                salir--;

                if (salir == 0) {
                    bandera = false;
                    finish.start();

                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.item_resumen,null);
                    TextView txtJ1 = view.findViewById(R.id.txtJ1R);
                    TextView txtJ2 = view.findViewById(R.id.txtJ2R);
                    TextView txtP1 = view.findViewById(R.id.txtP1R);
                    TextView txtP2 = view.findViewById(R.id.txtP2R);
                    TextView txtResultado = view.findViewById(R.id.txtResultado);
                    ImageButton imgT = view.findViewById(R.id.imgTwitter);
                    ImageButton imgF = view.findViewById(R.id.imgFacebook);
                    CardView cardView = view.findViewById(R.id.btnContinuarR);
                    txtJ1.setText(txtJugador1.getText());
                    txtJ2.setText(txtJugador2.getText());
                    txtP1.setText(txtPuntuacion1.getText());
                    txtP2.setText(txtPuntuacion2.getText());
                    txtResultado.setText(txtTiempo.getText());
                    AlertDialog.Builder builder = new AlertDialog.Builder(Juego.this);
                    builder.setView(view);
                    builder.setCancelable(false);
                    builder.show();
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                    GestorDB gestorDB = new GestorDB(Juego.this);
                    final Score jugador1 = new Score();
                    final Score jugador2 = new Score();
                    jugador1.setJugador(txtJugador1.getText().toString());
                    jugador2.setJugador(txtJugador2.getText().toString());
                    jugador1.setPuntuacion(txtPuntuacion1.getText().toString());
                    jugador2.setPuntuacion(txtPuntuacion2.getText().toString());
                    jugador1.setModo(Integer.toString(modo_juego));
                    jugador1.setDifcultad(Integer.toString(nivel));
                    jugador2.setModo(Integer.toString(modo_juego));
                    jugador2.setDifcultad(Integer.toString(nivel));
                    gestorDB.inputData(jugador1);
                    gestorDB.inputData(jugador2);
                    imgT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            String string = jugador1.getJugador()+" "+jugador1.getPuntuacion()+"\n"+
                                    jugador2.getJugador()+" "+jugador2.getPuntuacion()+"\n"+
                                    "nivel: "+nivel+" modo de juego "+modo_juego;
                            intent.setPackage("com.twitter.android");
                            intent.putExtra(Intent.EXTRA_TEXT, string);
                            try {

                                startActivity(intent);

                            }catch (Exception e){
                                Toast.makeText(Juego.this, "No cuentas con esta app", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                }

            }

            if (imagen1!=null && imagen2!=null){
                BitmapFactory.Options op = new BitmapFactory.Options();
                op.inSampleSize=3;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),fondoJuego,op);
                imagen1.setImageBitmap(bitmap);
                imagen2.setImageBitmap(bitmap);

                if (inicioJuego == 2) {
                    puntuacionJ2 -= 1;
                    txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
                }

                if (inicioJuego == 1) {
                    puntuacionJ1 -= 1;
                    txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
                }

                pipe.start();

            }

            canselect=0;
            pos1=-1;
            pos2=-1;
            contenedorJuego.setEnabled(true);


        }
    }


    private void inizialite() {
        txtJugador1 = findViewById(R.id.txtJugador1J);
        txtJugador2 = findViewById(R.id.txtJugador2J);
        txtPuntuacion1 = findViewById(R.id.txtPuntuacion1J);
        txtPuntuacion2 = findViewById(R.id.txtPuntuacion2J);
        txtTiempo = findViewById(R.id.txtTiempoJ);
        contenedorJuego = findViewById(R.id.contenedorJuego);
        pTiempo = findViewById(R.id.progressBar);
        up1 = MediaPlayer.create(this,R.raw.smb_up);
        pipe = MediaPlayer.create(this,R.raw.smb_pipe);
        finish = MediaPlayer.create(this,R.raw.smb_stage_clear);
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

        if (nivel==4){
            ancho=dpW/3;
            contenedorJuego.setNumColumns(2);
        }

        if (nivel==6){
            ancho=dpW/4;
            contenedorJuego.setNumColumns(3);
        }

        if (nivel==8){
            ancho=dpW/5;
            contenedorJuego.setNumColumns(4);
        }

        alto+=40;
        ancho+=40;


    }

    private void inputValues() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        nivel = MenuJ.nivel;
        modo_juego = juegoC.getInt("modo",1);
        txtJugador1.setText(Splash.nombres[0]);
        txtJugador2.setText(Splash.nombres[1]);
        pTiempo.setMax(60);
        bandera=true;
    }

    private void turnos() {
        inicioJuego = (int) (Math.random()*2)+1;
        if (inicioJuego==1){
            txtJugador1.setTextColor(getColor(R.color.colorVerde));
            txtJugador2.setTextColor(getColor(R.color.colorGris));
            Toast.makeText(this, "Inicia Jugador 1", Toast.LENGTH_SHORT).show();
            txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
            txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
        }

        if (inicioJuego==2){
            txtJugador2.setTextColor(getColor(R.color.colorVerde));
            txtJugador1.setTextColor(getColor(R.color.colorGris));
            Toast.makeText(this, "Inicia Jugador 2", Toast.LENGTH_SHORT).show();
            txtPuntuacion1.setText(Integer.toString(puntuacionJ1));
            txtPuntuacion2.setText(Integer.toString(puntuacionJ2));
        }
    }


    private void go_game() {
        generarFondo();
        generarSelec();
        generarAleatorias();
        chronometer();
    }

    private void generarFondo() {
        imagenesFondo = new int[nivel*2];
        for (int i=0; i<nivel*2; i++){
            imagenesFondo[i] = fondoJuego;
        }
    }

    private void generarSelec() {
        imagenesSelect = new ArrayList<>();
        for (int i=0; i<nivel; i++){
            int tmp = (int) (Math.random() * nivel);
            if (!imagenesSelect.contains(imagenesJuego[tmp])){
                imagenesSelect.add(imagenesJuego[tmp]);
            }else {
                i--;
            }
        }
    }

    private void generarAleatorias() {
        imagenesAleatorias = new int[nivel*2];
        for (int i=0;i<nivel;i++){
            int tmp = 0;
            do {
                int valor = (int) (Math.random() * nivel*2);
                if (imagenesAleatorias[valor]==0){
                    imagenesAleatorias[valor]=imagenesSelect.get(i);
                    tmp++;
                }

            }while (tmp<2);
        }
    }

    private void chronometer() {
        if (modo_juego==1){
            txtTiempo.setText("Tiempo: "+segundos[1]);
        }else {
            txtTiempo.setText("Movimientos: "+movimientos);
        }


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            segundos[0]++;
                            segundos[1]++;

                            pTiempo.setProgress(segundos[0]);

                            if (segundos[0] == 60) {
                                segundos[0] = 0;
                            }

                            if (modo_juego == 1) {
                                txtTiempo.setText("Tiempo: " + segundos[1]);
                            }


                        }
                    });
                }
            }
        });
        thread.start();
    }

}
