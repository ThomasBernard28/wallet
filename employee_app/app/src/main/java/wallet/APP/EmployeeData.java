package wallet.APP;

import java.util.Map;

public class EmployeeData {

    private String bic;
    private String psswd;
    private String language;
    private String name;

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBic() {
        return bic;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (name      + "\n" +
                bic       + "\n" +
                psswd     + "\n" +
                language);
    }
 
}
