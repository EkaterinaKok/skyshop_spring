package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;
    private final UUID id;

    public SimpleProduct(String name, int price, UUID id) throws IllegalArgumentException{
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Неверная цена.");
        }
        this.price = price;
        this.id = id;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + " : " + this.price;
    }
}
