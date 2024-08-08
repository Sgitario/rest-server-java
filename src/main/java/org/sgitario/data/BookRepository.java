package org.sgitario.data;

import org.sgitario.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {
    private static final Map<String, Book> BOOKS = new ConcurrentHashMap<>();

    public List<Book> getBooks() {
        return new ArrayList<>(BOOKS.values());
    }

    public void addBook(Book book) {
        BOOKS.put(book.title(), book);
    }

    public void deleteAll() {
        BOOKS.clear();
    }

    public boolean deleteBookByTitle(String title) {
        var book = BOOKS.remove(title);
        return book != null;
    }
}
