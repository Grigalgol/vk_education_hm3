import com.google.inject.Guice;
import com.google.inject.Injector;

import entity.Book;
import exceptions.EmptyCellException;
import exceptions.SmallCapacityException;
import org.jetbrains.annotations.NotNull;


public class Application {
    public static void main(@NotNull String[] args) throws SmallCapacityException, EmptyCellException {
        final Injector injector = Guice.createInjector(new MainModule(args));
        Library library =  injector.getInstance(LibraryFactory.class).library(Integer.parseInt(args[0]));
    }
}
