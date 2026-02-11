package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private final int FIX_PRICE = 50;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return this.getName() + " : " + this.getPrice() + " (Фиксированная цена: " + FIX_PRICE + ")";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
