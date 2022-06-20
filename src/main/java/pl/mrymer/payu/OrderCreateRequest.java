package pl.mrymer.payu;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderCreateRequest {
    String notifyUrl;
    String customerIp;
    String merchantPosId;
    String description;
    String currencyCode;
    Integer totalAmount;
    Buyer buyer;
    List<Product> products;
}
