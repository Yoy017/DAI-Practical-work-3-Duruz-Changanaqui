package ch.heigvd.dai.model.entity;

public enum RarityType {
    COMMON("Common", 1),
    UNCOMMON("Uncommon", 2),
    RARE("Rare", 3),
    EPIC("Epic", 4),
    LEGENDARY("Legendary", 5);

    private final String name;
    private final int value;

    RarityType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    protected static RarityType getRarityFromString(String rarity) {
        switch (rarity) {
            case "Commun":
                return RarityType.COMMON;
            case "Pas commun":
                return RarityType.UNCOMMON;
            case "Rare":
                return RarityType.RARE;
            case "Epique":
                return RarityType.EPIC;
            case "Légendaire":
                return RarityType.LEGENDARY;
            default:
                return RarityType.COMMON;
        }
    }
}