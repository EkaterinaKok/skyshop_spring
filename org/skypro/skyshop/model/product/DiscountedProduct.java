package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private double discountInPercentInt; // от 0 до 100
    private Product[] products = new Product[5];
    private boolean isPriceCalculated;
    private final UUID id;

    public DiscountedProduct(String name, int basePrice, int discountInPercentInt, UUID id) throws IllegalArgumentException{
        super(name, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Неверная цена.");
        }
        if (discountInPercentInt < 0 || discountInPercentInt > 100){
            throw new IllegalArgumentException("Неверный процент.");
        }
        this.basePrice = basePrice;
        this.discountInPercentInt = discountInPercentInt;
        this.id = id;
    }

    @Override
    public int getPrice() {
        if (!isPriceCalculated) {
            double percent;
            percent = basePrice * (discountInPercentInt / 100);
            basePrice = (int) (basePrice - percent);
            isPriceCalculated = true;
        }
        return basePrice;
    }

    @Override
    public String toString() {
        return this.getName() + " : " + this.getPrice() + " (" + this.discountInPercentInt + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
