package br.com.thnocard.dio.util.ui.custom.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {

    public ResetButton(final ActionListener actionListener) {
        this.setText("Reiniciar jogo");
        this.addActionListener(actionListener);
    }

}