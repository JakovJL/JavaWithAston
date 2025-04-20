package Module_2_Task_2;


import java.util.ArrayList;
import java.util.List;

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