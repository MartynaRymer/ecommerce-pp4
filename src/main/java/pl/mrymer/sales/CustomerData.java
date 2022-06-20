package pl.mrymer.sales;

public class CustomerData {
    private final String fname;
    private final String lname;
    private final String email;

    public CustomerData(String fname, String lname, String email) {

        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }
}
