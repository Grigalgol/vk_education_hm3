import books_factory.BooksFactory;
import books_factory.FileBookFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import entity.Author;
import entity.Book;
import exceptions.EmptyCellException;
import exceptions.SmallCapacityException;
import net.lamberto.junit.GuiceJUnitRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private BooksFactory booksFactory;
    private List<Book> library;

    @BeforeEach
    public void beforeEachTests() {
        booksFactory = Mockito.mock(BooksFactory.class);
        library = Arrays.asList(
                new Book("Book 0", new Author("Author0")),
                new Book("Book 1", new Author("Author0")),
                new Book( "Book 2", new Author("Author0")));

        Mockito.when(booksFactory.books()).thenReturn(library);
    }

    //Библиотека бросает исключение при создании, если ее вместимость меньше чем количество книг, возвращаемое фабрикой.
    @Test
    public void testCapacityThatLessThanCountBooks() {
        assertThrows(SmallCapacityException.class, () ->  new Library(library.size() - 1, booksFactory));
    }

    //При создании библиотеки все книги расставлены по ячейкам в порядке как они возвращаются фабрикой книг. Остальные ячейки пусты.
    @Test
    public void testCorrectBooksPositionInCell() throws SmallCapacityException, EmptyCellException {
        Library newLibrary = new Library(library.size(), booksFactory);
        for(int i = 0; i < library.size(); i++) {
            assertEquals(library.get(i), newLibrary.getBookFromCell(i));
        }
        for(int i = library.size(); i< newLibrary.getCells().length; i++) {
            assertNull(newLibrary.getBookFromCell(i));
        }
    }

}