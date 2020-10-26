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

    private final JCheckBox connect0Field;
    private final JCheckBox connect1Field;
    private final JRadioButton saidaField;
    private final Image image;
    private Color color;

    public GateView(Gate gate) {

        super();

        this.gate = gate;

        connect0Field = new JCheckBox();
        connect1Field = new JCheckBox();
        saidaField = new JRadioButton();

        //JLabel connect0Label = new JLabel();
        //JLabel connect1Field = new JLabel("1");
        //JLabel saidaLabel = new JLabel();

        //add(connect0Label, 10, 10, 18, 18);
        add(connect0Field, 10, 73);
        if (gate.getInputSize() > 1) {
            add(connect1Field, 10, 109);
            add(connect0Field, 10, 36);
        }
        //add(saidaLabel, 10, 85, 18, 18);
        add(saidaField, 243, 73);

        // Inicializamos o atributo de cor simplesmente como preto.
        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        connect0Field.addActionListener(this);
        connect1Field.addActionListener(this);


        saidaField.setEnabled(false);

        addMouseListener(this);

        update();
    }

    protected Component add(Component comp, int x, int y) {

        super.add(comp);

        comp.setBounds(x, y, 17, 17);

        return comp;
    }

    private void update() {
        Switch signal0 = new Switch();
        Switch signal1 = new Switch();
        Light light = new Light(255, 0, 0);

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

        light.connect(0, gate);
        light.setColor(light.getColor());
        saidaField.setForeground(light.getColor());
        saidaField.setBackground(light.getColor());
        add(saidaField);

        boolean result = gate.read();
        saidaField.setSelected(!result);
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
        if (x >= 243 && x < 250 && y >= 73 && y < 80) {

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

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 15, 15, 246, 131, this);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}


