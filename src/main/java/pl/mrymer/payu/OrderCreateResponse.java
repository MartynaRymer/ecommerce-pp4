package pl.mrymer.payu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.server.ErrorPage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {
    Status status;
    String redirectUri;
    String orderId;
    String extOrderId;
}
