package helpdesk.model;

/**
 * Клиент — создаёт заявки (задание 7).
 * IS-A: Customer является User.
 */
public class Customer extends User {

    public Customer(long id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void performAction() {
        System.out.println(getName() + " создаёт заявку");
    }
}
