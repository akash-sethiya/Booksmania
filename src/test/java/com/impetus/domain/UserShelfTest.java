package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UserShelfTest {

    @InjectMocks
    private UserShelf shelf;
    
    private Integer shelfId;

    /** The user. */
    private Users user;

    /** The book. */
    private Book book;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserShelf() {
        shelf.setBook(book);
        shelf.setShelfId(shelfId);
        shelf.setUser(user);
        
        assertEquals(book,shelf.getBook());
        assertEquals(user,shelf.getUser());
        assertEquals(shelfId,shelf.getShelfId());
        
    }

}
