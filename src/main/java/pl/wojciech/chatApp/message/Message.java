package pl.wojciech.message;


public class Message {
    private String author;
    private String message;
    private String time;
    private long number;
    private static long counter = 0;

    public Message(String author, String message, String time) {
        this.author = author;
        this.message = message;
        this.time = time;
        this.number = ++counter;
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

    public long getNumber() {
        return number;
    }

}
