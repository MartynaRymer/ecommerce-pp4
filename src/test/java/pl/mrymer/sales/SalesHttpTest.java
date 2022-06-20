package pl.mrymer.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.mrymer.sales.offerting.Offer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itExposeCurrentOffer() {
        String url = String.format("http://localhost:%s/api/sales/current-offer", port);

        ResponseEntity<Offer> r = http.getForEntity(url, Offer.class);

        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
