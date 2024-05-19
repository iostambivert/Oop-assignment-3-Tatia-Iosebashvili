package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LMS {

    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        boolean removed = false;
        // TODO must be implemented
        if(books != null && books.contains(book)){
            removed = books.remove(book);
        }

        return removed;
    }

    public boolean borrowBook(Book book, Student student) {
        boolean borrowed = false;
        // TODO must be implemented
        // Use the existing removeBook method
        if (removeBook(book)) {
            // If the book was successfully removed from the library
            student.borrowBook(book);
            borrowed = true; // Mark the operation as successful
        }

        return borrowed;
    }

    public boolean returnBook(Book book) {
        boolean returned = false;
        // TODO must be implemented
        if(book != null){
            // add book back to the list of available books
            books.add(book);
            returned = true;

        }
        return returned;
    }

    public void saveState(String filePath) {
        // TODO must be implemented
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // Write the list of books to the file
            out.writeObject(books);
            System.out.println("Library state saved to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean loadState(Book book, String filePath) {
        boolean returned = false;
        // TODO must be implemented
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            // Read the book object from the file
            Book loadedBook = (Book) in.readObject();

            // Compare the loaded book with the provided book
            if (loadedBook.equals(book)) {
                // If the loaded book matches the provided book
                // Update the state of the provided book with the loaded book
                book.setTitle(loadedBook.getTitle());
                book.setAuthor(loadedBook.getAuthor());
                boolean loaded = true;
                System.out.println("Book state loaded from " + filePath);
            } else {
                System.out.println("Book state not found or does not match the provided book.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return returned;
    }

}
