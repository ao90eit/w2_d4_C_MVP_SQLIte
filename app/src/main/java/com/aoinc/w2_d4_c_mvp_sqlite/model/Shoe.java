package com.aoinc.w2_d4_c_mvp_sqlite.model;

public class Shoe {
    private String shoeModel;
    private int shoeSize;
    private double shoePrice;

    // for db reference
    private int shoeID;

    public Shoe(String shoeModel, int shoeSize, double shoePrice) {
        this.shoeModel = shoeModel;
        this.shoeSize = shoeSize;
        this.shoePrice = shoePrice;
    }

    public Shoe(int shoeID, String shoeModel, int shoeSize, double shoePrice) {
        this.shoeModel = shoeModel;
        this.shoeSize = shoeSize;
        this.shoePrice = shoePrice;
        this.shoeID = shoeID;
    }

    public String getShoeModel() {
        return shoeModel;
    }

    public void setShoeModel(String shoeModel) {
        this.shoeModel = shoeModel;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    public int getShoeID() {
        return shoeID;
    }
}
