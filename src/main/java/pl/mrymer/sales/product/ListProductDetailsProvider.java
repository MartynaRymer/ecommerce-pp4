package pl.mrymer.sales.product;

import java.util.List;
import java.util.Optional;

public class ListProductDetailsProvider implements ProductDetailsProvider {
    private List<ProductDetails> availableProducts;

    public ListProductDetailsProvider(List<ProductDetails> availableProducts) {

        this.availableProducts = availableProducts;
    }

    @Override
    public Optional<ProductDetails> getById(String productId) {
        return availableProducts.stream()
                .filter(productDetails -> productDetails.getProductId().equals(productId))
                .findFirst();
    }
}
