package com.ll;

import java.util.List;

public class QuoteService {

    QuoteStore quoteStore;

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

    public void deleteQuote(int id) {
        if (quoteStore.find(id) == null) {
            System.out.println("명언이 없습니다");
            return;
        };

        quoteStore.delete(id);
        System.out.println(String.format("%d번 명언이 삭제되었습니다.", id));
    }

    public void updateQuote(Quote quote) {
        int id = quote.getId();

        if (quoteStore.find(id) == null) {
            System.out.println("명언이 없습니다");
            return;
        }

        quoteStore.save(quote);
        System.out.println(String.format("%d번 명언이 수정되었습니다.", id));
    }

    public void buildQuotes() {
        quoteStore.saveBuild(quoteStore.findAll());
    }

}
