package com.example.android.textswitcher;

public class Argument {
    private String text;
    private String type;

    public Argument(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
