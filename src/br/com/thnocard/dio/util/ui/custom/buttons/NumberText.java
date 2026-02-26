package br.com.thnocard.dio.util.ui.custom.buttons;

import br.com.thnocard.dio.util.model.Spaces;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NumberText extends JTextField {

    private final Spaces spaces;

    public NumberText(final Spaces spaces) {
        this.spaces = spaces;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimiter());
        this.setEnabled(!spaces.isFixed());

        if(spaces.isFixed()) {
            this.setText(spaces.getActualStage().toString());
        }

        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpaces();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpaces();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpaces();
            }

            private void changeSpaces() {
                if(getText().isEmpty()) {
                    spaces.clearSpaces();
                    return;
                }
            }

        });

    }
}