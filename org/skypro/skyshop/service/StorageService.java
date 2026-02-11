package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000001"), new SimpleProduct("Авокадо", 50, UUID.fromString("00000000-0000-0000-0000-000000000001")));
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000002"), new SimpleProduct("Томат", 100, UUID.fromString("00000000-0000-0000-0000-000000000002")));
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000003"), new SimpleProduct("Лук", 30, UUID.fromString("00000000-0000-0000-0000-000000000003")));
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000004"), new SimpleProduct("Огурец", 50, UUID.fromString("00000000-0000-0000-0000-000000000004")));

        articles.put(UUID.randomUUID(), new Article("Заголовок №1", "Интересный текст", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Заголовок №2", "Важный текст", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Заголовок №3", "Длинный текст", UUID.randomUUID()));
    }

    public Collection<Product> getProducts(){
        return products.values();
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Collection<Searchable> articlesAndProducts(){
        return Stream.concat(
                getProducts().stream().map(product -> (Searchable) product),
                getArticles().stream().map(article -> (Searchable) article)
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

}
