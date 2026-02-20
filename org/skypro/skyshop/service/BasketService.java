package org.skypro.skyshop.service;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID productId) {
        System.out.println("Поиск продукта по ID: " + productId);

        // Извлекаем Product из Optional или бросаем исключение
        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Товар не найден: " + productId));

        System.out.println("Найден продукт: " + product.getName() + ", цена: " + product.getPrice());

        // Добавляем продукт в корзину (предполагается, что метод принимает UUID)
        productBasket.addProduct(productId);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> itemsMap = productBasket.getItems();

        List<BasketItem> basketItems = itemsMap.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Товар не найден в хранилище: " + entry.getKey()
                            ));
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}

