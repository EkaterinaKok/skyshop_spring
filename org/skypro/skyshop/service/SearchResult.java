package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
public class SearchResult {
    final String id;
    final String name;
    final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContentTypeType() { return contentType; }

//    public static SearchResult fromSearchable(Searchable searchable){
//        String id = searchable.getId().toString();
//        String name = searchable.getSearchTerm(); // вспомогательная приватная метода
//        String contentType = searchable.getContentType(); // вспомогательная приватная метода
//        return new SearchResult(id, name, contentType);
//    }

    public static SearchResult fromSearchable(Searchable searchable) {
        System.out.println("→ Преобразуем Searchable: id=" + searchable.getId().toString() +
                ", term=" + searchable.getSearchTerm());

        // Тут ваш код создания SearchResult
        SearchResult result = new SearchResult(
                searchable.getId().toString(),
                searchable.getSearchTerm(),
                searchable.getContentType()
        );

        System.out.println("→ Получен SearchResult: " + result);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType);
    }
}
