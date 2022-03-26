package by.training.java.module_6.task01.logic;

import by.training.java.module_6.task01.library.CatalogBook;
import by.training.java.module_6.task01.library.EditableBook;
import by.training.java.module_6.task01.library.NewBook;
import by.training.java.module_6.task01.user.Account;

import java.util.Scanner;

public class Submenu {

    public Submenu(Account account) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String number;
        CatalogBook catalogBook = new CatalogBook();
        catalogBook.readCatalogBook();
        int currentBookNumber;

        if (account.getUserStatus() == "administrator") {

            do {
                System.out.println("1. Catalog View " + "| 2. Book search " + "| 3. Edit book "
                        + "| 4. Add book " + "| 0. End of the program.");

                System.out.print("Make your choice >> ");
                number = sc.nextLine();

                switch (number) {
                    case "1":
                        catalogBook.showLibrary();
                        break;
                    case "2":
                        System.out.print("Enter book title >> ");
                        currentBookNumber = catalogBook.bookSearch(sc.nextLine());
                        if (currentBookNumber >= 0) {
                            System.out.println(catalogBook.getBooks().get(currentBookNumber).toString());
                        }
                        break;
                    case "3":
                        System.out.print("Enter book title >> ");
                        currentBookNumber = catalogBook.bookSearch(sc.nextLine());
                        if (currentBookNumber >= 0) {
                            EditableBook editableBook = new EditableBook(currentBookNumber, catalogBook.getBooks());
                            catalogBook.setEditableBook(editableBook.getNewCatalogBooks());
                            catalogBook.saveBook();
                            catalogBook.readCatalogBook();
                        }
                        break;
                    case "4":
                        NewBook newBook = new NewBook();
                        newBook.setNewBook();
                        boolean correctInput = catalogBook.setBooks(newBook);

                        catalogBook.saveBook();
                        catalogBook.readCatalogBook();

                        if (correctInput) {

                            System.out.println("Sending users an email to a book added to the catalog: ");

                            Dispatch dispatch = new Dispatch();
                            dispatch.dispatchToAllUsers("Added a new book in the catalog "
                                    + newBook.getBookTitle(), newBook.toString());
                        }
                        break;
                    case "0":
                        System.out.print("End of the program.");
                        break;
                }
            } while (!number.matches("[0]"));

        } else {

            do {
                System.out.println("1. Catalog View " + "| 2. Book search " + "| 3. Suggest adding a book to the catalog "
                        + "| 0. End of the program.");

                System.out.print("Make your choice >> ");
                number = sc.nextLine();

                switch (number) {
                    case "1":
                        catalogBook.showLibrary();
                        break;
                    case "2":
                        System.out.print("Enter book title >> ");
                        currentBookNumber = catalogBook.bookSearch(sc.nextLine());
                        if (currentBookNumber >= 0) {
                            System.out.println(catalogBook.getBooks().get(currentBookNumber).toString());
                        }
                        break;
                    case "3":
                        NewBook newBook = new NewBook();
                        newBook.setNewBook();

                        System.out.println("Sending a suggestion to add a new book to the administrator by email: ");

                        Dispatch dispatch = new Dispatch();
                        if (!dispatch.dispatchToAdministrator("Suggestion for adding a new book to the catalog"
                                , newBook.toString())) {

                            System.out.println(" The user with the status \"administrator\" is not registered");
                        }

                        break;
                    case "0":
                        System.out.print("End of the program.");
                        break;
                }
            } while (!number.matches("[0]"));
        }
    }
}
