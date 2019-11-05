package pl.wojciech.message;

import java.time.LocalTime;

public class Message {
    private String author;
    private String message;
    private String time;

    public Message(String author, String message, String time) {
        this.author = author;
        this.message = message;
        this.time = time;
    }

    //gettery potrzebne do wytworzenia pliku json
    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
