package pl.mrymer.productcatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListProductStorage implements ProductStorage {

    List<ProductData> products;

    public ListProductStorage() {
        this.products = new ArrayList<>();
    }

    @Override
    public void save(ProductData productData) {
        products.add(productData);
    }

    @Override
    public ProductData loadById(String productId) {
        return products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .get();
    }

    @Override
    public List<ProductData> allPublishedProducts() {
        return products.stream()
                .filter(p -> p.isOnline())
                .collect(Collectors.toList());
    }
}
