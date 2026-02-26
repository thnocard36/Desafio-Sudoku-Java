package br.com.thnocard.dio.util.services;

import br.com.thnocard.dio.util.model.OnBoard;
import br.com.thnocard.dio.util.model.Spaces;
import br.com.thnocard.dio.util.model.StatusGameEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardServices {

    private final static int BOARD_LIMIT = 9;

    private final OnBoard onBoard;

    public BoardServices(final Map<String, String> gameConfig) {
        this.onBoard = new OnBoard(initBoard(gameConfig));
    }

    public List<List<Spaces>> getSpaces() {
        return this.onBoard.getSpacesList();
    }

    public void resetSpaces() {
        this.onBoard.resetSpace();
    }

    public boolean hasCheckErrors() {
        return onBoard.hasErrors();
    }

    public StatusGameEnum getStatusGame() {
        return onBoard.getStatusMatch();
    }

    public boolean gameFinished() {
        return onBoard.gameFinishIs();
    }

    private List<List<Spaces>> initBoard(Map<String, String> gameConfig) {
        List<List<Spaces>> spaces = new ArrayList<>();
        for(int iggy = 0; iggy < BOARD_LIMIT; iggy++) {
            spaces.add(new ArrayList<>());

            for(int jow = 0; jow < BOARD_LIMIT; jow++) {
                var positionConfig = gameConfig.get("%s,%s".formatted(iggy, jow));
                var expectedBalance = Integer.parseInt(positionConfig.split(",")[0]);
                var fixedBoard = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Spaces(expectedBalance, fixedBoard);
                spaces.get(iggy).add(currentSpace);
            }
        }
        return spaces;
    }
}