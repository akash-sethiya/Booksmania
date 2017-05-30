package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.DAORuntimeException;
import com.impetus.dao.impl.BookDAOImpl;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;
import com.impetus.services.api.BookCategoryService;
@SuppressWarnings({"rawtypes","unchecked","serial"})
public class BookDAOImplTest {

	@InjectMocks
	private BookDAOImpl bookDAOImpl;
	
	@Mock
	private HibernateTemplate hibernateTemplate;
	
	@Mock
	private BookCategoryService bookCategoryService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddBookData() throws DAOException {
		Book book=new Book();
		
		Language lang=new Language();
		lang.setLanguage("Hindi");
		lang.setLanguageId(1);
		
		BookCategory category=new BookCategory();
		category.setCategory("Autobiography");
		category.setCategoryId(1);
		
		book.setAuthor("test");
		book.setAvailability(10);
		book.setBookLanguage(lang);
		book.setCategory(category);
		book.setCount(10);
		book.setDescription("test");
		book.setImageName("test.jpg");
		book.setISBN("test");
		book.setPublisher("test");
		book.setQuantity(10);
		
		hibernateTemplate.save(book);
		assertEquals(book.getBookId(),bookDAOImpl.addBookData(book, lang, category, "test.jpg").getBookId());
	}
	
	@Test(expected=DAOException.class)
	public void testAddBookDataCatchBlock() throws DAOException {
	    try{
	        Book book=new Book();
	        Language lang=new Language();
	        BookCategory category=new BookCategory();
	        doThrow(DAOException.class).when(hibernateTemplate).save(book);
	        bookDAOImpl.addBookData(book,lang,category,"test.jpg");
	    }
	    catch(DAOException e)
	    {
	        throw new DAOException();
	    }
	}
	@Test(expected=DAORuntimeException.class)
    public void testAddBookDataCatchBlockDataAccess() throws DAOException {
        try{
            Book book=new Book();
            Language lang=new Language();
            BookCategory category=new BookCategory();
            doThrow(new DataAccessException("") {}).when(hibernateTemplate).save(book);
            bookDAOImpl.addBookData(book,lang,category,"test.jpg");
        }
        catch(DataAccessException e)
        {
            throw new DAORuntimeException();
        }
    }
	
	@Test
	public void testGetBook() throws DAOException
	{
		List bookList=new ArrayList();
		when(hibernateTemplate.find("from Book")).thenReturn(bookList);
		assertEquals(bookList.size(),bookDAOImpl.getBook().size());
	}
	
	@Test(expected=DAOException.class)
	public void testGetBookCatchBlock() throws DAOException{
	    try{
	        when(hibernateTemplate.find("from Book")).thenThrow(NullPointerException.class);
	        bookDAOImpl.getBook();
	    }
	    catch(DAOException e){
	        throw new DAOException();
	    }
	}
	
	@Test(expected=DAORuntimeException.class)
    public void testGetBookCatchBlockDataAccess() throws DAOException{
        try{
            when(hibernateTemplate.find("from Book")).thenThrow(new DataAccessException(""){});
            bookDAOImpl.getBook();
        }
        catch(DataAccessException e){
            throw new DAORuntimeException();
        }
    }
	
	@Test
	public void testGetBookByBookId() throws DAOException
	{
		int bookId=1;
		Book book=new Book();
		
		Language lang=new Language();
		lang.setLanguage("Hindi");
		lang.setLanguageId(1);
		
		BookCategory category=new BookCategory();
		category.setCategory("Autobiography");
		category.setCategoryId(1);
		
		book.setAuthor("test");
		book.setAvailability(10);
		book.setBookLanguage(lang);
		book.setCategory(category);
		book.setCount(10);
		book.setDescription("test");
		book.setImageName("test.jpg");
		book.setISBN("test");
		book.setPublisher("test");
		book.setQuantity(10);
		when(hibernateTemplate.get(Book.class,bookId)).thenReturn(book);
		assertEquals(book.getBookId(),bookDAOImpl.getBook(bookId).getBookId());
	}
	
	@Test(expected=DAOException.class)
	public void testGetBookByBookIdCatchBlock() throws DAOException
    {
	    try{
	        int bookId=1;
	        when(hibernateTemplate.get(Book.class,bookId)).thenThrow(DAOException.class);
	        bookDAOImpl.getBook(bookId);
	    }
	    catch(DAOException e){
	        throw new DAOException();
	    }
    }
	
	@Test(expected=DAORuntimeException.class)
    public void testGetBookByBookIdCatchBlockDataAccess() throws DAOException
    {
        try{
            int bookId=1;
            when(hibernateTemplate.get(Book.class,bookId)).thenThrow(new DataAccessException(""){});
            bookDAOImpl.getBook(bookId);
        }
        catch(DataAccessException e){
            throw new DAORuntimeException();
        }
    }
	
	@Test
	public void testUpdateBook() throws DAOException
	{
		Book book=new Book();
		
		Language lang=new Language();
		lang.setLanguage("Hindi");
		lang.setLanguageId(1);
		
		BookCategory category=new BookCategory();
		category.setCategory("Autobiography");
		category.setCategoryId(1);
		
		book.setAuthor("test");
		book.setAvailability(10);
		book.setBookLanguage(lang);
		book.setCategory(category);
		book.setCount(10);
		book.setDescription("test");
		book.setImageName("test.jpg");
		book.setISBN("test");
		book.setPublisher("test");
		book.setQuantity(10);
		
		doNothing().when(hibernateTemplate).update(book);
		assertEquals(book,bookDAOImpl.updateBook(book));
		
	}
	
	@Test(expected=DAOException.class)
    public void testUpdateBookCatchBlock() throws DAOException
    {
	    try{
	        Book book=new Book();
	        doThrow(NullPointerException.class).when(hibernateTemplate).update(book);
	        bookDAOImpl.updateBook(book);
	        
	    }
	    catch(NullPointerException e)
	    {
	        throw new DAOException();
	    }
    }
	
	@Test(expected=DAORuntimeException.class)
    public void testUpdateBookCatchBlockDataAccess() throws DAOException
    {
        try{
            Book book=new Book();
            doThrow(new DataAccessException("") {}).when(hibernateTemplate).update(book);
            bookDAOImpl.updateBook(book);
            
        }
        catch(DataAccessException e)
        {
            throw new DAORuntimeException();
        }
    }
	
	@Test
	public void testGetBookAuthor() throws DAOException
	{
		List list=new ArrayList();
		when(hibernateTemplate.find("SELECT DISTINCT author FROM Book")).thenReturn(list);
		assertEquals(list,bookDAOImpl.getBookAuthor());
	}
	
	@Test(expected=DAOException.class)
	public void testGetBookAuthorCatchBlock()throws DAOException
	{
	    try{
	        when(hibernateTemplate.find("SELECT DISTINCT author FROM Book")).thenThrow(NullPointerException.class);
	        bookDAOImpl.getBookAuthor();
	    }
	    catch(NullPointerException e)
	    {
	        throw new DAOException();
	    }
	}
	
	@Test(expected=DAORuntimeException.class)
    public void testGetBookAuthorCatchBlockDataAccess()throws DAOException
    {
        try{
            when(hibernateTemplate.find("SELECT DISTINCT author FROM Book")).thenThrow(new DataAccessException(""){});
            bookDAOImpl.getBookAuthor();
        }
        catch(DataAccessException e)
        {
            throw new DAORuntimeException();
        }
    }
}
