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

    public OnBoard(List<List<Spaces>> spacesList) {
        this.spacesList = spacesList;
    }

    public List<List<Spaces>> getSpacesList() {
        return spacesList;
    }

    public StatusGameEnum getStatusMatch() {

        if(spacesList.stream().flatMap(Collection::stream)
                .noneMatch(spaces -> !spaces.isFixed() && nonNull(spaces.getActualStage()))) {
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
                .anyMatch(spaces -> nonNull(spaces.getActualStage()) && !spaces.getActualStage().equals(spaces.getExpectedStage()));
    }

}