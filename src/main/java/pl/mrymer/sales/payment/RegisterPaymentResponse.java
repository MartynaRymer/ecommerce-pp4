package pl.mrymer.sales.payment;

public class RegisterPaymentResponse {
    private final String paymentId;
    private final String url;

    public RegisterPaymentResponse(String paymentId, String url) {

        this.paymentId = paymentId;
        this.url = url;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentUrl() {
        return url;
    }
}
