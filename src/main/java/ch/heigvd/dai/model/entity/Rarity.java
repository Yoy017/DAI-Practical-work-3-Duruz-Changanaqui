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