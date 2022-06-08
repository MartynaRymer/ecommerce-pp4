package pl.mrymer.credit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;


public class CreditCardTest {
    @Test
    void itAllowsAssignCreditLimit() {
        CreditCard card = new CreditCard();

        card.assignLimit(BigDecimal.valueOf(1000));

        assertEquals(BigDecimal.valueOf(1000), card.getBalance());
    }
}
