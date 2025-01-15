package ch.heigvd.dai.model.entity;

import java.sql.*;

public class Player extends Character {
    private double experience;
    private int balance;
    private String champion;

    public Player(String name, double experience, int balance, String champion) {
        super(name);
        this.experience = experience;
        this.balance = balance;
        this.champion = champion;
    }

    // Getters and setters
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }
}