import books_factory.BooksFactory;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import exceptions.SmallCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;

public class TestWithInject {

    @Inject
    private BooksFactory booksFactory;

    @BeforeEach
    public void beforeEachMethod() throws URISyntaxException {
        Path path = Path.of(Objects.requireNonNull(this.getClass().getResource("/books.txt")).toURI());
        final Injector injector = Guice.createInjector(new MainModule(path.toString()));
        injector.injectMembers(this);
    }

    //Библиотека бросает исключение при создании, если ее вместимость меньше чем количество книг, возвращаемое фабрикой.
    @Test
    public void testCapacityThatLessThanCountBooks() {
        assertThrows(SmallCapacityException.class, () -> new Library(1, booksFactory));
    }
}
