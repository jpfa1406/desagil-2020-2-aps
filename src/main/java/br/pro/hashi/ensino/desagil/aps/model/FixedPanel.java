package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;

public class FixedPanel extends JPanel {
    protected FixedPanel() {

        setLayout(null);

        setPreferredSize(new Dimension(276, 163));
    }

    protected Component add(Component comp, int x, int y) {

        super.add(comp);

        comp.setBounds(x, y, 17, 17);

        return comp;
    }
}
