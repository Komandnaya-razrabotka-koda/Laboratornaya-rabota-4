package bookstore;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public class Book {

    private @Nonnull String author;
    private @Nonnull String title;
    private @CheckForNull String subtitle;

    //ДОБАВЛЕНО: Конструктор для обязательной инициализации полей
    public Book(@Nonnull String author, @Nonnull String title, @CheckForNull String subtitle) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
    }

    public @Nonnull String getAuthor() {
        return author;
    }

    // ИСПРАВЛЕНО: Изменена сигнатура метода на @CheckForNull
    // Теперь контракт соответствует реальному поведению
    public @CheckForNull String getSubtitle() {
        return subtitle;
    }

    public @Nonnull String getTitle() {
        return title;
    }
}