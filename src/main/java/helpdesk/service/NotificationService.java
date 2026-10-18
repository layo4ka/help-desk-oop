package helpdesk.service;

/**
 * Контракт отправки уведомлений (задание 10).
 * TicketService зависит от этого интерфейса, а не от конкретного способа доставки.
 */
public interface NotificationService {

    void send(String message);
}
