// Name: Tim Yang
// Course: CNT 4714-Spring 2022
// Assignment title: Project 1- Event-driven Enterprise Simulation
// Date: Sunday january 30, 2022

public class Item {
    private String id;
    private String name;
    private boolean availability;
    private double price;

    public Item(String id, String name, boolean availability, double price) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getAvailability() {
        return this.availability;
    }

    public double getPrice() {
        return this.price;
    }
}
