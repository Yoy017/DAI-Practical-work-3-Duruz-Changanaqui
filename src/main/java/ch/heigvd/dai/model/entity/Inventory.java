package ch.heigvd.dai.model.entity;

import java.util.LinkedList;

public class Inventory {
    private LinkedList<Slot> slots;

    public void addSlot(Slot slot) {
        if(slots == null) {
            slots = new LinkedList<>();
        }
        slots.add(slot);
    }

    public LinkedList<Slot> getSlots() {
        return slots;
    }
}