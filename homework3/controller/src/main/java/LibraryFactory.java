import entity.Book;
import exceptions.SmallCapacityException;

import java.util.ArrayList;
import java.util.List;


public class LibraryFactory {

    private static BooksFactory booksFactory = new FileBookFactory("books.txt"); //todo изменить

    public static Library library(int capacity) throws SmallCapacityException {
        return new Library(capacity, booksFactory);
    }

}
