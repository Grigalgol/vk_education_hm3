import entity.Book;
import exceptions.SmallCapacityException;

import java.util.ArrayList;
import java.util.List;


public class LibraryFactory {

    private static BooksFactory booksFactory = new FileBookFactory("books.txt");

    public static Library library(int capacity) throws SmallCapacityException {
        Library library = new Library(capacity);
        Book[] books = library.getCells();

        List<Book> booksFromFile = new ArrayList<>(booksFactory.books());
        if(booksFromFile.size() > capacity) {
            throw new SmallCapacityException("the capacity is less than the number of books returned by the factory");
        }

        for (int i = 0; i < booksFromFile.size(); i++) {
            books[i] = booksFromFile.get(i);
        }

        return library;
    }

}
