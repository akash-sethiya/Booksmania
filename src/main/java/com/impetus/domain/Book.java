package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Book.
 */
@Entity
public class Book implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The book id. */
    @Id
    @GeneratedValue
    private Integer bookId;

    /** The title. */
    private String title;

    /** The author. */
    private String author;

    /** The description. */
    private String description;

    /** The publisher. */
    private String publisher;

    /** The availability. */
    private Integer availability;

    /** The quantity. */
    private Integer quantity;

    /** The image name. */
    private String imageName;

    /** The isbn. */
    @Column(unique=true)
    private String iSBN;

    /** The book language. */
    @ManyToOne
    private Language bookLanguage;

    /** The category. */
    @ManyToOne
    private BookCategory category;

    /**
     * Instantiates a new book.
     */
    public Book() {
        super();
    }

    /**
     * Gets the isbn.
     * 
     * @return the isbn
     */
    public String getISBN() {
        return iSBN;
    }

    /**
     * Sets the isbn.
     * 
     * @param iSBN
     *            the new isbn
     */
    public void setISBN(String iSBN) {
        this.iSBN = iSBN;
    }

    /**
     * Gets the count.
     * 
     * @return the count
     */
    public Integer getCount() {
        return quantity;
    }

    /**
     * Sets the count.
     * 
     * @param quantity
     *            the new count
     */
    public void setCount(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the book language.
     * 
     * @return the book language
     */
    public Language getBookLanguage() {
        return bookLanguage;
    }

    /**
     * Sets the book language.
     * 
     * @param bookLanguage
     *            the new book language
     */
    public void setBookLanguage(Language bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    /**
     * Gets the availability.
     * 
     * @return the availability
     */
    public Integer getAvailability() {
        return availability;
    }

    /**
     * Gets the quantity.
     * 
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param quantity
     *            the new quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the image name.
     * 
     * @return the image name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the image name.
     * 
     * @param imageName
     *            the new image name
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets the book id.
     * 
     * @return the book id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book id.
     * 
     * @param bookId
     *            the new book id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author.
     * 
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     * 
     * @param author
     *            the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the publisher.
     * 
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher.
     * 
     * @param publisher
     *            the new publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Checks if is availability.
     * 
     * @return the integer
     */
    public Integer isAvailability() {
        return availability;
    }

    /**
     * Sets the availability.
     * 
     * @param availability
     *            the new availability
     */
    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    /**
     * Gets the category.
     * 
     * @return the category
     */
    public BookCategory getCategory() {
        return category;
    }

    /**
     * Sets the category.
     * 
     * @param category
     *            the new category
     */
    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
