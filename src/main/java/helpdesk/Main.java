package helpdesk;

import helpdesk.model.Administrator;
import helpdesk.model.Customer;
import helpdesk.model.SupportAgent;
import helpdesk.model.Ticket;
import helpdesk.model.TicketPriority;
import helpdesk.model.User;
import helpdesk.repository.TicketRepository;
import helpdesk.service.ConsoleNotificationService;
import helpdesk.service.NotificationService;
import helpdesk.service.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Точка входа. Итоговый обязательный сценарий (раздел 6 методички):
 * клиент создаёт заявку, специалист принимает её в работу,
 * заявка проходит NEW -> IN_PROGRESS -> RESOLVED -> CLOSED,
 * каждое действие сопровождается уведомлением, заявки хранятся в репозитории.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== HELP DESK ===");

        // 1-2. Пользователи системы
        Customer anna = new Customer(1, "Анна Петрова", "anna@mail.ru");
        SupportAgent sergey = new SupportAgent(2, "Сергей Иванов", "sergey@helpdesk.ru");
        Administrator oleg = new Administrator(3, "Олег Смирнов", "admin@helpdesk.ru");

        System.out.println("Клиент: " + anna.getName());
        System.out.println("Специалист: " + sergey.getName());
        System.out.println("Администратор: " + oleg.getName());

        // Полиморфизм (задания 8-9): один вызов — разные реализации
        System.out.println("\nРоли в системе:");
        List<User> users = new ArrayList<>();
        users.add(anna);
        users.add(sergey);
        users.add(oleg);
        for (User user : users) {
            user.performAction();
        }

        // 3. Клиент создаёт заявку
        Ticket ticket = new Ticket(
                1,
                "Не работает Wi-Fi",
                "Ноутбук не видит домашнюю сеть после перезагрузки",
                TicketPriority.HIGH
        );
        System.out.println("\nЗаявка #1: " + ticket.getTitle() + " | " + ticket.getStatus());

        // Сервис зависит от интерфейса уведомлений (задание 11)
        NotificationService notificationService = new ConsoleNotificationService();
        TicketService ticketService = new TicketService(notificationService);

        // 4-8. Жизненный цикл заявки NEW -> IN_PROGRESS -> RESOLVED -> CLOSED
        ticketService.startTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());

        ticketService.resolveTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());

        ticketService.closeTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());

        // 9-10. Репозиторий: не менее трёх заявок и вывод списка
        TicketRepository repository = new TicketRepository();
        repository.add(ticket);
        repository.add(new Ticket(
                2, "Ошибка приложения", "Программа падает при запуске", TicketPriority.CRITICAL));
        repository.add(new Ticket(
                3, "Не печатает принтер", "Принтер не отвечает на запросы", TicketPriority.LOW));

        System.out.println("\n=== СПИСОК ЗАЯВОК ===");
        for (Ticket t : repository.findAll()) {
            System.out.println(t); // сработает переопределённый toString()
        }
    }
}
