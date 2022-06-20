package pl.mrymer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mrymer.payu.PayU;
import pl.mrymer.productcatalog.*;
import pl.mrymer.sales.*;
import pl.mrymer.sales.cart.CartStorage;
import pl.mrymer.sales.offerting.OfferCalculator;
import pl.mrymer.sales.payment.DummyPaymentGateway;
import pl.mrymer.sales.payment.PayUPaymentGateway;
import pl.mrymer.sales.payment.PaymentGateway;
import pl.mrymer.sales.product.ListProductDetailsProvider;
import pl.mrymer.sales.product.ProductDetails;
import pl.mrymer.sales.product.ProductDetailsProvider;
import pl.mrymer.sales.reservation.InMemoryReservationStorage;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Hello");
    }

    @Bean
    PaymentGateway createPaymentGateway() {
        return new PayUPaymentGateway(new PayU(System.getenv("MERCHANT_POS_ID")));
    }

    @Bean
    ProductDetailsProvider createProductDetailsProvider(ProductCatalog productCatalog) {
        return productId -> {
            ProductData data = productCatalog.findById(productId);

            if (data == null) {
                return Optional.empty();
            }

            return Optional.of(new ProductDetails(productId, data.getName(), data.getPrice()));
        };
    }
    @Bean
    Sales createSalesComponent(PaymentGateway paymentGateway, ProductDetailsProvider productDetailsProvider) {
        return new Sales(
                new CartStorage(),
                productDetailsProvider,
                paymentGateway,
                new InMemoryReservationStorage(),
                new OfferCalculator()
        );
    }

    @Bean
    ProductStorage createMyProductStorage(JpaProductDataRepository repository) {
        return new JpaProductStorage(repository);
    }

    @Bean
    ProductCatalog createMyProductCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);

        fillWithExampleProducts(productCatalog);

        return productCatalog;
    }

    private void fillWithExampleProducts(ProductCatalog productCatalog) {
        String productId1 = productCatalog.addProduct("lego-1", "Nice set");
        productCatalog.changePrice(productId1, BigDecimal.TEN);
        productCatalog.assignImage(productId1, "https://picsum.photos/id/237/200/300");
        productCatalog.publish(productId1);

        String productId2 = productCatalog.addProduct("lego-2", "Event nicer");
        productCatalog.changePrice(productId2, BigDecimal.TEN);
        productCatalog.assignImage(productId2, "https://picsum.photos/id/238/200/300");
        productCatalog.publish(productId2);

        String productId3 = productCatalog.addProduct("lego-3", "Bad one");
        productCatalog.changePrice(productId3, BigDecimal.TEN);
        productCatalog.assignImage(productId3, "https://picsum.photos/id/239/200/300");
        productCatalog.publish(productId3);
    }

}
