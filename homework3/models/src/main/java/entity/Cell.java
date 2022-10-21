package entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Cell {
    private Book book;
    private boolean isEmpty = true;

    public void setBook(Book book) {
        this.book = book;
        isEmpty = false;
    }

    public Book getBook() {
        isEmpty = true;
        return book;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

}

