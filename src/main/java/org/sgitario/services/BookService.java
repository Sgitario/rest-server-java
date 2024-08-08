package org.sgitario.services;

import org.sgitario.data.BookRepository;
import org.sgitario.model.Book;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService() {
        this(new BookRepository());
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    public String getAuthorByBook(String title) {
        return getBooks().stream().filter(book -> book.title().equals(title))
                .map(Book::author)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteBookByTitle(String title) {
        return bookRepository.deleteBookByTitle(title);
    }
}
