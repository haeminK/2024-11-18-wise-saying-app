package com.ll.utils;

import com.ll.Quote;

public class JsonParser {
    public String quoteToJson(Quote quote) {
        return String.format("{/n\"id\": %d\n\"content\": \"%s\"\n\"author\": \"%s\"\n}",
                                quote.getId(),
                                quote.getSentence(),
                                quote.getAuthor());
    }

    public Quote jsonToQuote(String json) {
        String[] strings = json.split("\n");

        Quote quote = new Quote();

        int id = Integer.parseInt(strings[1].split(":")[1].strip());
        String sentence = strings[2].split(":")[1].strip().strip();
        String author = strings[3].split(":")[1].strip().strip();

        quote.setId(id);
        quote.setSentence(sentence);
        quote.setAuthor(author);

        return quote;
    }
}
