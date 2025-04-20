package Module_2_Task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;



public class Main {
    public static void main(String[] args) {
        // comment

        Stream.of(
                        new Student("Ivan", 19) {{
                            addBook(new Book("Java 8 ", 400, 2014));
                            addBook(new Book("Clean Code", 450, 2008));
                            addBook(new Book("Design Patterns", 320, 1994));
                            addBook(new Book("To Kill a Mockingbird", 336, 2015));
                            addBook(new Book("The Great Gatsby", 338, 2020));
                        }},

                        new Student("Sasha", 22) {{
                            addBook(new Book("Clean Code", 450, 2008));
                            addBook(new Book("Effective Java", 350, 2017));
                            addBook(new Book("Spring Boot in Action", 380, 2015));
                            addBook(new Book("1984", 328, 2013));
                            addBook(new Book("The Great Gatsby", 338, 2020));
                        }},

                        new Student("Maria", 21) {{
                            addBook(new Book("Clean Code", 450, 2008));
                            addBook(new Book("Effective Java", 350, 2017));
                            addBook(new Book("Thinking in Java", 1200, 1998));
                            addBook(new Book("To Kill a Mockingbird", 336, 2015));
                            addBook(new Book("Pride and Prejudice", 432, 2018));
                        }}
                )
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted((b1, b2) -> Integer.compare(b1.getPageCount(), b2.getPageCount()))
                .distinct()
                .filter(book -> book.getYearPublished() > 2000)
                .limit(3)
                .findFirst()
                .ifPresentOrElse(
                        book -> System.out.println("Year of book: " + book.getYearPublished()),
                        () -> System.out.println("The book is null")
                );
    }
}






