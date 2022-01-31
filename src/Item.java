
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

    // public Item(int parseInt, String name2, String string, double parseDouble) {
    // }



}
