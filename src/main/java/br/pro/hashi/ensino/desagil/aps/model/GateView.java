package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GateView extends JPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final JCheckBox connect0Field;
    private final JCheckBox connect1Field;
    private final JCheckBox saidaField;

    private Color color;

    public GateView(Gate gate) {

        setLayout(null);
        setPreferredSize(new Dimension(200, 130));

        this.gate = gate;

        connect0Field = new JCheckBox();
        connect1Field = new JCheckBox();
        saidaField = new JCheckBox();

        JLabel connect0Label = new JLabel("Entrada:");
        //JLabel connect1Field = new JLabel("1");
        JLabel saidaLabel = new JLabel("Saida:");

        add(connect0Label, 10, 10, 75, 25);
        add(connect0Field, 10, 35, 150, 25);
        if (gate.getInputSize() > 1) {
            add(connect1Field, 10, 55, 150, 25);
        }
        add(saidaLabel, 10, 85, 75, 25);
        add(saidaField, 10, 105, 120, 25);

        // Inicializamos o atributo de cor simplesmente como preto.
        color = Color.BLACK;


        connect0Field.addActionListener(this);
        connect1Field.addActionListener(this);


        saidaField.setEnabled(false);

        addMouseListener(this);

        update();
    }

    protected Component add(Component comp, int x, int y, int width, int height) {

        super.add(comp);

        comp.setBounds(x, y, width, height);

        return comp;
    }

    private void update() {
        Switch signal0 = new Switch();
        Switch signal1 = new Switch();

        if (connect0Field.isSelected()) {
            signal0.turnOn();
        } else {
            signal0.turnOff();
        }
        gate.connect(0, signal0);
        if (gate.getInputSize() > 1) {
            if (connect1Field.isSelected()) {
                signal1.turnOn();
            } else {
                signal1.turnOff();
            }
            gate.connect(1, signal1);
        }

        boolean result = gate.read();


        saidaField.setSelected(result);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }
}


