package com.ll;

import com.ll.utils.FileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuoteStore {


    private static final HashMap<Integer, Quote> store = new HashMap<>();

    public void save(Quote quote) {
        quote.setId(getAndUpdateCurrentId());
        store.put(quote.getId(), quote);
    }

    public void delete(Integer id) {
        store.remove(id);
    }

    public void update(Quote quote) {
        store.put(quote.getId(), quote);
    }

    public Quote find(int id) {
        if (!store.containsKey(id)) return null;
        return store.get(id);
    }

    public List<Quote> findAll() {
        List<Quote> list = new ArrayList<Quote>();

        store.forEach((id, quote) -> {
            list.add(quote);
        });

        return list;
    }

    private static Integer getAndUpdateCurrentId() {
        String strId = FileManager.read("./src/main/java/com/ll/db/wiseSaying/lastId.txt");
        int id = (strId == null) ? 0 : Integer.parseInt(strId);
        updateId(id+1);

        return id;
    }

    private static void updateId(int id) {
        FileManager.write("./src/main/java/com/ll/db/wiseSaying/lastId.txt", String.valueOf(id));
    }


}
