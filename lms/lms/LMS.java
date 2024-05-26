package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LMS {

    private List<Book> books;

    public LMS(){
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public boolean borrowBook(Book book, Student student) {
        boolean borrowed = false;
        // TODO must be implemented
        if (books.contains(book) && book.isAvailable()) {
            book.borrow();

            // Add the book to the student's list of borrowed books
            student.addBorrowedBook(book);
            borrowed = true;
        }

        return borrowed;
    }

    public boolean returnBook(Book book) {
        boolean returned = false;
        if (book != null && books.contains(book) && !book.isAvailable()) {
            book.available = true;
            returned = true;
        }
        return returned;
    }

    public void saveState(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.isAvailable());
                writer.newLine();
            }
            System.out.println("Library state saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            books.clear(); // Clear the existing book list
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                String title = bookData[0];
                String author = bookData[1];
                boolean available = Boolean.parseBoolean(bookData[2]);
                Book book = new Book(title, author);
                book.available = available;
                books.add(book);
            }
            System.out.println("Library state loaded from " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
