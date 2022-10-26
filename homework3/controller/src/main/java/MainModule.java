import books_factory.BooksFactory;
import books_factory.FileBookFactory;
import com.google.inject.AbstractModule;
import org.jetbrains.annotations.NotNull;

public class MainModule extends AbstractModule {

    private final String sourcePath;

    public MainModule(@NotNull String  sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(sourcePath));
    }
}
