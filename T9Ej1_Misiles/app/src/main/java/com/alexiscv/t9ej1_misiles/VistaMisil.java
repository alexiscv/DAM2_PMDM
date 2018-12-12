package com.alexiscv.t9ej1_misiles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

public class VistaMisil extends View {

    private Grafico misil;                            // misil
    private ThreadJuego thread = new ThreadJuego();    // Thread encargado de procesar el juego
    private static int PERIODO_PROCESO = 50;        // Cada cuanto queremos procesar cambios (ms)
    private long ultimoProceso = 0;                    // Cuando se realizó el último proceso

    public VistaMisil(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableMisil;
        drawableMisil = context.getResources().getDrawable(R.drawable.misil);

        misil = new Grafico(drawableMisil, this);
        //misil.setIncY(5);
        misil.setIncX(10);

    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int ancho_anter,
                                 int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
        misil.setPosX(10);
        misil.setPosY(50);

        ultimoProceso = System.currentTimeMillis();
        thread.start();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        misil.dibujaGrafico(canvas);
    }

    protected synchronized void actualizaFisica() {
        long ahora = System.currentTimeMillis();

        // No hagas nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }

        ultimoProceso = ahora;        // Para la próxima vez
        misil.incrementaPos();        // Actualizamos posiciones posX y posY
    }

    class ThreadJuego extends Thread {
        @Override
        public void run() {
            while (true) {
                actualizaFisica();
            }
        }
    }
}