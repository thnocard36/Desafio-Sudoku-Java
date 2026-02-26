package br.com.thnocard.dio.util.ui.custom.buttons.panel;

import br.com.thnocard.dio.util.ui.custom.buttons.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static java.awt.Color.black;

public class SudokuSecta extends JPanel {

    public SudokuSecta(final List<NumberText> textFields) {
        var dimensional = new Dimension(170, 170);
        this.setSize(dimensional);
        this.setPreferredSize(dimensional);
        this.setBorder(new LineBorder(black, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}