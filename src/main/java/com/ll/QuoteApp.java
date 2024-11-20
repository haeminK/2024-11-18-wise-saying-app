package com.ll;

import com.ll.utils.QuoteService;

import java.util.Scanner;

public class QuoteApp {

    boolean running = true;
    Scanner sc = new Scanner(System.in);

    QuoteStore quoteStore = new QuoteStore();
    QuoteService quoteService = new QuoteService(quoteStore);
    QuoteController quoteController = new QuoteController(quoteService);


    public void run() {

        System.out.println("== 명언 앱 ==");

        while (running) {
            System.out.print("명령) ");

            String[] commandArr = sc.nextLine().strip().split("[?]");
            String command = commandArr[0];
            String query = commandArr.length > 1 ? commandArr[1] : null;
            Integer id = query != null ? Integer.parseInt(query.split("=")[1]) : null;

            switch (command) {
                case "종료":
                    running = false;
                    break;

                case "등록":
                    quoteController.saveQuote(sc);
                    break;

                case "목록":
                    quoteController.quoteList();
                    break;

                case "삭제":
                    quoteController.deleteQuote(id);
                    break;

                case "수정":
                    quoteController.updateQuote(sc, id);
                    break;
            }
        }

        sc.close();
    }
}