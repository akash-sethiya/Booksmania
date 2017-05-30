package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.MyShelfDAO;
import com.impetus.dao.impl.MyShelfDAOImpl;
import com.impetus.domain.Address;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;
import com.impetus.services.impl.MyShelfServiceImpl;

public class MyShelfServiceImplTest {

	@Autowired private MyShelfDAO myShelfDAO;
	MyShelfServiceImpl myShelfService=new MyShelfServiceImpl();
	@Before
	public void setUp() throws Exception {
		myShelfDAO=mock(MyShelfDAOImpl.class);
	}

	@Test
	public void testGetShelfOfUser() throws DAOException, ServiceException {
		myShelfService.setMyShelfDAO(myShelfDAO);
		List<Book> expectedList=new ArrayList<Book>();
		
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
		
		expectedList.add(book);
		
		Users user=new Users();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmail("a@a.com");
		user.setEnabled(true);
		user.setRequestBookCount(0);
		user.setRole(new Role());
		user.setStatus(true);
		user.setSubscription(new Subscription());
		user.setUseraddress(new Address());
		user.setUserDetails(new UserDetails());
		user.setUserId(1);
		
		when(myShelfDAO.getShelf(user)).thenReturn(expectedList);
		assertEquals(expectedList,myShelfService.getShelf(user));
	}
	
	@Test(expected=TestException.class)
	public void testGestShelfOfUserCatchBlock() throws TestException
	{
	    try{
	        myShelfService.setMyShelfDAO(myShelfDAO);
	        Users user=new Users();
	        doThrow(DAOException.class).when(myShelfDAO).getShelf(user);
	        assertEquals(new ServiceException(),myShelfService.getShelf(user));
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exception occured in testing catch block of get shelf of user",e);
	    }
	}
	
	@Test
	public void testGetShelfItemsOfUser() throws DAOException, ServiceException
	{
		myShelfService.setMyShelfDAO(myShelfDAO);
		List<Integer> list=new ArrayList<Integer>();
		
		Users user=new Users();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmail("a@a.com");
		user.setEnabled(true);
		user.setRequestBookCount(0);
		user.setRole(new Role());
		user.setStatus(true);
		user.setSubscription(new Subscription());
		user.setUseraddress(new Address());
		user.setUserDetails(new UserDetails());
		user.setUserId(1);
		
		when(myShelfDAO.getShelfItems(user)).thenReturn(list);
		assertEquals(list,myShelfService.getShelfItems(user));
	}
	
	@Test(expected=TestException.class)
	public void testGetShelfItemsOfUserCatchBlock()throws TestException{
	    try{
	        myShelfService.setMyShelfDAO(myShelfDAO);
	        Users user=null;
	        doThrow(DAOException.class).when(myShelfDAO).getShelfItems(user);
	        assertEquals(new ServiceException(),myShelfService.getShelfItems(user));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	    
	}
	
	@Test
	public void tstGetShelfItems() throws DAOException, ServiceException
	{
		myShelfService.setMyShelfDAO(myShelfDAO);
		List<Integer> list=new ArrayList<Integer>();
		when(myShelfDAO.getShelfItems()).thenReturn(list);
		assertEquals(list,myShelfService.getShelfItems());
		
	}
	
	@Test(expected=TestException.class)
	public void tstGetShelfItemsCatchBlock()throws TestException
	{
	    try{
	        myShelfService.setMyShelfDAO(myShelfDAO);
	        doThrow(DAOException.class).when(myShelfDAO).getShelfItems();
	        assertEquals(new ServiceException(),myShelfService.getShelfItems());
	    }
	    catch(Exception e)
	    {
	        throw new TestException("Exception occured during testing the catch block of getShelfItems",e);
	    }
	}
	
	@Test
	public void testAddToShelf() throws DAOException, ServiceException
	{
		myShelfService.setMyShelfDAO(myShelfDAO);
		UserShelf myShelf=new UserShelf();
		
		Users user=new Users();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmail("a@a.com");
		user.setEnabled(true);
		user.setRequestBookCount(0);
		user.setRole(new Role());
		user.setStatus(true);
		user.setSubscription(new Subscription());
		user.setUseraddress(new Address());
		user.setUserDetails(new UserDetails());
		user.setUserId(1);
		
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
		
		myShelf.setUser(user);
		myShelf.setBook(book);
		when(myShelfDAO.addToShelf(myShelf)).thenReturn(myShelf);
		assertEquals(null,myShelfService.addToShelf(book, user));
		
	}
	@Test(expected=TestException.class)
	public void testAddToShelfCatchBlock()throws TestException
	{
	    try{
	        Book book=null;Users user=null;
	        UserShelf myShelf=null;
	        doThrow(DAOException.class).when(myShelfDAO).addToShelf(myShelf);
	        assertEquals(new ServiceException(),myShelfService.addToShelf(book, user));
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	@Test
	public void testDeleteFromShelf() throws DAOException, ServiceException
	{
		myShelfService.setMyShelfDAO(myShelfDAO);
		Integer bookId=1,userId=1;
		doNothing().when(myShelfDAO).deleteFromShelf(bookId, userId);
		myShelfService.deleteFromShelf(bookId, userId);
	}
	
	@Test(expected=TestException.class)
	public void testDeleteFromShelfCatchBlock()throws TestException
	{
	    try{
	        myShelfService.setMyShelfDAO(myShelfDAO);
	        Integer bookId=1,userId=1;
	        doThrow(DAOException.class).when(myShelfDAO).deleteFromShelf(bookId, userId);
	        myShelfService.deleteFromShelf(bookId, userId);
	    }
	    catch(Exception e)
	    {
	        throw new TestException("excpetion occured in catch block of delete from shelf testing",e);
	    }
	    
	}
}
