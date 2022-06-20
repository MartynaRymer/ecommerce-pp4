package pl.mrymer.sales.reservation;

import pl.mrymer.sales.CustomerData;
import pl.mrymer.sales.payment.DummyPaymentGateway;
import pl.mrymer.sales.payment.PaymentGateway;
import pl.mrymer.sales.payment.RegisterPaymentRequest;
import pl.mrymer.sales.payment.RegisterPaymentResponse;

import java.math.BigDecimal;

public class Reservation {
    private final String id;
    private final BigDecimal totalAmount;
    private final String fname;
    private final String lname;
    private final String email;
    private String paymentId;
    private String paymentUrl;

    public Reservation(String id, BigDecimal totalAmount, String fname, String lname, String email) {

        this.id = id;
        this.totalAmount = totalAmount;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public static Reservation of(String reservationId, BigDecimal totalAmount, CustomerData customerData) {
        return new Reservation(reservationId, totalAmount, customerData.getFname(), customerData.getLname(), customerData.getEmail());
    }

    public void registerPayment(PaymentGateway paymentGateway) {
        RegisterPaymentResponse registerPaymentResponse = paymentGateway.register(new RegisterPaymentRequest(
                id,
                totalAmount,
                fname,
                lname,
                email
        ));

        this.paymentId = registerPaymentResponse.getPaymentId();
        this.paymentUrl = registerPaymentResponse.getPaymentUrl();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return totalAmount;
    }
}
