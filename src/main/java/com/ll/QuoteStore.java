package com.ll;

import com.ll.utils.FileManager;
import com.ll.utils.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuoteStore {


    private static final HashMap<Integer, Quote> store = new HashMap<>();
    private final JsonParser jp = new JsonParser();


    public void save(Quote quote) {
        quote.setId(updateAndGetCurrentId());
        FileManager.write(getJsonPath(quote.getId()),
                jp.quoteToJson(quote));
    }

    public void delete(Integer id) {
        FileManager.delete(getJsonPath(id));
    }

//    public void update(Quote quote) {
//
//    }

    public Quote find(int id) {
        String foundStr = FileManager.read(getJsonPath(id));
        return (foundStr != null) ? jp.jsonToQuote(foundStr) : null;
    }

    public List<Quote> findAll() {
        List<Quote> list = new ArrayList<Quote>();

        String dirPaths = "./src/main/java/com/ll/db/wiseSaying";
        File dir = new File(dirPaths);
        String[] filenames = dir.list();

        for (String filename : filenames) {
            if (!filename.endsWith(".json")) continue;
            String path = dirPaths+"/"+filename;
            Quote q = jp.jsonToQuote(FileManager.read(path));
            list.add(q);
        }

        return list;
    }

    private static int updateAndGetCurrentId() {
        String strId = FileManager.read("./src/main/java/com/ll/db/wiseSaying/lastId.txt");
        int id = (strId == null) ? 0 : Integer.parseInt(strId);
        updateId(id+1);

        return id;
    }

    private static void updateId(int id) {
        FileManager.write("./src/main/java/com/ll/db/wiseSaying/lastId.txt", String.valueOf(id));
    }

    private String getJsonPath(int id) {
        return "./src/main/java/com/ll/db/wiseSaying/%s.json".formatted(id);
    }

}
