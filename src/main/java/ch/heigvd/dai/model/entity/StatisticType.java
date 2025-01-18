package ch.heigvd.dai.model.entity;

public enum StatisticType {
    INTELLIGENCE(0),
    AGILITY(0),
    STRENGTH(0),
    STAMINA(0);

    protected int value;

    StatisticType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}