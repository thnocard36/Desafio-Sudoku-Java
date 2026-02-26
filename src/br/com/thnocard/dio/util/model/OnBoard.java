package br.com.thnocard.dio.util.model;

import java.util.Collection;
import java.util.List;

import static br.com.thnocard.dio.util.model.StatusGameEnum.COMPLETE_MATCH;
import static br.com.thnocard.dio.util.model.StatusGameEnum.INCOMPLETE_MATCH;
import static br.com.thnocard.dio.util.model.StatusGameEnum.NON_STARTED_MATCH;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OnBoard {

    private final List<List<Spaces>> spacesList;

    public OnBoard(final List<List<Spaces>> spacesList) {
        this.spacesList = spacesList;
    }

    public List<List<Spaces>> getSpacesList() {
        return spacesList;
    }

    public StatusGameEnum getStatusMatch() {

        if(spacesList.stream().flatMap(Collection::stream)
                .noneMatch(slet -> !slet.isFixed() && nonNull(slet.getActualStage()))) {
            return NON_STARTED_MATCH;
        }

        return spacesList.stream().flatMap(Collection::stream)
                .anyMatch(spaces -> isNull(spaces.getActualStage())) ? INCOMPLETE_MATCH : COMPLETE_MATCH;

    }

    public boolean hasErrors() {
        if(getStatusMatch() == NON_STARTED_MATCH) {
            return false;
        }

        return spacesList.stream().flatMap(Collection::stream)
                .anyMatch(slet -> nonNull(slet.getActualStage()) && !slet.getActualStage()
                        .equals(slet.getExpectedStage()));
    }

    public boolean changeValues(final int col, final int row, final Integer value) {
        var spaces = spacesList.get(col).get(row);
        if(spaces.isFixed()) {
            return false;
        }

        spaces.setActualStage(value);
        return true;
    }

    public boolean clearValues(final int col, final int row) {
        var spaces = spacesList.get(col).get(row);
        if(spaces.isFixed()) {
            return false;
        }

        spaces.clearSpaces();
        return true;
    }

    public void resetSpace() {
        spacesList.forEach(coht -> coht.forEach(Spaces::clearSpaces));
    }

    public boolean gameFinishIs() {
        return !hasErrors() && getStatusMatch().equals(COMPLETE_MATCH);
    }

}