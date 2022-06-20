package pl.mrymer.sales.offerting;

import java.math.BigDecimal;

public class Offer {
    private final BigDecimal total;
    private final int itemsCount;

    public Offer(BigDecimal total, int itemsCount) {
        this.total = total;
        this.itemsCount = itemsCount;
    }

    public static Offer empty() {
        return new Offer(BigDecimal.ZERO, 0);
    }

    public static Offer of(BigDecimal total, int itemsCount) {
        return new Offer(total, itemsCount);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getItemsCount() {
        return itemsCount;
    }
}
