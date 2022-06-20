package pl.mrymer.sales.payment;

import pl.mrymer.payu.*;

import java.util.Arrays;

public class PayUPaymentGateway implements PaymentGateway {
    PayU payU;

    public PayUPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public RegisterPaymentResponse register(RegisterPaymentRequest registerPaymentRequest) {
        OrderCreateResponse response = payU.handle(OrderCreateRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("My nice services")
                .currencyCode("PLN")
                .totalAmount(registerPaymentRequest.getTotalAmountAsIntInSubUnit())
                .buyer(Buyer.builder()
                        .email(registerPaymentRequest.getEmail())
                        .firstName(registerPaymentRequest.getFname())
                        .lastName(registerPaymentRequest.getLname())
                        .language("pl")
                        .build())
                .products(Arrays.asList(
                        Product.builder()
                                .name("My nice services")
                                .unitPrice(registerPaymentRequest.getTotalAmountAsIntInSubUnit())
                                .quantity(1)
                                .build()
                ))
                .build());
        return new RegisterPaymentResponse(response.getOrderId(), response.getRedirectUri());
    }
}
