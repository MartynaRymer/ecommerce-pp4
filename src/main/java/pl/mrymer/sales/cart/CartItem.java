package pl.mrymer.sales.cart;

import java.math.BigDecimal;

public class CartItem {
    private final String productId;
    private final String name;
    private final BigDecimal price;
    private int quantity;

    public CartItem(String productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public static CartItem of(String productId, String name, BigDecimal price) {
        return new CartItem(productId, name, price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
