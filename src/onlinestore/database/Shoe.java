package onlinestore.database;

public class Shoe {

    private int id;
    private String name;
    private int price;
    private int stock;
    private String color;
    private int size;

    @Override
    public String toString() {
        return  "\nShoe number: " + id +
                "\nName: " + name +
                "\nPrice: " + price +
                "\nAmount in stock: " + stock +
                "\nColor: " + color +
                "\nSize: " + size;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
