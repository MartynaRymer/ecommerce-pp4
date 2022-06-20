package pl.mrymer.sales.payment;

import java.math.BigDecimal;

public class RegisterPaymentRequest {
    private final String id;
    private final BigDecimal totalAmount;
    private final String fname;
    private final String lname;
    private final String email;

    public RegisterPaymentRequest(String id, BigDecimal totalAmount, String fname, String lname, String email) {

        this.id = id;
        this.totalAmount = totalAmount;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
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

    public Integer getTotalAmountAsIntInSubUnit() {
        return totalAmount.multiply(BigDecimal.valueOf(100)).intValue();
    }
}
