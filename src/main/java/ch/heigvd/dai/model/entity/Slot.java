package ch.heigvd.dai.model.entity;

public class Slot {
    SlotType type;
    Item item;

    public Slot(String type) {
        this.type = SlotType.getTypeFromString(type);
        this.item = null;
    }

    public Slot(String type, Item item) {
        this.type = SlotType.getTypeFromString(type);
        this.item = item;
    }

    public SlotType getType() {
        return type;
    }

    public Item getItem() {
        return item;
    }
}