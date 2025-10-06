package bookstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nonnull;

public class Library {

    private final Set<Book> books = new TreeSet<Book>(new ComparatorImpl());

    // ✅ УПРОЩЕНО: Убрана избыточная проверка null
    // @Nonnull аннотация гарантирует, что клиенты не передадут null
    public void addBook(@Nonnull Book newBook) {
        books.add(newBook);
    }

    public @Nonnull Iterable<? extends String> describeBooksBy(@Nonnull String author) {
        List<String> result = new ArrayList<String>();

        for (Book b : books) {
            if (!author.equals(b.getAuthor())) continue;

            // ИСПРАВЛЕНО: %d заменен на %s для строк
            result.add(String.format("%s: %s", b.getAuthor(), b.getTitle()));
        }

        return result;
    }

    private static class ComparatorImpl implements Comparator<Book>, Serializable {

        @Override
        public int compare(Book o1, Book o2) {
            // Сравнение автора (оба @Nonnull - безопасно)
            int r = o1.getAuthor().compareTo(o2.getAuthor());
            if (r != 0) return r;

            // Сравнение заголовка (оба @Nonnull - безопасно)
            r = o1.getTitle().compareTo(o2.getTitle());
            if (r != 0) return r;

            // БЕЗОПАСНОЕ сравнение подзаголовка (может быть null)
            String sub1 = o1.getSubtitle();  // @CheckForNull - может вернуть null
            String sub2 = o2.getSubtitle();  // @CheckForNull - может вернуть null

            // Обработка всех случаев null значений:
            if (sub1 == null && sub2 == null) return 0;    // оба null - равны
            if (sub1 == null) return -1;                   // null всегда меньше
            if (sub2 == null) return 1;                    // null всегда меньше

            // Оба не-null - обычное сравнение
            return sub1.compareTo(sub2);
        }

        // УЛУЧШЕНО: Используется нормальный serialVersionUID
        // 0L может вызвать проблемы при изменении класса
        private static final long serialVersionUID = 1L;
    }
}