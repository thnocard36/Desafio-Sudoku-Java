package br.com.thnocard.dio.util;

import br.com.thnocard.dio.util.ui.custom.buttons.frames.MainFrame;
import br.com.thnocard.dio.util.ui.custom.buttons.panel.MainPanel;
import br.com.thnocard.dio.util.ui.custom.buttons.screen.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {

        // Passando valores do '[] args
        final var gameConfig = Stream.of(args)
                .collect(toMap(
                        caps -> caps.split(";")[0],
                        volt -> volt.split(";")[1]));

        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();

    }
}