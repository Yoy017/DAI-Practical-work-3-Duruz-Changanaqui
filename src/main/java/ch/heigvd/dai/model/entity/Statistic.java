package ch.heigvd.dai.model.entity;

import java.util.LinkedList;

public class Statistic {
    private final int id;
    private final LinkedList<StatisticType> statisticTypes;

    {
        statisticTypes = new LinkedList<>();
        statisticTypes.add(StatisticType.INTELLIGENCE);
        statisticTypes.add(StatisticType.AGILITY);
        statisticTypes.add(StatisticType.STRENGTH);
        statisticTypes.add(StatisticType.STAMINA);
    }

    public Statistic(int id, int intelligence, int agility, int strength, int stamina) {
        this.id = id;
        statisticTypes.get(0).setValue(intelligence);
        statisticTypes.get(1).setValue(agility);
        statisticTypes.get(2).setValue(strength);
        statisticTypes.get(3).setValue(stamina);
    }

    public LinkedList<StatisticType> getStatisticTypes() {
        return (LinkedList<StatisticType>) statisticTypes.clone();
    }

    public void combine(Statistic statistic) {
        for (int i = 0; i < statisticTypes.size(); i++) {
            statisticTypes.get(i).setValue(statisticTypes.get(i).getValue() + statistic.getStatisticTypes().get(i).getValue());
        }
    }
}