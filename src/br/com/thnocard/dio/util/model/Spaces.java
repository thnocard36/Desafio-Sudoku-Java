package br.com.thnocard.dio.util.model;

public class Spaces {

    private Integer actualStage;
    private final int expectedStage;
    private final boolean fixedStage;

    // Um construtor argumentativo
    public Spaces(int expectedStage, boolean fixedStage) {
        this.expectedStage = expectedStage;
        this.fixedStage = fixedStage;
        if(fixedStage) {
            actualStage = expectedStage;
        }
    }

    public Integer getActualStage() {
        return actualStage;
    }

    public void setActualStage(Integer actualStage) {
        if(fixedStage) return;
        this.actualStage = actualStage;
    }

    public void clearSpaces() {
        setActualStage(null);
    }

    public int getExpectedStage() {
        return expectedStage;
    }

    public boolean isFixedStage() {
        return fixedStage;
    }

    public boolean isFixed() {
        return false;
    }
}