package br.pro.hashi.ensino.desagil.aps.model;

import java.awt.*;

public class Light implements Receiver {
    private Color color;
    private Emitter emitter;

    public Light(int r, int g, int b) {
        color = new Color(r, g, b);
        emitter = null;
    }

    public Color getColor() {
        if (emitter != null && emitter.read()) {
            return color;
        }
        return Color.BLACK;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        this.emitter = emitter;
    }
}
