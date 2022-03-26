package by.training.java.module_6.task01.library;

import java.util.Scanner;

public class NewBook extends Book {

    public NewBook() {
        super();
    }

    public void setNewBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book title >> ");
        super.setBookTitle(sc.nextLine());

        System.out.print("Enter book author >> ");
        super.setAuthor(sc.nextLine());

        super.setBookVersion(setNewBookType());
        super.setBookDescription(setNewBookDescription());
    }

    // установку типа и описания вынес в методы

    public String setNewBookType() {

        boolean correctInput = false;
        Scanner sc = new Scanner(System.in);
        String bookVersion = "";

        do {

            System.out.print("Enter book type >> ");
            super.setBookVersion(sc.nextLine());
            if (getBookVersion().compareToIgnoreCase("Paper") == 0) {
                bookVersion = "Paper";
                correctInput = true;
            } else if (getBookVersion().compareToIgnoreCase("Electronic") == 0) {
                bookVersion = "Electronic";
                correctInput = true;
            } else {
                System.out.println("Incorrect input, book type can only be \"Paper\" or \"Electronic\"");
            }

        } while (!correctInput);
        return bookVersion;
    }

    public String setNewBookDescription() {

        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        String currentLine;

        System.out.print("Enter a description of the book (\\n - for a new line) >> ");
        currentLine = sc.nextLine();

        for (int i = 0; i < currentLine.length(); i++) {
            if (currentLine.charAt(i) == '\\' && currentLine.charAt(i + 1) == 'n') {
                stringBuilder.append(System.getProperty("line.separator"));
                i++;
            } else {
                stringBuilder.append(currentLine.charAt(i));
            }
        }
        return stringBuilder + getNewLine();
    }
}
