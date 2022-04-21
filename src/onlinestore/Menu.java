package onlinestore;
import onlinestore.database.Customer;
import onlinestore.database.Repository;
import onlinestore.database.Shoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scan = new Scanner(System.in);
    Customer c;
    List<Integer> shoesInOrder = new ArrayList<Integer>();

    Menu(){
        loginMenu();
    }


    public void loginMenu(){
        List<Customer> customerList = Repository.getAllCustomers();

        String ssn;
        String pw;

        while(true){
            boolean found = false;
            System.out.println("Social security number:");
            ssn = scan.nextLine();
            System.out.println("Password:");
            pw = scan.nextLine();
            for(Customer customer : customerList){
                if (customer.getSocialSecurityNumber().equals(ssn) && customer.getPassword().equals(pw)){
                    c = customer;
                    found = true;
                    mainMenu();
                    break;
                }
            }
            if (!found) {
                System.out.println("Credentials incorrect, try again.");
            }
        }
    }

    public void mainMenu(){
        System.out.println("Welcome " + c.getFullName());
        int input;
        Boolean inloggad = true;
        while(inloggad){
            System.out.println("Enter a number to choose option:" +
                    "\n 1: Shoe menu" +
                    "\n 2: Order details" +
                    "\n 0: Log out");
            input = scan.nextInt();
            if (input == 1) {
                shoeMenu();
            } else if (input == 2) {
                    printShoesInOrder();
            } else if (input == 0) {
                System.exit(0);
            } else {
                System.out.println("Incorrect input, try again.");
            }
        }
    }

    public void shoeMenu(){
        List<Shoe> shoeList = Repository.getAllShoesInStock();
        for(Shoe shoe : shoeList){
            System.out.println(shoe.toString());
        }
        int input;
        while(true) {
            System.out.println("Enter a number to choose option:" +
                    "\n 1: Order a shoe" +
                    "\n 0: Back to main menu");
            input = scan.nextInt();
            if (input == 1) {
                shoeOrderMenu();
            } else if (input == 0) {
                mainMenu();
            } else {
                System.out.println("Incorrect input, try again.");
            }
        }
    }

    public int intToGetFromList(int shoeNumber) {
        int intToGet = 0;
         if (shoeNumber == 1) {
            intToGet = 0;
        } else if (shoeNumber >= 2) {
            intToGet = shoeNumber - 1;
        }
        return intToGet;
    }

    public void addToOrder(int shoeNumber) {
        shoesInOrder.add(shoeNumber);
    }

    public void printShoesInOrder() {
        List<Shoe> shoeList = Repository.getAllShoesInStock();
        if (shoesInOrder.isEmpty()) {
            System.out.println("You have not ordered anything yet.");
        } else {
            for (int i = 0; i < shoesInOrder.size(); i++) {
                System.out.println(shoeList.get(intToGetFromList(shoesInOrder.get(i))).toString());
            }
        }
    }

    public void shoeOrderMenu(){
        System.out.println("Enter the shoe number:");
        int nummer = scan.nextInt();

        try{
            if (nummer <= 10) {
                Repository.addToCart(c.getId(), Repository.getActiveOrderId(c), nummer);
                addToOrder(nummer);
            } else {
                System.out.println("No such shoe, try again...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Menu();
    }

}