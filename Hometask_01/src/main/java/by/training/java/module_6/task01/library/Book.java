package by.training.java.module_6.task01.library;

public class Book {

    private int bookNumber;
    private String bookTitle;
    private String author;
    private String bookVersion;
    private String bookDescription;
    private String newLine = System.getProperty("line.separator");

    public Book() {
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getNewLine() {
        return newLine;
    }

    @Override
    public String toString() {
        return "Book # " + getBookNumber() + newLine + getBookTitle() + newLine + getAuthor() + newLine
                + getBookVersion() + newLine + getBookDescription();
    }
}
