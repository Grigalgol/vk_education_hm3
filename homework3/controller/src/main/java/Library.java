import books_factory.BooksFactory;
import entity.Book;
import exceptions.EmptyCellException;
import exceptions.SmallCapacityException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final Book[] cells;

    public Library(int capacity, @NotNull BooksFactory booksFactory) throws SmallCapacityException {
        cells = new Book[capacity];

        List<Book> booksFromFile = new ArrayList<>(booksFactory.books());
        if(booksFromFile.size() > capacity) {
            throw new SmallCapacityException("the capacity is less than the number of books returned by the factory");
        }

        for (int i = 0; i < booksFromFile.size(); i++) {
            cells[i] = booksFromFile.get(i);
        }
    }


    public Book[] getCells() {
        return cells;
    }

    public Book getBookFromCell (int numberOfCell) throws SmallCapacityException, EmptyCellException {
        if(numberOfCell >= cells.length) throw new SmallCapacityException("cell number " + numberOfCell + " does not exist");
        if(cells[numberOfCell] == null) throw new EmptyCellException("cell number " + numberOfCell + " is Empty");

        System.out.println("Cell number " + numberOfCell + " [" + cells[numberOfCell] + "] ");
        Book book = cells[numberOfCell];
        cells[numberOfCell] = null;
        return book;
    }

    public void setBookInCell (@NotNull Book book) throws EmptyCellException {
        int numberEmptyCell = findEmptyCell();
        if(numberEmptyCell == -1) throw new EmptyCellException("the empty cell does not exist");
        else cells[numberEmptyCell] = book;
    }

    //метод ищет первую пустую ячейку, если таких нет, возвращает -1
    private int findEmptyCell() {
        for (int index = 0; index < cells.length; index++) {
            if(cells[index] == null) {
                return index;
            }
        }
        return -1;
    }

    public void printContentToTheConsole() {
        for (int numberOfCell = 0; numberOfCell < cells.length; numberOfCell++) {
            System.out.println("Cell number " + numberOfCell + " [" + cells[numberOfCell] + "] ");
        }
    }

}
