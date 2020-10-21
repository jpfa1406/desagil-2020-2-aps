package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.util.LinkedList;

public class APS {
    public static void main(String[] args) {
        LinkedList<Gate> model = new LinkedList<>();
        model.add(new XorGate());
        model.add(new AndGate());
        model.add(new NandGate());
        model.add(new OrGate());
        model.add(new NotGate());

        View view = new View(model);

        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(view);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
