package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
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
        products.put(UUID.randomUUID(), new SimpleProduct("Огурец", 50, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new SimpleProduct("Авокадо", 100, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new SimpleProduct("Лук", 30, UUID.randomUUID()));

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

}
