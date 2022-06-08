package pl.mrymer.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapProductStorage implements ProductStorage {
    Map<String, ProductData> products;

    public MapProductStorage() {
        this.products = new HashMap<>();
    }

    @Override
    public void save(ProductData productData) {
        products.put(productData.getId(),
                productData);
    }

    @Override
    public ProductData loadById(String productId) {
        return products.get(productId);
    }

    @Override
    public List<ProductData> allPublishedProducts() {
        return products.values().stream()
                .filter(item -> item.isOnline())
                .collect(Collectors.toList());
    }
}
