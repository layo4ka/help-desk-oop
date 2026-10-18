package helpdesk.model;

/**
 * Приоритет заявки (уровень 1 №1).
 * Задаётся при создании заявки и доступен только для чтения.
 */
public enum TicketPriority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}
