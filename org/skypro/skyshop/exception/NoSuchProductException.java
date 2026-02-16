package org.skypro.skyshop.exception;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    public static final String ERROR_CODE = "PRODUCT_NOT_FOUND";

    public NoSuchProductException(UUID productId) {
        super("Товар с ID " + productId + " не найден");
    }

    public NoSuchProductException(UUID productId, Throwable cause) {
        super("Товар с ID " + productId + " не найден", cause);
    }
}

