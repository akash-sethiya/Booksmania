package com.impetus.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
public class BookCategoryTest {

    @InjectMocks
    private BookCategory bookCategory;
    
    private int categoryId;
    private String category;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBookCategory() {
        bookCategory.setCategory(category);
        bookCategory.setCategoryId(categoryId);
        
        assertEquals(category,bookCategory.getCategory());
        assertEquals(categoryId,bookCategory.getCategoryId());
    }

    

}
