import books_factory.BooksFactory;
import books_factory.FileBookFactory;
import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {

    private final String[] args;

    public MainModule(String [] args) {
        this.args = args;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(args[1]));
    }
}
