package com.alexiscv.t9ej1_misiles;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Grafico {

    private Drawable drawable;  // Imagen a dibujar
    private double posX, posY;  // Posición del gráfico (en nuestro caso misil)
    private double incX, incY;  // Incrementos de velocidad
    private int ancho, alto;    // Dimensiones del gráfico (misil)
    private View view;          // Vista

    /**
     * Constructor
     *
     * @param drawable
     * @param view
     */
    public Grafico(Drawable drawable, View view) {
        this.drawable = drawable;   // Imagen a pintar
        this.ancho = drawable.getIntrinsicHeight() + 50;  // Ancho de la imagen
        this.alto = drawable.getIntrinsicWidth() + 50;    // Alto de la imagen
        this.view = view;
    }

    public void dibujaGrafico(Canvas canvas) {
        // Indicamos donde empieza a dibujar la imagen posX, posY y donde acaba de
        // dibujarla posX+ancho, posY+alto. Creando así el tamaño de la imagen.
        drawable.setBounds((int) posX, (int) posY, (int) posX + ancho, (int) posY + alto);

        // Dibujamos en el canvas
        drawable.draw(canvas);
        view.invalidate(); // Llamamos al método invalidate() para refrescar la interfaz
    }

    public void incrementaPos() {
        // En nuestro caso el misil se desplaza hacia la derecha siempre en una
        // posiciónY fija, de forma que cuando llega al final derecho de la pantalla
        // tendremos que decir que vuelva a la posición inicial de la derecha pero
        // en una posiciónY aleatoria en caso contrario tiene que incrementarse un valor
        // constante fijo -50dps- en nuestro caso
        // view.getWidth() y view.getHeight() indicando el ancho y alto de la pantalla
        // donde va a moverse el misil
        if ((posX < 0) || (posX < view.getHeight())) {
            posX = 1;
            posY = Math.random() * view.getHeight() - alto;
        }
        posX += incX;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
