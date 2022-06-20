package pl.mrymer.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mrymer.sales.cart.CartStorage;
import pl.mrymer.sales.offerting.Offer;
import pl.mrymer.sales.offerting.OfferCalculator;
import pl.mrymer.sales.payment.DummyPaymentGateway;
import pl.mrymer.sales.product.ListProductDetailsProvider;
import pl.mrymer.sales.product.ProductDetails;
import pl.mrymer.sales.product.ProductNotAvailableException;
import pl.mrymer.sales.reservation.InMemoryReservationStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectingProductsTest {

    List<ProductDetails> availableProducts;

    @BeforeEach
    void setup() {
        this.availableProducts = new ArrayList<>();
    }

    @Test
    void showsEmptyOffer() {
        String clientId = thereIsClient();
        Sales sales = thereIsSalesModule();

        Offer offer = sales.getCurrentOffer(clientId);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());
    }

    @Test
    void itDenyAddingMissingProductToCart() {
        String clientId = thereIsClient();
        Sales sales = thereIsSalesModule();

        assertThrows(ProductNotAvailableException.class, () -> {
            sales.addToCart(clientId, "MISSING_PRODUCT_ID");
        });
    }


    @Test
    void itAllowToAddProduct() {
        String productId = thereIsProduct("lego", BigDecimal.valueOf(10.10));
        String clientId = thereIsClient();
        Sales sales = thereIsSalesModule();

        sales.addToCart(clientId, productId);
        Offer offer = sales.getCurrentOffer(clientId);

        assertEquals(BigDecimal.valueOf(10.10), offer.getTotal());
        assertEquals(1, offer.getItemsCount());
    }

    @Test
    void itAllowToAddMultipleProducts() {
        String productId1 = thereIsProduct("lego-1", BigDecimal.valueOf(10.10));
        String productId2 = thereIsProduct("lego-2", BigDecimal.valueOf(20.20));
        String clientId = thereIsClient();
        Sales sales = thereIsSalesModule();

        sales.addToCart(clientId, productId1);
        sales.addToCart(clientId, productId2);
        Offer offer = sales.getCurrentOffer(clientId);

        assertEquals(BigDecimal.valueOf(30.30), offer.getTotal());
        assertEquals(2, offer.getItemsCount());
    }

    private String thereIsClient() {
        return "martyna";
    }

    private String thereIsProduct(String productId, BigDecimal price) {
        ProductDetails productDetails = new ProductDetails(productId, "Some name", price);
        availableProducts.add(productDetails);

        return productId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                new CartStorage(),
                new ListProductDetailsProvider(availableProducts),
                new DummyPaymentGateway(),
                new InMemoryReservationStorage(),
                new OfferCalculator()
        );
    }

    void every5thProductIsFree() {
        String productId = thereIsProduct("lego", BigDecimal.valueOf(10));
        String clientId = thereIsClient();
        Sales sales = thereIsSalesModule();

        sales.addToCart(clientId, productId);
        sales.addToCart(clientId, productId);
        sales.addToCart(clientId, productId);
        sales.addToCart(clientId, productId);
        sales.addToCart(clientId, productId);

        Offer offer = sales.getCurrentOffer(clientId);

        assertEquals(BigDecimal.valueOf(40), offer.getTotal());
    }
}
