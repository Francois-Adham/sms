package users;

public abstract class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String mobileNumber;


    // =====================================
    // ============== SETTERS ==============
    // =====================================
    public void setName(String newName) {
        this.name = newName;
    }
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
    public void setMobileNumber(String newNumber) {
        this.mobileNumber = newNumber;
    }


    // =====================================
    // ============== GETTERS ==============
    // =====================================
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getMobileNumber(){
        return this.mobileNumber;
    }
    public String getEmail(){
        return this.email;
    }

}
