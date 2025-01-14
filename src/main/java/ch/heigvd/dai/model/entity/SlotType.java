package ch.heigvd.dai.model.entity;

public enum SlotType {
    BAG, EQUIPMENT;

    protected static SlotType getTypeFromString(String type) {
        switch (type) {
            case "Bag":
                return SlotType.BAG;
            case "Equipement":
                return SlotType.EQUIPMENT;
            default:
                return SlotType.BAG;
        }
    }

    public boolean equals(SlotType type) {
        return this == type;
    }
}