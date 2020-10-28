package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final JCheckBox[] connectFields;
    private final Image image;
    private final Switch[] signals;
    private final Light light;


    public GateView(Gate gate) {

        super();
        light = new Light(255, 0, 0);

        connectFields = new JCheckBox[gate.getInputSize()];

        this.gate = gate;
        signals = new Switch[gate.getInputSize()];
        int d = 155 / (gate.getInputSize() + 1);

        for (int i = 0; i < gate.getInputSize(); i++) {
            signals[i] = new Switch();
            gate.connect(i, signals[i]);
            connectFields[i] = new JCheckBox();
            add(connectFields[i], 10, d * (i + 1) - 5);
            connectFields[i].addActionListener(this);
        }

        light.connect(0, gate);

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        update();
    }

    protected Component add(Component comp, int x, int y) {

        super.add(comp);

        comp.setBounds(x, y, 17, 17);

        return comp;
    }

    private void update() {

        for (int i = 0; i < gate.getInputSize(); i++) {
            if (connectFields[i].isSelected()) {
                signals[i].turnOn();
            } else {
                signals[i].turnOff();
            }
        }

        repaint();
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
        //256, 80
        //System.out.println(Math.sqrt(Math.pow(x-256,2)+Math.pow(y-80,2)));

        // Se o clique foi dentro do quadrado colorido...
        if (Math.sqrt(Math.pow(x - 256, 2) + Math.pow(y - 80, 2)) <= 6 && light.getColor() != Color.black) {

            // ...então abrimos a janela seletora de cor...
            light.setColor(JColorChooser.showDialog(this, null, light.getColor()));

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

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 15, 15, 246, 130, this);
        //g.drawOval(250, 75, 10, 10);
        g.setColor(light.getColor());
        g.fillOval(250, 74, 12, 12);


        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}


