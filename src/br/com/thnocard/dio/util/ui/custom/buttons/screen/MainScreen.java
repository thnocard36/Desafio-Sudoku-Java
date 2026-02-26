package br.com.thnocard.dio.util.ui.custom.buttons.screen;

import br.com.thnocard.dio.util.services.BoardServices;
import br.com.thnocard.dio.util.ui.custom.buttons.CheckGameStatus;
import br.com.thnocard.dio.util.ui.custom.buttons.FinishGameButton;
import br.com.thnocard.dio.util.ui.custom.buttons.ResetButton;
import br.com.thnocard.dio.util.ui.custom.buttons.frames.MainFrame;
import br.com.thnocard.dio.util.ui.custom.buttons.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MainScreen {

    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardServices boardServices;

    private JButton finishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetGameButton;


    public MainScreen(final Map<String, String> gameConfig) {
        this.boardServices = new BoardServices(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        addResetButton(mainPanel);
        addCheckGameStatus(mainPanel);
        addFinishGameButton(mainPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new FinishGameButton(exper -> {
            if(boardServices.gameFinished()) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você concluiu o jogo.");
                resetGameButton.setEnabled(false);
                finishGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
            } else {
                var messageWarning = "Seu jogo possui erros, por favor verificar.";
                JOptionPane.showMessageDialog(null, messageWarning);
            }
            });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatus(final JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatus(exper -> {
            var hasCheckerErros = boardServices.hasCheckErrors();
            var gameStatus = boardServices.getStatusGame();
            var messageS = switch(gameStatus) {
                case NON_STARTED_MATCH -> "O Jogo não foi iniciado!";
                case INCOMPLETE_MATCH ->  "O Jogo está incompleto!";
                case COMPLETE_MATCH -> "O Jogo está completo!";
            };
            messageS += hasCheckerErros ? " e contem erros " : " e não tem erros";
            JOptionPane.showMessageDialog(null, messageS);
        }

        );
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(final JPanel mainPanel) {
       resetGameButton = new ResetButton(elememt -> {
            var dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar a partida?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if(dialogResult == 0) {
                boardServices.resetSpaces();
            }
        });
        mainPanel.add(resetGameButton);
    }

}