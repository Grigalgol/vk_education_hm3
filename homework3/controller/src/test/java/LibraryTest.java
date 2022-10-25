import books_factory.BooksFactory;
import entity.Author;
import entity.Book;
import exceptions.EmptyCellException;
import exceptions.SmallCapacityException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @NotNull
    private BooksFactory booksFactory;
    @NotNull
    private List<Book> libraryFromBooksFactory;
    @NotNull
    private Library library;

    @BeforeEach
    public void beforeEachTests() throws SmallCapacityException {
        booksFactory = Mockito.mock(BooksFactory.class);
        libraryFromBooksFactory = Arrays.asList(
                new Book("Book 0", new Author("Author0")),
                new Book("Book 1", new Author("Author0")),
                new Book("Book 2", new Author("Author0")));

        Mockito.when(booksFactory.books()).thenReturn(libraryFromBooksFactory);
        library = new Library(libraryFromBooksFactory.size(), booksFactory);
    }

    //Библиотека бросает исключение при создании, если ее вместимость меньше чем количество книг, возвращаемое фабрикой.
    @Test
    public void testCapacityThatLessThanCountBooks() {
        assertThrows(SmallCapacityException.class, () -> new Library(libraryFromBooksFactory.size() - 1, booksFactory));
    }

    //При создании библиотеки все книги расставлены по ячейкам в порядке как они возвращаются фабрикой книг. Остальные ячейки пусты.
    @Test
    public void testCorrectBooksPositionInCell() throws SmallCapacityException, EmptyCellException {
        for (int i = 0; i < libraryFromBooksFactory.size(); i++) {
            assertEquals(libraryFromBooksFactory.get(i), library.getBookFromCell(i));
        }
        for (int i = libraryFromBooksFactory.size(); i < library.getCells().length; i++) {
            assertNull(library.getBookFromCell(i));
        }
    }

    //При взятии книги информация о ней и ячейке выводится.
    @Test
    public void testPrintInfoAboutBook() throws SmallCapacityException, EmptyCellException, IOException {

        //ридер-обертка на System.out
        try (
                PrintStream consoleOutput = System.out;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream stream = new PrintStream(outputStream)) {
            System.setOut(stream);

            int numberOfCell = 0;
            library.getBookFromCell(numberOfCell);
            assertEquals(
                    "Cell number " + numberOfCell + " [" + libraryFromBooksFactory.get(numberOfCell) + "]".trim(),
                    outputStream.toString().trim()
            );
            System.setOut(consoleOutput);
        }
    }

    //При попытке взять книгу из пустой ячейки библиотека бросает исключение.
    @Test
    public void testGetBookFromEmptyCell() throws SmallCapacityException {
        Library newLibrary = new Library(libraryFromBooksFactory.size()+2, booksFactory);
        assertThrows(EmptyCellException.class, () -> newLibrary.getBookFromCell(libraryFromBooksFactory.size()+1));
    }

    //При взятии книги возвращается именно та книга, что была в этой ячейке.
    @Test
    public void testReturnCertainBook() throws SmallCapacityException, EmptyCellException {
        Book book = library.getBookFromCell(1);
        assertEquals(book, libraryFromBooksFactory.get(1));
    }

    //При добавлении книги она размещается в первой свободной ячейке.
    @Test
    public void testSetBookInFirstEmptyCell() throws SmallCapacityException, EmptyCellException {
        Library newLibrary = new Library(libraryFromBooksFactory.size()+1, booksFactory);
        //первая пустая
        assertThrows(EmptyCellException.class, () -> newLibrary.getBookFromCell(libraryFromBooksFactory.size()));
        newLibrary.setBookInCell(new Book("Java VK", new Author("Gregory")));
        assertNotNull(newLibrary.getBookFromCell(libraryFromBooksFactory.size()));
    }

    //Если при добавлении книги свободных ячеек нет, библиотека бросает исключение.
    @Test
    public void testFailAddBook() throws SmallCapacityException {
            assertThrows(EmptyCellException.class, () -> library.setBookInCell(new Book("b1", new Author("griga"))));
    }

    //Вызов метода “напечатать в консоль содержимое” выводит информацию о содержимом ячеек библиотеки
    @Test
    public void testPrintBooksFromCells() throws IOException {
        assertEquals(expectedString(), actualString(library));
    }

    private String actualString(@NotNull Library library) throws IOException {
        String actual;

        //ридер-обертка на System.out
        try (
                PrintStream consoleOutput = System.out;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream stream = new PrintStream(outputStream)) {
            System.setOut(stream);
            library.printContentToTheConsole();
            actual = outputStream.toString();
            System.setOut(consoleOutput);
        }
        return actual;
    }

    private String expectedString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < libraryFromBooksFactory.size(); index++) {
            stringBuilder.append("Cell number ").append(index).append(" [").append(libraryFromBooksFactory.get(index)).append("]");
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

}