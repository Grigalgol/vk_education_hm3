import books_factory.BooksFactory;
import books_factory.FileBookFactory;
import com.google.inject.AbstractModule;
import org.jetbrains.annotations.NotNull;

public class MainModule extends AbstractModule {

    private final String[] args;

    public MainModule(@NotNull String [] args) {
        this.args = args;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(args[1]));
    }
}
