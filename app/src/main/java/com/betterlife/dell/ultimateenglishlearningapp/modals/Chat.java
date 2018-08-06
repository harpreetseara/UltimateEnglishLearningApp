package com.betterlife.dell.ultimateenglishlearningapp.modals;

/**
 * Created by Dell on 6/19/2018.
 */

public class Chat {

    String text,side;

    public void setText(String text) {
        this.text = text;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getText() {
        return text;
    }

    public String getSide() {
        return side;
    }

    public Chat(String text, String side) {

        this.text = text;
        this.side = side;
    }
}
