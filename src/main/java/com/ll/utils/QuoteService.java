package com.ll.utils;

import com.ll.Quote;
import com.ll.QuoteStore;

public class QuoteService {

    public void saveQuote(Quote quote) {
        QuoteStore.save(quote);
    }

    public void deleteQuote(Integer id) {
        if (id == null) throw new RuntimeException();
        if (QuoteStore.find(id) == null) throw new RuntimeException();

        QuoteStore.delete(id);
    }

    public void updateQuote(Quote quote) {
        Integer id = quote.getId();

        if (id == null) throw new RuntimeException();
        if (QuoteStore.find(id) == null) throw new RuntimeException();

        QuoteStore.update(quote);
    }
}
