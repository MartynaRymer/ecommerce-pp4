package pl.mrymer.sales.payment;

public class DummyPaymentGateway implements PaymentGateway {

    @Override
    public RegisterPaymentResponse register(RegisterPaymentRequest registerPaymentRequest) {
        return new RegisterPaymentResponse(
                "paymentId__123qwe",
                "http://dumyyPaymentGateway"
        );
    }
}
