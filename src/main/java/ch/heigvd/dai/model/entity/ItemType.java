package ch.heigvd.dai.model.entity;

public enum ItemType {
    WEAPON, ARMOR, CONSUMABLE, RESSOURCE, OTHER;

    protected static ItemType getTypeFromString(String type) {
        switch (type) {
            case "Arme":
                return ItemType.WEAPON;
            case "Armure":
                return ItemType.ARMOR;
            case "Consommable":
                return ItemType.CONSUMABLE;
            case "Ressource":
                return ItemType.RESSOURCE;
            default:
                return ItemType.OTHER;
        }
    }

    public boolean equals(ItemType type) {
        return this == type;
    }
}
