package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    // Вспомогательные тестовые объекты
    private final UUID productId = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private final SimpleProduct testProduct = new SimpleProduct("TestProduct", 100, productId);


    @Test
//если StorageService пустой
    void search_whenStorageEmpty_returnsEmptyList() {
        when(storageService.articlesAndProducts()).thenReturn(List.of());
        var results = searchService.search("anyPattern");
        assertThat(results).isEmpty();
    }

    @Test
//при наличии объектов в StorageService, но без совпадений
    void search_whenNoMatches_returnsEmptyList() {
        when(storageService.articlesAndProducts())
                .thenReturn(List.of(testProduct));
        var results = searchService.search("NonExistentPattern");
        assertThat(results).isEmpty();
    }

    @Test
//поиск с совпадением
    void search_whenMatchFound_returnsSingleResult() {
        when(storageService.articlesAndProducts())
                .thenReturn(List.of(testProduct));
        var results = searchService.search("Test");
        assertThat(results)
                .hasSize(1)
                .first()
                .isEqualTo(new SearchResult(testProduct.getId().toString(), testProduct.getName(), "PRODUCT"));
    }
}
