package pl.mrymer.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsToAddProductDraftToCatalog() {
        ProductCatalog catalog = thereIsProductCatalog();
        catalog.addProduct("lego-bricks", "Nice Lego set");

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(0, products.size());
    }

    @Test
    void itDenyPublishingProductWithoutPictureAndPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-bricks", "Nice Lego set");
        assertThrows(CantPublishProductException.class, () -> {
            catalog.publish(productId);
        });

//        try {
//            catalog.publish(productId);
//        } catch (CantPublishProductException e) {
//            fail("assertion should be thrown");
//        }
    }

    @Test
    void itAllowsToAssignPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-bricks", "Nice Lego set");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        ProductData loaded = catalog.findById(productId);
        assertEquals(BigDecimal.valueOf(20.20), loaded.getPrice());
    }

    @Test
    void itPublishProductHappyPath() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-bricks", "Nice Lego set");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));
        catalog.assignImage(productId, "http://someNiceImage");
        catalog.publish(productId);

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(1, products.size());
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new MapProductStorage());
    }
}
