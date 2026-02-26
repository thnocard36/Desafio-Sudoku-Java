package br.com.thnocard.dio.util.ui.custom.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CheckGameStatus extends JButton {

    public CheckGameStatus(final ActionListener actionListener) {
        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }

}