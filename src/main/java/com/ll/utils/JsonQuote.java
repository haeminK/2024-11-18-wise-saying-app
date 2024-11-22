package com.ll.utils;

import com.ll.Quote;

public class JsonQuote {


    public static String quoteToJson(Quote quote) {
        return String.format("{\n\"id\": %d,\n\"content\": \"%s\",\n\"author\": \"%s\"\n}",
                                quote.getId(),
                                quote.getSentence(),
                                quote.getAuthor());
    }

    public static Quote jsonToQuote(String json) {
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
