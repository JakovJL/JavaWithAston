package Module_2_Task_2;


import java.util.Objects;


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

