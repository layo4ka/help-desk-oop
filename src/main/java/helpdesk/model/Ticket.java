package helpdesk.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Заявка (задания 2-5, уровень 1 №1-№5).
 * Инкапсуляция: все поля закрытые, setStatus отсутствует —
 * статус меняется только через бизнес-методы,
 * и сам Ticket контролирует допустимые переходы состояния.
 */
public class Ticket {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final long id;
    private final String title;
    private final String description;
    private final TicketPriority priority;      // уровень 1 №1
    private final LocalDateTime createdAt;      // уровень 1 №4
    private TicketStatus status;

    public Ticket(long id, String title, String description, TicketPriority priority) {
        // уровень 1 №3: пустое название заявки создавать нельзя
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Заявку с пустым названием создавать нельзя");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = (priority == null) ? TicketPriority.MEDIUM : priority;
        this.createdAt = LocalDateTime.now();   // время создания фиксируется автоматически
        this.status = TicketStatus.NEW;
    }

    // ---- бизнес-действия вместо setStatus (задания 3 и 4) ----

    public void startProcessing() {
        if (status != TicketStatus.NEW) {
            System.out.println("Ошибка: в работу можно взять только новую заявку");
            return;
        }
        status = TicketStatus.IN_PROGRESS;
    }

    public void resolve() {
        if (status != TicketStatus.IN_PROGRESS) {
            System.out.println("Ошибка: решить можно только заявку в работе");
            return;
        }
        status = TicketStatus.RESOLVED;
    }

    public void close() {
        if (status != TicketStatus.RESOLVED) {
            System.out.println("Ошибка: закрыть можно только решённую заявку");
            return;
        }
        status = TicketStatus.CLOSED;
    }

    // уровень 1 №2: отменить нельзя только уже закрытую заявку
    public void cancel() {
        if (status == TicketStatus.CLOSED) {
            System.out.println("Ошибка: закрытую заявку отменить нельзя");
            return;
        }
        status = TicketStatus.CANCELLED;
    }

    // ---- только чтение состояния ----

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // уровень 1 №5: красивый вывод заявки одной строкой
    @Override
    public String toString() {
        return "#" + id + " " + title + " | " + status
                + " | приоритет: " + priority
                + " | создана: " + createdAt.format(FORMATTER);
    }
}
