package pl.mrymer.sales.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public static Cart getEmptyCart() {
        return new Cart();
    }

    public void add(CartItem cartitem) {
        items.add(cartitem);
    }

    public List<CartItem> getItems() {
        return items;

    }

    public int itemsCount() {
        return items.size();
    }
}
