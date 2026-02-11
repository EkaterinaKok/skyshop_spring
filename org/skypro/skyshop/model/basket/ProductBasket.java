package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> items  = new HashMap<>();

    public void addProduct(UUID id) {
        items.computeIfAbsent(id, key -> 0);
        items.put(id, items.get(id) + 1);
    }

    public Map<UUID, Integer> getItems() {
        return Collections.unmodifiableMap(items); //вернули копию
    }
}
