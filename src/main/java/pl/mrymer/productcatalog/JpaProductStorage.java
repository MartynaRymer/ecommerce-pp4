package pl.mrymer.productcatalog;

import java.util.List;
import java.util.stream.Collectors;

public class JpaProductStorage implements ProductStorage {
    private JpaProductDataRepository repository;

    public JpaProductStorage(JpaProductDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ProductData productData) {
        repository.save(productData);
    }

    @Override
    public ProductData loadById(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductData> allPublishedProducts() {
        return repository.findAll()
                .stream()
                .filter(productData -> productData.isOnline())
                .collect(Collectors.toList());
    }
}
