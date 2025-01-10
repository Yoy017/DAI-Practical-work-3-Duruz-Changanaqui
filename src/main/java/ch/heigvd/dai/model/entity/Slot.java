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

enum SlotType {
    BAG, EQUIPMENT;

    protected static SlotType getTypeFromString(String type) {
        switch (type) {
            case "Sac":
                return SlotType.BAG;
            case "Equipement":
                return SlotType.EQUIPMENT;
            default:
                return SlotType.BAG;
        }
    }
}