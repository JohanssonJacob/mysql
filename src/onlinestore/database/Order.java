package onlinestore.database;

import java.util.List;

public class Order {
    private int id;
    private String date;
    private int customerId;
    private List<Shoe>shoeList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", customerId=" + customerId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    public void printAllShoes(){
        for (Shoe s : shoeList){
            System.out.println(s.toString());
        }
    }

    public void printSpecificShoe(int shoeId) {
        for (int i = 0; i < shoeList.size(); i++) {
            if (shoeId == i+1) {
                System.out.println(shoeList.get(i).toString());
            }
        }
    }
}
