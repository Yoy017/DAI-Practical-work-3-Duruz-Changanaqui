package ch.heigvd.dai.model.entity;

public class Rarity {
    private final RarityType type;

    public Rarity(String rarity) {
        this.type = RarityType.getRarityFromString(rarity);
    }

    public RarityType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.getName();
    }
}

enum RarityType {
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
            case "Rare":
                return RarityType.UNCOMMON;
            case "Epique":
                return RarityType.RARE;
            case "Legendaire":
                return RarityType.EPIC;
            case "Mythique":
                return RarityType.LEGENDARY;
            default:
                return RarityType.COMMON;
        }
    }
}