package com.ll.utils;

public class Message {
    static String msg;

    static public String getMessage() {
        return msg;
    }

    static public void setMessage(String msg) {
        Message.msg = msg;
    }

    static public void reset() {
        msg = null;
    }
}
