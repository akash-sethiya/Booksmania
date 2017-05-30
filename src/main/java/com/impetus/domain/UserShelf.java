package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class UserShelf.
 */
@Entity
public class UserShelf implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The shelf id. */
    @Id
    @GeneratedValue
    private Integer shelfId;

    /** The user. */
    @OneToOne
    private Users user;

    /** The book. */
    @ManyToOne
    private Book book;

    /**
     * Gets the shelf id.
     * 
     * @return the shelf id
     */
    public Integer getShelfId() {
        return shelfId;
    }

    /**
     * Sets the shelf id.
     * 
     * @param shelfId
     *            the new shelf id
     */
    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * Gets the user.
     * 
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * Sets the user.
     * 
     * @param user
     *            the new user
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * Gets the book.
     * 
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book.
     * 
     * @param book
     *            the new book
     */
    public void setBook(Book book) {
        this.book = book;
    }

}
