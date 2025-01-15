package ch.heigvd.dai.model.entity;

public class Item {
    protected int id;
    protected final String name, description;
    protected int quantity;
    protected final ItemType type;
    //protected SlotType slotType = SlotType.OTHER;
    protected Rarity rarity;
    protected final double levelRequired;

    public Item(int id, String name, String description, int quantity, String type, String rarity, double levelRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.type = ItemType.getTypeFromString(type);
        this.rarity = new Rarity(rarity);
        this.levelRequired = levelRequired;
    }

    public Item(int id, String name, String type, String rarity) {
        this(id, name, null, 1, type, rarity, 0);
    }

    public Item(int id, String name, String type, String rarity, double levelRequired) {
        this(id, name, null, 1, type, rarity, levelRequired);
    }

    public Item(int id, String name, String description, String type, String rarity, double levelRequired) {
        this(id, name, description, 1, type, rarity, levelRequired);
    }

    public Item(int id, String name, String description, String type, String rarity) {
        this(id, name, description, 1, type, rarity, 0);
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

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }
}