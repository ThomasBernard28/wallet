package wallet.APP;

public class UserData {

    private String firstName;
    private String lastName;
    private int    nationalID;

    public UserData() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public int getNationalID() {
        return nationalID;
    }

    @Override
    public String toString() {
        return (firstName   + "\n" +
                lastName    + "\n" +
                nationalID);
    }

 
}
