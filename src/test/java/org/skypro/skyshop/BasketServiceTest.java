package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    private final UUID validId = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private final UUID invalidId = UUID.randomUUID();
    private final Product validProduct = new SimpleProduct("ValidProduct", 50, validId);

    @Test
    void addProduct_whenProductNotFound_throwsException() {
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> basketService.addProductToBasket(invalidId)
        );
    }

    @Test
    void addProduct_whenProductExists_addsToBasket() {
        when(storageService.getProductById(validId)).thenReturn(Optional.of(validProduct));

        basketService.addProductToBasket(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    void getUserBasket_whenEmpty_returnsEmptyBasket() {
        when(productBasket.getItems()).thenReturn(Map.of()); // Пустая мапа
        UserBasket basket = basketService.getUserBasket();
        assertThat(basket.getItems()).isEmpty();
        assertThat(basket.getTotal()).isZero();
    }

    @Test
    void getUserBasket_whenNotEmpty_returnsCorrectBasket() {
        Product product = new SimpleProduct("Product", 100, validId);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        // Настройка мока productBasket: id → количество
        Map<UUID, Integer> itemsMap = Map.of(validId, 1);
        when(productBasket.getItems()).thenReturn(itemsMap);

        basketService.addProductToBasket(validId);
        verify(storageService, times(1)).getProductById(validId);

        UserBasket basket = basketService.getUserBasket();
        assertThat(basket.getItems()).hasSize(1);
        assertThat(basket.getItems())
                .first()
                .extracting(BasketItem::getProduct)
                .isEqualTo(product);
        assertThat(basket.getItems())
                .first()
                .extracting(BasketItem::getQuantity)
                .isEqualTo(1);
        assertThat(basket.getTotal()).isEqualTo(100.0); // total = 100 × 1
    }
}
