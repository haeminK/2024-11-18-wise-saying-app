package com.ll;

import com.ll.utils.FileManager;
import com.ll.utils.JsonQuote;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuoteStore {


    private final FileManager fileManager;

    public QuoteStore(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void save(Quote quote) {
        quote.setId(fileManager.updateAndGetCurrentId());
        fileManager.writeById("", quote.getId(), JsonQuote.quoteToJson(quote));
    }

    public void delete(Integer id) {
        fileManager.deleteById("", id);
    }

    public Quote find(int id) {
        String foundStr = fileManager.readById("", id);
        return (foundStr != null) ? JsonQuote.jsonToQuote(foundStr) : null;
    }

    public List<Quote> findAll() {
        List<Quote> list = new ArrayList<Quote>();

        String dirPaths = fileManager.getBasePath();
        File dir = new File(dirPaths);
        String[] filenames = dir.list();

        for (String filename : filenames) {
            if (!filename.endsWith(".json")) continue;
            String path = dirPaths+"/"+filename;
            Quote q = JsonQuote.jsonToQuote(fileManager.read(path));
            list.add(q);
        }

        return list;
    }

    public void saveBuild(List<Quote> quotes) {
        fileManager.writeBySubPath("data", JsonQuote.quotesToJsons(quotes));
    }
}
