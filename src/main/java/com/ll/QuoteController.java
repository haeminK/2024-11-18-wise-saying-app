package com.ll;

import java.util.Scanner;

public class QuoteController {

    QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public void saveQuote(Scanner sc) {
        System.out.print("명언: ");
        String sentence = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();

        Quote quote = new Quote();
        quote.setAuthor(author);
        quote.setSentence(sentence);

        quoteService.saveQuote(quote);
    }

    public void deleteQuote(Integer id) {
        if (id == null) {
            System.out.println("쿼리를 입력하세요");
            return;
        }

        quoteService.deleteQuote(id);

    }

    public void updateQuote(Scanner sc, Integer id) {
        if (id == null) {
            System.out.println("쿼리를 입력하세요");
            return;
        }

        Quote foundQuote = quoteService.findQuote(id);

        if (foundQuote == null) {
            System.out.println("명언이 없습니다");
            return;
        }

        System.out.println(String.format("명언(기존) : %s", foundQuote.getSentence()));
        System.out.print("명언: ");
        String sentence = sc.nextLine();

        System.out.println(String.format("작가(기존) : %s", foundQuote.getAuthor()));
        System.out.print("작가: ");
        String author = sc.nextLine();

        Quote updatedQuote = new Quote();
        updatedQuote.setId(id);
        updatedQuote.setAuthor(author);
        updatedQuote.setSentence(sentence);

        quoteService.updateQuote(updatedQuote);


    }

    public void quoteList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (Quote quote : quoteService.findAllQuotes()) {
            System.out.println(
                    String.format(
                            "%d / %s / %s",
                            quote.getId(),
                            quote.getAuthor(),
                            quote.getSentence())
            );
        }
        System.out.println();
    }

    public void buildQuotes() {
        try {

            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (Exception e) {
            throw e;
        }

    }

}
