package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UserBasket {
    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items) {
        this.items = Objects.requireNonNull(items, "Список не может быть null")
                .stream()
                .map(Objects::requireNonNull)
                .collect(Collectors.toList());

        this.total = this.items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBasket)) return false;
        UserBasket that = (UserBasket) o;
        return Double.compare(that.total, total) == 0 &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }

    @Override
    public String toString() {
        return "Корзина Покупателя {Продуктов = " + items + ", Сумма = " + total + "}";
    }
}
