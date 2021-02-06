package ru.totowka.frontend;

import ru.totowka.backend.contact.Contact;
import ru.totowka.backend.database.DataBase;
import ru.totowka.backend.searcher.ContactSearcher;
import ru.totowka.backend.searcher.ContactSearcherImpl;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс для определения взаимодействия с пользователем через консоль.
 */
public class Interface {
    private final DataBase dataBase;
    private final ContactSearcher contactSearcher;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Публичный конструктор.
     * @param dataBase база данных
     */
    public Interface(DataBase dataBase) {
        this.dataBase = Objects.requireNonNull(dataBase);
        this.contactSearcher = new ContactSearcherImpl(dataBase);
    }

    /**
     * Запуск считывания комманд.
     */
    public void execute() {
        String response;
        do {
            try {
                response = input("\nCommand Number (1 for help): ");
            } catch (Exception exception) {
                System.out.println("No input.");
                return;
            }
        } while(command(response));
    }

    /**
     * Метод для запроса ввода из консоли и считывание ответа.
     * @param message сообщение в консоль
     * @return ответ пользователя
     */
    private String input(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Запуск методов в зависимости от комманды пользователя
     * @param command комманда
     * @return true - если пользователь продолжает пользоваться книжкой, false - в противном случае.
     */
    private boolean command(String command) {
        switch (command) {
            case "1": // Help.
                System.out.println("1 - help.\n2 - add contact to phonebook.\n" +
                        "3 - delete contact from phonebook.\n4 - find contact in phonebook.\n" +
                        "5 - print all contacts.\n0 - exit.");
                break;

            case "2": // Add.
                boolean response = dataBase.add(new Contact(input("-> Name: "),
                        input("-> Surname: "), input("-> Patronymic: "),
                        input("-> Address: "), input("-> Email: "),
                        Arrays.stream(input("-> Numbers (divide by comma): ")
                                .split(",")).map(String::trim).toArray(String[]::new),
                        input("-> Birthday: ")));

                if(response) {
                    System.out.println("Contact successfully added.");
                } else {
                    System.out.println("Contact is already in your phonebook.");
                }
                break;

            case "3": // Delete.
                if(dataBase.remove(Integer.parseInt(input("-> Contact ID: ")))){
                    System.out.println("Successfully deleted.");
                } else {
                    System.out.println("No such Contact ID.");
                }
                break;

            case "4": // Find.
                String argument = input("1 - Search by Fullname." +
                        "\n2 - Seatch by Birthday." +
                        "\n3 - Search by Phone Numbers.\n-> Option: ");

                switch (argument) {
                    case "1":
                        Utils.printContacts(contactSearcher.searchByFullname
                                (input("-> Fullname query ")));
                        break;

                    case "2":
                        Utils.printContacts(contactSearcher.searchByBirthday
                                (input("-> Birthday query ")));
                        break;

                    case "3":
                        Utils.printContacts(contactSearcher.searchByPhoneNumber
                                (input("-> Phone Number query ")));
                        break;

                    default:
                        System.out.println("No such option.");
                }
                break;

            case "5": // Output all contacts.
                Utils.printContacts(dataBase.getContacts());
                break;

            case "0": // Exit.
                return false;

            default:
                System.out.println("No such command. Use help.");
        }

        return true;
    }
}
