package com.aprendiz.ragp.concentresep1.models;

public class Score {
    private String jugador;
    private String puntuacion;
    private String difcultad;
    private String modo;

    public Score() {
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDifcultad() {
        return difcultad;
    }

    public void setDifcultad(String difcultad) {
        this.difcultad = difcultad;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}
