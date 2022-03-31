package wallet.APP;

import java.util.Map;

public class UserData {

    private String userID;
    private String natID;
    private String psswd;
    private String lastName;
    private String firstName;
    private String language = "EN"; // default value

    public UserData() {}

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setLanguage(Map language) {
        this.language = (String) language.get("language");
    }

    public String getLanguage() {
        return language;
    }

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

    public void setNatID(String natID) {
        this.natID = natID;
    }

    public String getNatID() {
        return natID;
    }

    @Override
    public String toString() {
        return (firstName   + "\n" +
                lastName    + "\n" +
                natID       + "\n" +
                userID      + "\n" +
                psswd       + "\n" +
                language);
    }
 
}
