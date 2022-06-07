package org.dng;

public class Car implements Comparable<Car>{
//    private String gosNumber;
//    private int productionYear;
//    private int loadCapacity;
//    private int passengerSeats;

    private String model;
    private int cost;
    private int power;

    public Car(String model, int cost, int power) {
        this.model = model;
        this.cost = cost;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getCost() {
        return cost;
    }

    public void changeCost(int percent){
        double multiplier = (double) (100 + percent) / 100;
        cost *= multiplier;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", cost=" + cost +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(Car o) {
//        if (this.getCost() == o.getCost())
//            return this.getPower() - o.getPower();
        return this.getCost() - o.getCost();
    }
}
