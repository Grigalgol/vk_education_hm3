import books_factory.BooksFactory;
import books_factory.FileBookFactory;
import exceptions.SmallCapacityException;

import javax.inject.Inject;

public class LibraryFactory {

    @Inject
    private BooksFactory booksFactory;

    public Library library(int capacity) throws SmallCapacityException {
        return new Library(capacity, booksFactory);
    }

}
