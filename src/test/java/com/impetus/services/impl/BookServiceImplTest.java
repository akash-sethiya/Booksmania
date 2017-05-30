package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.BookDAO;
import com.impetus.dao.impl.BookDAOImpl;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Class BookServiceImplTest.
 */
@SuppressWarnings( "null" )
public class BookServiceImplTest {

	/** The book dao. */
	@Autowired
	private BookDAO bookDAO;
	
	/** The book service. */
	private BookServiceImpl bookService = new BookServiceImpl();

	private Logger logger=LoggerFactory.getLogger(BookServiceImpl.class);
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		bookDAO = mock(BookDAOImpl.class);
	}

	/**
	 * Test get book.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testGetBook() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		List<Book> expectedBookList = new ArrayList<Book>();
		
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
		
		expectedBookList.add(book);
		when(bookDAO.getBook()).thenReturn(expectedBookList);
		assertEquals(expectedBookList, bookService.getBook());
	}
	
	@Test(expected=TestException.class)
    public void testGetBookDataCatchBlock()throws TestException
    {
        try{
            logger.info("testing getBookData catch block.");
            bookService.setBookDAO(bookDAO);
            doThrow(DAOException.class).when(bookDAO).getBook();
            assertEquals(new ServiceException(), bookService.getBook());
        }
        catch(Exception e)
        {
            logger.error("exception occured during catch block testing of get book data::::"+e);
            throw new TestException("exception occured in test of get book data catch block",e);
        }
        
    }
	/**
	 * Test add book data.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testAddBookData() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		Language lang = new Language();
		BookCategory category = new BookCategory();
		Book book = new Book();
		book.setAuthor("a");
		book.setTitle("a");
		book.setDescription("a");
		book.setPublisher("a");
		book.setAvailability(1);
		book.setQuantity(1);
		book.setBookLanguage(lang);
		book.setImageName("a");
		book.setISBN("123");
		book.setCategory(category);
		when(bookDAO.addBookData(book, lang, category, "a")).thenReturn(book);
		assertEquals(book.getBookId(),
				bookService.addBookData(book, lang, category, "a").getBookId());
	}
	
	@Test(expected=TestException.class)
    public void testAddBookDataCatchBlock()throws TestException
    {
        try{
            logger.info("testing addBookData catch block.");
            bookService.setBookDAO(bookDAO);
            Book book = null;
            Language lang = null;
            BookCategory category = null;
            
            doThrow(DAOException.class).when(bookDAO).addBookData(book, lang, category, "a");
            assertEquals(new ServiceException(),bookService.addBookData(book, lang, category, "a").getBookId());
            
        }
        catch(Exception e)
        {
            logger.error("exception occured in add book data catch block::::"+e);
            throw new TestException("exception occured in add book data catch block",e);
        }
    }

	/**
	 * Test get book list by id.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testGetBookListById() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		
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
		
		when(bookDAO.getBook(book.getBookId()))
				.thenReturn(book);
		assertEquals(book,bookService.getBook(book.getBookId()));
	}
	@Test(expected=TestException.class)
	public void testGetBookListByIdCatchBlock()throws TestException
	{
	    try{
	        logger.info("testing getBookListById catch block.");
	        Book book=null;
	        doThrow(DAOException.class).when(bookDAO).getBook(book.getBookId());
	        assertEquals(new ServiceException(),bookService.getBook(book.getBookId()));
	    }
	    catch(Exception e)
	    {
	        logger.error("error occured in getBookListById catch block::::"+e);
	        throw new TestException("Exception occured in getBookListById catch Block",e);
	    }
	    
	}

	/**
	 * Test update book.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testUpdateBook() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		Book expectedBook = new Book();
		Book book = new Book();
		when(bookDAO.updateBook(book)).thenReturn(expectedBook);
		assertEquals(expectedBook.getTitle(), bookService.updateBook(book)
				.getTitle());
	}
	
	@Test(expected=TestException.class)
    public void testUpdateBookCatchBlock()throws TestException
    {
        try{
            logger.info("testing update book catch block");
            bookService.setBookDAO(bookDAO);
            Book book = null;
            doThrow(DAOException.class).when(bookDAO).updateBook(book);
            assertEquals(new ServiceException(), bookService.updateBook(book).getTitle());
        }
        catch(Exception e)
        {
            logger.error("error in testing update book catch block::::"+e);
            throw new TestException("exception during testing update book catch block",e);
        }
    }

	/**
	 * Test delete book.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testDeleteBook() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		Book expectedBook = new Book();
		int bookId=1;
		when(bookDAO.deleteBook(bookId)).thenReturn(expectedBook);
		assertEquals(null, bookService.deleteBook(expectedBook.getBookId()));
	}
	
	@Test(expected=TestException.class)
	public void testDeleteBookCatchBlock()throws TestException
	{
	    try{
	        logger.info("testing delete book catch block");
	        bookService.setBookDAO(bookDAO);
	        Book expectedBook = null;
	        int bookId=0;
	        doThrow(DAOException.class).when(bookDAO).deleteBook(bookId);
	        assertEquals(new ServiceException(), bookService.deleteBook(expectedBook.getBookId()));
	    }
	    catch(Exception e)
	    {
	        logger.error("exception occured in testing delete book catch block::::"+e);
	        throw new TestException("during delete book catch block",e);
	    }
	}

	/**
	 * Test get book by searchquery.
	 * @throws DAOException 
	 * @throws ServiceException 
	 */
	@Test
	public void testGetBookBySearchquery() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book());
		String searchQuery = "lords";
		when(bookDAO.getBook(searchQuery)).thenReturn(bookList);
		assertEquals(bookList.size(), bookService.getBook(searchQuery).size());
	}
	
	@Test(expected=TestException.class)
    public void testGetBookBySearchQuery()throws TestException
    {
        try{
            logger.info("testing getBookBySearchQuery catch block method");
            bookService.setBookDAO(bookDAO);
            String searchQuery = "lords";
            doThrow(DAOException.class).when(bookDAO).getBook(searchQuery);
            assertEquals(new ServiceException(), bookService.getBook(searchQuery).size());
        }
        catch(Exception e)
        {
            logger.error("exception hitted in get bookbysaerchquery catch block method::::"+e);
            throw new TestException("exception occured during testing bookBySearchQuery catch block",e);
            
        }
    }
    
    

	/**
	 * Test get book author.
	 * @throws ServiceException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetBookAuthor() throws DAOException, ServiceException {
		bookService.setBookDAO(bookDAO);
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book());
		when(bookDAO.getBookAuthor()).thenReturn(bookList);
		assertEquals(bookList, bookService.getBookAuthor());
	}
	
	
   
    @Test(expected=TestException.class)
	public void testGetBookAuthorCatchBlock()throws TestException
	{
	    try{
	        logger.info("testing getBookAuthor method.");
	        bookService.setBookDAO(bookDAO);
	        doThrow(DAOException.class).when(bookDAO).getBookAuthor();
	        assertEquals(new ServiceException(), bookService.getBookAuthor());
	    }
	    catch(Exception e)
	    {
	        logger.error("Exception in testing getBookAuthorcatch block::::"+e);
	        throw new TestException("Exception in testing get Book author catch block.",e);
	    }
        
	}
    
    @Test
    public void testRecentlyAddedBook()throws DAOException,ServiceException
    {
           logger.info("testing recently add recent book method");
            bookService.setBookDAO(bookDAO);
            List<Book> bookList=new ArrayList<Book>();
            when(bookDAO.recentlyAddedBooks()).thenReturn(bookList);
            assertEquals(bookList,bookService.recentlyAddedBooks());
    }
    
    @Test(expected=TestException.class)
    public void testRecentlyAddedBookCatchBlock()throws TestException
    {
        try{
            logger.info("testing recently added book catch block.");
            bookService.setBookDAO(bookDAO);
            doThrow(DAOException.class).when(bookDAO).recentlyAddedBooks();
            assertEquals(new ServiceException(),bookService.recentlyAddedBooks());
        }
        catch(Exception e)
        {
            logger.error("exception comes in testing recently added book catch block:::"+e);
            throw new TestException("exception occured in recently added book catch block.",e);
        }
    }
	

}
