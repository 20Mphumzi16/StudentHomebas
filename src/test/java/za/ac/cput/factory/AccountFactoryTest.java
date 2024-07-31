package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Account;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountFactoryTest {

    private Account account1;
    private Account account2;
    private Account account3;

    @BeforeEach
    void setUp() {
        Name name = new Name.NameBuilder().setFirstName("John").setMiddleName("Mean").setLastName("Doe").build();
        Contact contact = new Contact.Builder()
                .setEmail("Johndoe@gmail.com")
                .setPhoneNumber("07821265349")
                .build();

        User user = new User.UserBuilder()
                .setUserId(1L)
                .setName(name)
                .setGender("Male")
                .setDateOfBirth(LocalDate.of(1990, 1, 1))
                .setPassword("securepassword")
                .setContact(contact)
                .build();

        account1 = AccountFactory.buildAccount(1, "Checking", "VISA", 1000.00,
                LocalDate.of(2024, 7, 1), "Pending", "Active", user);

        account2 = AccountFactory.buildAccount(2, "Savings", "MASTERCARD", 2000.00,
                LocalDate.of(2023, 7, 1), "Paid", "Inactive", user);

        account3 = AccountFactory.buildAccount(3, "Checking", "VISA", -500.00,
                LocalDate.of(2024, 7, 1), "Pending", "Active", user);
    }

    @Test
    void buildAccountWithValidDetails() {
        assertNotNull(account1);
        System.out.println(account1);
    }

    @Test
    void buildAccountWithPastDueDate() {
        assertNotNull(account2);
        System.out.println(account2);
    }

    @Test
    void buildAccountWithNegativeBalanceMustFail() {
        assertNull(account3);
        System.out.println(account3);
    }
}
