package com.gurov.educational_practic;

import javafx.beans.property.SimpleDoubleProperty;

public class Expenses {

    private SimpleDoubleProperty nutrition;
    private SimpleDoubleProperty medicine;
    private SimpleDoubleProperty house;
    private SimpleDoubleProperty transport;
    private SimpleDoubleProperty education;
    private SimpleDoubleProperty borrow;
    private SimpleDoubleProperty other;

    public Expenses(double nutrition,double medicine,double house,double transport,double education,double borrow,double other){

        this.nutrition = new SimpleDoubleProperty(nutrition);
        this.medicine = new SimpleDoubleProperty(medicine);
        this.house = new SimpleDoubleProperty(house);
        this.transport = new SimpleDoubleProperty(transport);
        this.education = new SimpleDoubleProperty(education);
        this.borrow = new SimpleDoubleProperty(borrow);
        this.other = new SimpleDoubleProperty(other);
    }

    public double getNutrition() {
        return nutrition.get();
    }

    public void setNutrition(double nutrition) {
        this.nutrition.set(nutrition);
    }

    public double getMedicine() {
        return medicine.get();
    }

    public void setMedicine(double medicine) {
        this.medicine.set(medicine);
    }

    public double getHouse() {
        return house.get();
    }

    public void setHouse(double house) {
        this.house.set(house);
    }

    public double getTransport() {
        return transport.get();
    }

    public void setTransport(double transport) {
        this.transport.set(transport);
    }

    public double getEducation() {
        return education.get();
    }

    public void setEducation(double education) {
        this.education.set(education);
    }

    public double getBorrow() {
        return borrow.get();
    }

    public void setBorrow(double borrow) {
        this.borrow.set(borrow);
    }

    public double getOther() {
        return other.get();
    }

    public void setOther(double other) {
        this.other.set(other);
    }
}
