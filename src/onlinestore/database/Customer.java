package onlinestore.database;

public class Customer {

    private int id;
    private String fullName;
    private String password;
    private String socialSecurityNumber;
    private String address;
    private String city;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", full_name='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
