package ex.g1.cau1;

public class student {
    private String  id, name, email;
    private int sex; // 1: nam, 0: nu
    public student(String id, String name, String email, int sex) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
    }
    public String getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public int getSex() {return sex;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setSex(int sex) {this.sex = sex;}

}
