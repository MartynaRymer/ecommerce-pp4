package pl.mrymer.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer {
    String email;
    String phone;
    String firstName;
    String lastName;
    String language;
}
