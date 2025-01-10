package ch.heigvd.dai.model.entity;

public class Item {
    protected final String name, description;
    protected final ItemType type;
    protected Rarity rarity;
    protected final double levelRequired;

    public Item(String name, String type, String rarity) {
        this.name = name;
        this.description = null;
        this.type = ItemType.getTypeFromString(type);
        this.rarity = new Rarity(rarity);
        this.levelRequired = 0.;
    }

    public Item(String name, String type, String rarity, double levelRequired) {
        this.name = name;
        this.description = null;
        this.type = ItemType.getTypeFromString(type);
        this.rarity = new Rarity(rarity);
        this.levelRequired = levelRequired;
    }

    public Item(String name, String description, String type, String rarity, double levelRequired) {
        this.name = name;
        this.description = description;
        this.type = ItemType.getTypeFromString(type);
        this.rarity = new Rarity(rarity);
        this.levelRequired = levelRequired;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public double getLevelRequired() {
        return levelRequired;
    }
}

enum ItemType {
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
}