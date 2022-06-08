package pl.mrymer.productcatalog;

import java.util.List;

public class SqlProductStorage implements ProductStorage {
    @Override
    public void save(ProductData productData) {

    }

    @Override
    public ProductData loadById(String productId) {
        return null;
    }

    @Override
    public List<ProductData> allPublishedProducts() {
        return null;
    }
}
