package com.ivan.learn.java.concurrency.example.threadlocal.context;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 22:14
 **/
public class Context {
    private String name;
    private String cardId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
