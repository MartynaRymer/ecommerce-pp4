package pl.mrymer.productcatalog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListProductStorageTest {
    @Test
    void itSotreAndLoadProduct() {
        ProductStorage sqlProductStorage = new ListProductStorage();
        ProductData sampleProduct = thereIsSampleProductData();

        sqlProductStorage.save(sampleProduct);

        ProductData loaded = sqlProductStorage.loadById(sampleProduct.getId());

        assertEquals(sampleProduct.getName(), loaded.getName());
        assertEquals(sampleProduct.getId(), loaded.getId());
    }

    private ProductData thereIsSampleProductData() {
        return new ProductData("lego-1", "nice one");
    }
}
