package books_factory;

import entity.Book;

import java.util.Collection;

public interface BooksFactory {
    Collection<Book> books();
}
