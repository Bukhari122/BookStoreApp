package application;

import java.util.ArrayList;
import java.util.List;

public class BooksClass {
    private static List<Book> books = new ArrayList<>();

    public static List<Book> getBooks() {
        return books;
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static void deleteBook(Book book) {
        books.remove(book);
    }

    public static void loadSampleBooks() {
        if (books.isEmpty()) {
            books.add(new Book("1984", 19.99));
            books.add(new Book("The Hobbit", 14.99));
            books.add(new Book("Clean Code", 29.99));
        }
    }
}

