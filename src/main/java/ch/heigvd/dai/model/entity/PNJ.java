package ch.heigvd.dai.model.entity;

public class PNJ extends Character {
    private Type type;

    public PNJ(String name, Type type) {
        super(name);
        this.type = type;
    }

    // Getters and setters
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

enum Type {
    VILLAGER, MONSTER, MERCHANT
}