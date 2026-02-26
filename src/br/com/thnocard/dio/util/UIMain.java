package br.com.thnocard.dio.util;

import br.com.thnocard.dio.util.ui.custom.buttons.frames.MainFrame;
import br.com.thnocard.dio.util.ui.custom.buttons.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UIMain {

    public static void main(String[] args) {
        var dimension = new Dimension(800, 600);
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

}