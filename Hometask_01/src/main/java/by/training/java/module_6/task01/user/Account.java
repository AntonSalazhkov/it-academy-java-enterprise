package by.training.java.module_6.task01.user;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Account {

    private String username;
    private String email;
    private String password;
    private String userStatus;    // administrator or user
    private ArrayList<Account> accounts = new ArrayList<>();
    //private String directoryAccounts = "by\\training\\java\\module_6\\task01\\user\\Accounts.txt";
    private String directoryAccounts = "\\Accounts.txt";

    public Account() {
    }

    public Account(String username, String email, String password, String userStatus) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts.add(accounts);
    }

    public String getDirectoryAccounts() {
        return directoryAccounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        if (getUsername().compareToIgnoreCase("administrator") == 0) {
            setUserStatus("administrator");
        } else {
            setUserStatus("user");
        }
        return "You are logged in: " + getUsername() + ", your mail: " + getEmail() + ", your status: " + getUserStatus();
    }

    public void saveAccount() {
        if (getUsername() != null) {
            try (FileWriter fw = new FileWriter(directoryAccounts, true)) {
                fw.write(getUsername() + "!*#" + getEmail() + "!*#" + getPassword()
                        + "!*#" + getUserStatus() + System.getProperty("line.separator"));   // !*#  - разделитель для StringTokenizer
                fw.flush();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void readAccount() {

        try (FileReader fr = new FileReader(getDirectoryAccounts())) {
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(scan.nextLine(), "!*#");
                // добавляем сохраненные аккаунты из файла в ArrayList
                setAccounts(new Account(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
