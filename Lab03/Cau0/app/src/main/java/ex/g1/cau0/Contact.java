package ex.g1.cau0;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    Contact(int id, String name, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setId(int id) {this.id = id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
