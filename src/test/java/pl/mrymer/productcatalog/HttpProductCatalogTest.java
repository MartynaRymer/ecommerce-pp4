package pl.mrymer.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpProductCatalogTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itReturns200OnRequest() {
        String url = String.format(
                "http://localhost:%s/api/products", port);
        ResponseEntity<ProductData[]> response = http.getForEntity(url, ProductData[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
