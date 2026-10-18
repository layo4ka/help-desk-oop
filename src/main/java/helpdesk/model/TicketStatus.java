package helpdesk.model;

/**
 * Статусы заявки (задание 5 + уровень 1 №2).
 * Набор допустимых значений ограничен на уровне языка.
 */
public enum TicketStatus {
    NEW,
    IN_PROGRESS,
    RESOLVED,
    CLOSED,
    CANCELLED
}
