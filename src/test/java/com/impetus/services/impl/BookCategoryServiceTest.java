package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.BookCategoryDAO;
import com.impetus.domain.BookCategory;


public class BookCategoryServiceTest 
{
	
	@InjectMocks
	private BookCategoryServiceImpl bookCategoryServiceImpl;
	
	@Mock
    BookCategoryDAO bookCategoryDAO;
	
	private Logger logger = LoggerFactory.getLogger(BookCategoryServiceImpl.class);
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBookCategory() throws TestException, DAOException, ServiceException
	{
	    try{
	        logger.info("Testing getBookCategory() method");
	        List<BookCategory> list=new ArrayList<BookCategory>();
	        BookCategory category=new BookCategory();
	        category.setCategory("English");
	        category.setCategoryId(1);
	        list.add(category);
	        when(bookCategoryDAO.getBookCategory()).thenReturn(list);
	        assertEquals(list,bookCategoryServiceImpl.getBookCategory());
	    }
	    catch(Exception e)
	    {
	        logger.error("Exception occured in testing bookcategory::::"+e);
	        throw new TestException("Exception occured during testing bookCategory",e);
	    }
	}
	
	@Test
	public void testGetBookCategoryByCategory() throws DAOException, ServiceException, TestException
	{
	    try{
	        logger.info("Testing getBookCategoryByCategory() method");
	        String category="English";
	        BookCategory bookCategory=new BookCategory();
	        bookCategory.setCategory(category);
	        when(bookCategoryDAO.getBookCategory(category)).thenReturn(bookCategory);
	        assertEquals(bookCategory,bookCategoryServiceImpl.getBookCategory(category));
	    }
	    catch(Exception e)
	    {
	        logger.error("Exception occured in testing bookcategory by category::::"+e);
	        throw new TestException("Exception occured during testing bookcategory by category.",e);
	    }
	}

	@SuppressWarnings("unchecked")
    @Test(expected=TestException.class)
	public void testGetBookCategoryByCategoryCatchException() throws TestException
	{
	    try{
	        logger.info("Testing book category by category catch block");
	        String category="English";
	        BookCategory bookCategory=null;
	        when(bookCategoryDAO.getBookCategory(category)).thenThrow(DAOException.class);
	        assertEquals(bookCategory,bookCategoryServiceImpl.getBookCategory(category));
	    }
	    catch(Exception e)
	    {
	        logger.error("Exception hit in testing exception in bookcategory by category method::::"+e);
	        throw new TestException("exception occured in testing catch block in book category by category",e);
	    }
	}
	
	@SuppressWarnings({ "unchecked","unused" })
    @Test(expected=TestException.class)
	public void testGetBookCategoryCatchException() throws DAOException, ServiceException, TestException
	{
	    try{
	        logger.info("testing book category catch block");
	        List<BookCategory> list=null;
	        BookCategory category=null;
	        when(bookCategoryDAO.getBookCategory()).thenThrow(DAOException.class);
	        assertEquals(list,bookCategoryServiceImpl.getBookCategory());
	    }
	    catch(Exception e)
	    {
	        logger.error("Exception hit in testing catch block of getBookCategory method.::::"+e);
	        throw new TestException("exception hitted in testing of catch block of getBookCategory.",e);
	    }
	}
}
