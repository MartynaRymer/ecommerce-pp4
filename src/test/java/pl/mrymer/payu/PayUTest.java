package pl.mrymer.payu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itHandlePaymentCreation() {
        //A
        PayU payu = thereIsPayUApiClient();
        OrderCreateRequest orderCreateRequest = exampleOrderCreateRequest();
        //A
        OrderCreateResponse response = payu.handle(orderCreateRequest);
        //A
        assertEquals(response.getStatus().getStatusCode(), "SUCCESS");
        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest exampleOrderCreateRequest() {
        return OrderCreateRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("RTV market")
                .currencyCode("PLN")
                .totalAmount(21000)
                .buyer(Buyer.builder()
                        .email("john.doe@example.com")
                        .phone("654111654")
                        .firstName("John")
                        .lastName("Doe")
                        .language("pl")
                        .build())
                .products(Arrays.asList(
                        Product.builder()
                                .name("Wireless Mouse for Laptop")
                                .unitPrice(15000)
                                .quantity(1)
                                .build()
                ))
                .build();
    }

    private PayU thereIsPayUApiClient() {
        return new PayU("300746");
    }
}
