package by.training.java.module_6.task01.logic;

import by.training.java.module_6.task01.user.Account;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class Logging {

    public Logging() {

        Account account = new Account();
        Account currentAccount;
        account.readAccount();

        boolean correctInput = false;
        Scanner sc = new Scanner(System.in);
        String line;
        int numberAccount = 0;


        while (!correctInput) {

            // даем ввести и логин и пароль, не уточняем что из них было введено неправильно
            System.out.print("Enter username: ");
            line = sc.nextLine();

            for (int i = 0; i < account.getAccounts().size(); i++) {
                if (line.compareToIgnoreCase(account.getAccounts().get(i).getUsername()) == 0) {
                    correctInput = true;
                    numberAccount = i;
                }
            }

            System.out.print("Enter password: ");
            line = sc.nextLine();

            if (correctInput && BCrypt.checkpw(line, account.getAccounts().get(numberAccount).getPassword())) {

                System.out.println(account.getAccounts().get(numberAccount).toString());
                currentAccount = account.getAccounts().get(numberAccount);
                Submenu submenu = new Submenu(currentAccount);
            } else {
                correctInput = false;
            }

            if (!correctInput) {
                System.out.println("Login or password entered incorrectly");
            }
        }
    }
}
