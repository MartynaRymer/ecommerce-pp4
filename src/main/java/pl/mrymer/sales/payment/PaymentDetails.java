package pl.mrymer.sales.payment;

public class PaymentDetails {

    String url;
    String reservationId;
    String paymentId;

    public PaymentDetails(String reservationId, String paymentId, String url) {
        this.url = url;
        this.reservationId = reservationId;
        this.paymentId = paymentId;
    }

    public PaymentDetails() {
    }

    public String getUrl() {
        return url;
    }

    public String getReservationId() {
        return reservationId;
    }
}
