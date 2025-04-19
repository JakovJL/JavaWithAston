package Module_2_Task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;



public class StudentsStreams {
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



class Student {
    private String name;
    private int age;
    private List<Book> books;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student [" +
                "name '" + name + '\'' +
                ", age =" + age +
                ']';
    }
}


class Book {
    private String title;
    private int pageCount;
    private int yearPublished;

    public Book(String title, int pageCount, int yearPublished) {
        this.title = title;
        this.pageCount = pageCount;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        return "Book [" +
                "name '" + title + '\'' +
                ", page count = " + pageCount +
                ", year = " + yearPublished +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                yearPublished == book.yearPublished &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pageCount, yearPublished);
    }
}




