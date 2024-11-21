package com.ll.utils;

import com.ll.Quote;

import java.util.Arrays;

public class JsonParser {

    public static void main(String[] args) {
        JsonParser jp = new JsonParser();
        Quote quote = new Quote();
        quote.setId(1);
        quote.setAuthor("asdf");
        quote.setSentence("sentece");
        String s = jp.quoteToJson(quote);
        System.out.println(s);
        Quote q = jp.jsonToQuote(s);
        System.out.println("%d %s %s".formatted(q.getId(), q.getAuthor(), q.getSentence()));
    }

    public String quoteToJson(Quote quote) {
        return String.format("{\n\"id\": %d,\n\"content\": \"%s\",\n\"author\": \"%s\"\n}",
                                quote.getId(),
                                quote.getSentence(),
                                quote.getAuthor());
    }

    public Quote jsonToQuote(String json) {
        String[] strings = json.substring(1, json.length()-1).split(",");
        Quote quote = new Quote();

        int id = Integer.parseInt(strings[0].split(":")[1].strip());

        String sentence = strings[1].split(":")[1].strip().strip();
        sentence = sentence.substring(1, sentence.length()-1);

        String author = strings[2].split(":")[1].strip().strip();
        author = author.substring(1, author.length()-1);

        quote.setId(id);
        quote.setSentence(sentence);
        quote.setAuthor(author);

        return quote;
    }
}
