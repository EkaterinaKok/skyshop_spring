package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

//    private boolean matchesQuery(String text, String query) {
//        if (text == null || query == null) {
//            return false;
//        }
//        String lowerText = text.toLowerCase();
//        String lowerQuery = query.toLowerCase();
//        return lowerText.contains(lowerQuery);
//    }

//    public Collection<SearchResult> search(@RequestParam String pattern){
//        Collection<Searchable> allSearchable = storageService.articlesAndProducts();
//        return allSearchable.stream()
//                .filter(searchable -> searchable.getSearchTerm().contains(pattern))
//                .map(SearchResult::fromSearchable)
//                .collect(Collectors.toList());
//    }

    public Collection<SearchResult> search(@RequestParam String pattern) {
        System.out.println("→ Поиск запущен. Запрос: \"" + pattern + "\"");

        Collection<Searchable> allSearchable = storageService.articlesAndProducts();
        System.out.println("→ Всего объектов для поиска: " + allSearchable.size());

        Collection<SearchResult> result = allSearchable.stream()
                .filter(searchable -> {
                    String term = searchable.getSearchTerm();
                    boolean matches = term != null && term.contains(pattern);
                    System.out.println(
                            "→ Проверяем: \"" + term + "\" | Совпадение: " + matches
                    );
                    return matches;
                })
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());

        System.out.println("→ Найдено результатов: " + result.size());
        return result;
    }
}
