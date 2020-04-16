package sample.classes;

public class signedInAccount {

    int accountID;
    String fName;
    String lName;
    String school;
    String email;

    public signedInAccount(){}

    public signedInAccount(int accountID, String fName, String lName, String school, String email) {
        this.accountID = accountID;
        this.fName = fName;
        this.lName = lName;
        this.school = school;
        this.email = email;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
