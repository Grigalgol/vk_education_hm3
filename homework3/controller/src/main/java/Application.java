import entity.Book;
import exceptions.EmptyCellException;
import exceptions.SmallCapacityException;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws SmallCapacityException, EmptyCellException {
        Library library = LibraryFactory.library(105);
    }
}
