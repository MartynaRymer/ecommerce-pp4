package pl.mrymer.productcatalog;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductData {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private boolean online;

    ProductData() {}

    public ProductData(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal newPrice) {
        price = newPrice;
    }

    public void assignImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOnline() {
        return online;
    }

    public void publish() {
        this.online = true;
    }

    public String getId() {
        return id;
    }
}
