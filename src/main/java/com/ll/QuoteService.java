package com.ll;

import java.util.List;

public class QuoteService {

    QuoteStore quoteStore = new QuoteStore();

    public QuoteService(QuoteStore quoteStore) {
        this.quoteStore = quoteStore;
    }

    public Quote findQuote(Integer id) {
        return quoteStore.find(id);
    }

    public List<Quote> findAllQuotes() {
        return quoteStore.findAll();
    }

    public void saveQuote(Quote quote) {
        quoteStore.save(quote);
    }

    public void deleteQuote(Integer id) {
        if (id == null) throw new RuntimeException();
        if (quoteStore.find(id) == null) throw new RuntimeException();

        quoteStore.delete(id);
    }

    public void updateQuote(Quote quote) {
        Integer id = quote.getId();

        if (id == null) throw new RuntimeException();
        if (quoteStore.find(id) == null) throw new RuntimeException();

        quoteStore.save(quote);
    }
}
