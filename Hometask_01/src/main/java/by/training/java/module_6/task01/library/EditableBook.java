package by.training.java.module_6.task01.library;

import by.training.java.module_6.task01.logic.Dispatch;

import java.util.ArrayList;
import java.util.Scanner;

public class EditableBook extends NewBook {

    private ArrayList<Book> newCatalogBooks = new ArrayList<>();

    public EditableBook(int currentBookNumber, ArrayList<Book> books) {
        super();
        setNewCatalogBooks(books);

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String number;

        System.out.println("1. Edit book " + "| 2. Delete book " + "| 0. Exit from the submenu.");

        System.out.print("Make your choice >> ");
        number = sc.nextLine();

        switch (number) {
            case "1":
                do {
                    System.out.println("1. Change book title " + "| 2. Change book author "
                            + "| 3. Change book type " + "| 4. Change book description " + "| 0. Exit from the submenu.");

                    System.out.print("Make your choice >> ");
                    number = sc.nextLine();

                    switch (number) {
                        case "1":
                            System.out.print("Enter book title >> ");
                            getNewCatalogBooks().get(currentBookNumber).setBookTitle(sc.nextLine());
                            break;
                        case "2":
                            System.out.print("Enter book author >> ");
                            getNewCatalogBooks().get(currentBookNumber).setAuthor(sc.nextLine());
                            break;
                        case "3":
                            getNewCatalogBooks().get(currentBookNumber).setBookVersion(setNewBookType());
                            break;
                        case "4":
                            getNewCatalogBooks().get(currentBookNumber).setBookDescription(setNewBookDescription());

                            System.out.println("Sending users by email a book with an edited description: ");

                            Dispatch dispatch = new Dispatch();
                            dispatch.dispatchToAllUsers("Added new book description "
                                            + getNewCatalogBooks().get(currentBookNumber).getBookTitle(),
                                    getNewCatalogBooks().get(currentBookNumber).toString());
                            break;
                    }
                } while (!number.matches("[0]"));
                break;

            case "2":
                getNewCatalogBooks().remove(currentBookNumber);
                break;

            case "0":
                break;
        }
    }

    public ArrayList<Book> getNewCatalogBooks() {
        return newCatalogBooks;
    }

    public void setNewCatalogBooks(ArrayList<Book> newCatalogBooks) {
        this.newCatalogBooks = newCatalogBooks;
    }
}
