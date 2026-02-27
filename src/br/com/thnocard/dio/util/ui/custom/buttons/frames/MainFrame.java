package br.com.thnocard.dio.util.ui.custom.buttons.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel) {
        super("Sudoko 2025"); // Titulo
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(mainPanel);
    }

}