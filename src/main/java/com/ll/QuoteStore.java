package com.ll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuoteStore {

    private static Integer id = 0;

    private static final HashMap<Integer, Quote> store = new HashMap<>();

    public static void save(Quote quote) {
        quote.setId(getCurrentId());
        store.put(quote.getId(), quote);
    }

    public static void delete(Integer id) {
        store.remove(id);
    }

    public static void update(Quote quote) {
        store.put(quote.getId(), quote);
    }

    public static Quote find(int id) {
        if (!store.containsKey(id)) return null;
        return store.get(id);
    }

    public static List<Quote> findAll() {
        List<Quote> list = new ArrayList<Quote>();

        store.forEach((id, quote) -> {
            list.add(quote);
        });

        return list;
    }

    private static Integer getCurrentId() {
        increaseId();
        return id;
    }

    private static void increaseId() {
        ++id;
    }
}
