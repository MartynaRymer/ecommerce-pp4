package pl.mrymer.payu;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class PayU {

    private String merchantPosId;

    public PayU(String merchantPosId) {

        this.merchantPosId = merchantPosId;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {
        orderCreateRequest.setMerchantPosId(merchantPosId);
        RestTemplate http = new RestTemplate();
        String url = "https://secure.snd.payu.com/api/v2_1/orders";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("FOO", "BOO");
        headers.add("Authorization", "Bearer d9a4536e-62ba-4f60-8017-6053211d3f47");

        HttpEntity<OrderCreateRequest> httpRequest = new HttpEntity<>(orderCreateRequest, headers);

        ResponseEntity<OrderCreateResponse> response = http.postForEntity(url, httpRequest, OrderCreateResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {

        }

        return response.getBody();
    }
}
