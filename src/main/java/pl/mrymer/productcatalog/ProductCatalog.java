package pl.mrymer.productcatalog;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalog {
    ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public String addProduct(String id, String name) {
        ProductData productData = new ProductData(id, name);
        productStorage.save(productData);
        return id;
    }

    public void publish(String productId) {
        ProductData loaded = productStorage.loadById(productId);

        if (loaded.getPrice() == null) {
            throw new CantPublishProductException();
        }

        loaded.publish();
        productStorage.save(loaded);
    }

    public List<ProductData> allPublishedProducts() {
        return productStorage.allPublishedProducts();
    }

    public void changePrice(String productId, BigDecimal newPrice) {
            ProductData loaded = productStorage.loadById(productId);
            loaded.changePrice(newPrice);
            productStorage.save(loaded);
    }

    public ProductData findById(String productId) {
        return productStorage.loadById(productId);
    }

    public void assignImage(String productId, String imageUrl) {
        ProductData loaded = productStorage.loadById(productId);
        loaded.assignImage(imageUrl);
        productStorage.save(loaded);
    }
}
