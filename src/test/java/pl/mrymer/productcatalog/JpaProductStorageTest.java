package pl.mrymer.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaProductStorageTest {

    @Autowired
    JpaProductDataRepository repository;

    @Test
    void itStoreAndLoadProduct() {
        ProductStorage productStorage = thereIsJpaProductStorage();
        ProductData productData = thereIsExampleProduct();

        productStorage.save(productData);
        ProductData loaded = productStorage.loadById(productData.getId());

        assertEquals(productData.getName(), loaded.getName());
    }

    private ProductStorage thereIsJpaProductStorage() {
        return new JpaProductStorage(repository);
    }

    private ProductData thereIsExampleProduct() {
        return new ProductData("nice-one", "Nice product");
    }
}
