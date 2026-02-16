package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

public final class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product);
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество не должно быть меньше 0");
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketItem)) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public String toString() {
        return "Добавлено в корзину {Продукт = " + product + ", Количество = " + quantity + "}";
    }
}
