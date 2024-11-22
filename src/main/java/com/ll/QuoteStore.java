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
        update(quote);
    }

    public void update(Quote quote) {
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
        File dir = new File(fileManager.getBasePath());

        for (String filename : dir.list()) {
            String id = filename.substring(0, filename.lastIndexOf('.'));
            if (!filename.endsWith(".json") || !id.matches("\\d+"))
                continue;

            Quote q = JsonQuote.jsonToQuote(fileManager.readById("", Integer.parseInt(id)));
            list.add(q);
        }

        return list;
    }

    public void saveBuild(List<Quote> quotes) {
        fileManager.writeBySubPath("data", JsonQuote.quotesToJsons(quotes));
    }
}
