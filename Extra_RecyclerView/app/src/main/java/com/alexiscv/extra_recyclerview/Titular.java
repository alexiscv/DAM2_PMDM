package com.alexiscv.extra_recyclerview;

public class Titular {

    private String titulo;
    private String Subtitulo;

    public Titular(String titulo, String subtitulo) {
        this.titulo = titulo;
        Subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return Subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        Subtitulo = subtitulo;
    }
}
