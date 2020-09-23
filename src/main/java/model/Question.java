package ch.heigvd.amt.mvcsimple.model;

public class Question {

    private String author;
    private String body;
    private String title;
    private int id;

    public Question(String author, String title, String body, int id) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getId(){
        return id;
    }
}
