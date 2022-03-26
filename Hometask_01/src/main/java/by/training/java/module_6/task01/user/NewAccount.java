package by.training.java.module_6.task01.user;

import by.training.java.module_6.task01.logic.Submenu;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class NewAccount extends Account {

    private String LOGIN_PATTERN = "^[a-zA-Z0-9]{2,15}$";
    private String PASSWORD_PATTERN = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}";
    private String EMAIL_PATTERN = "^.+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$";

    public NewAccount() {

        boolean correctInput = false;
        Account currentAccount;
        String line;
        readAccount();

        while (!correctInput) {

            System.out.print("Enter username: ");
            line = inputUser(LOGIN_PATTERN);
            if (line != null) {
                correctInput = true;

                for (int i = 0; i < getAccounts().size(); i++) {
                    if (line.compareToIgnoreCase(getAccounts().get(i).getUsername()) == 0) {
                        System.out.println("User with this name is already registered");
                        correctInput = false;
                    }
                }
                if (correctInput) {
                    super.setUsername(line);
                }
            } else {
                System.out.println("Invalid input. The username must consist of" +
                        "latin letters (a-z), numbers (0-9), length from 2 to 15 characters");
            }
        }
        correctInput = false;
        while (!correctInput) {
            System.out.print("Enter password: ");
            line = inputUser(PASSWORD_PATTERN);
            if (line != null) {
                super.setPassword(BCrypt.hashpw(line, BCrypt.gensalt()));
                correctInput = true;
            } else {
                System.out.println("Invalid input. The password must consist of " +
                        "Latin letters (a-z) and numbers (0-9), at least 8 characters long");
            }
        }
        correctInput = false;
        while (!correctInput) {
            System.out.print("Enter email: ");
            line = inputUser(EMAIL_PATTERN);
            if (line != null) {
                super.setEmail(line);
                correctInput = true;
            } else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println(toString());
        saveAccount();

        currentAccount = new Account(getUsername(), getEmail(), getPassword(), getUserStatus());
        Submenu submenu = new Submenu(currentAccount);
    }

    public String inputUser(String pattern) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String imput = sc.nextLine();

        if (imput.matches(pattern)) {
            return imput;
        } else {
            return null;
        }
    }
}
