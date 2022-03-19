package wallet.APP;

import java.util.Map;

public class EmployeeData {

    private String employeeID;
    private String psswd;
    private String language;

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
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

    @Override
    public String toString() {
        return (employeeID      + "\n" +
                psswd       + "\n" +
                language);
    }
 
}
