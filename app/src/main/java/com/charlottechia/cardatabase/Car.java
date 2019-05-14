package com.charlottechia.cardatabase;

public class Car {

    private int id;
    private String brand;
    private double litre;


    public Car(int id, String brand, double litre) {
        this.id = id;
        this.brand = brand;
        this.litre = litre;
    }

    public int getId() {
        return id;
    }

    public double getLitre() {
        return litre;
    }

    public void setLitre(double litre) {
        this.litre = litre;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setId(int id) {
        this.id = id;
    }
}
