package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;

    @Autowired
    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts();
    };

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles();
    };

    @GetMapping("/search")
    public Collection<SearchResult> searchPattern(@RequestParam String pattern){
        System.out.println("Мы вошли в метод");
        return searchService.search(pattern);
    }

}
