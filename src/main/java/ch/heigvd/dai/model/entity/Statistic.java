package ch.heigvd.dai.model.entity;

import java.util.LinkedList;

public class Statistic {
    private final int id;
    private final LinkedList<StatisticType> statisticTypes = new LinkedList<>();

    public Statistic(int id) {
        this.id = id;
        statisticTypes.add(StatisticType.STRENGTH);
        statisticTypes.add(StatisticType.AGILITY);
        statisticTypes.add(StatisticType.INTELLIGENCE);
        statisticTypes.add(StatisticType.HEALTH);
        statisticTypes.add(StatisticType.MANA);
    }

    public void addStatisticType(StatisticType statisticType) {
        statisticTypes.add(statisticType);
    }

    public LinkedList<StatisticType> getStatisticTypes() {
        return statisticTypes;
    }

    public void setStatLevel(StatisticType statisticType, int value) {
        statisticTypes.get(statisticTypes.indexOf(statisticType)).setValue(value);
    }
}

enum StatisticType {
    STRENGTH("Strength", 0),
    AGILITY("Agility", 0),
    INTELLIGENCE("Intelligence", 0),
    HEALTH("Health", 0),
    MANA("Mana", 0);

    private String name;
    private int value;

    StatisticType(String name, int value) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }
}