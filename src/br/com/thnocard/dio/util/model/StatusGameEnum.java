package br.com.thnocard.dio.util.model;

public enum StatusGameEnum {

    NON_STARTED_MATCH("Partida não iniciada"),
    INCOMPLETE_MATCH("Partida incompleta"),
    COMPLETE_MATCH("Partida finalizada");

    private String lables;

    StatusGameEnum(final String label) {
        this.lables = label;
    }

    public String getLables() {
        return lables;
    }

}