package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
public class BookTest {
    
    @InjectMocks
    private Book book;

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
    private String iSBN;

    /** The book language. */
    private Language bookLanguage;

    /** The category. */
    private BookCategory category;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    
    @Test
    public void testSetBook() {
        book.setAuthor(author);
        book.setAvailability(availability);
        book.setBookId(bookId);
        book.setBookLanguage(bookLanguage);
        book.setCategory(category);
        book.setCount(quantity);
        book.setDescription(description);
        book.setImageName(imageName);
        book.setISBN(iSBN);
        book.setPublisher(publisher);
        book.setQuantity(quantity);
        book.setTitle(title);
        
        assertEquals(author,book.getAuthor());
        assertEquals(availability,book.getAvailability());
        assertEquals(availability,book.isAvailability());
        assertEquals(bookId,book.getBookId());
        assertEquals(bookLanguage,book.getBookLanguage());
        assertEquals(category,book.getCategory());
        assertEquals(quantity,book.getCount());
        assertEquals(description,book.getDescription());
        assertEquals(imageName,book.getImageName());
        assertEquals(iSBN,book.getISBN());
        assertEquals(publisher,book.getPublisher());
        assertEquals(quantity,book.getQuantity());
        assertEquals(title,book.getTitle());
        
    }
}
