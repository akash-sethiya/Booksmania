package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.BookDAO;
import com.impetus.dao.api.RequestDAO;
import com.impetus.dao.api.UserDAO;
import com.impetus.dao.impl.BookDAOImpl;
import com.impetus.dao.impl.RequestDAOImpl;
import com.impetus.dao.impl.UserDAOImpl;
import com.impetus.domain.Address;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;
import com.impetus.domain.Request;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;

public class RequestServiceImplTest {
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BookDAO bookDAO;

	private RequestServiceImpl requestService=new RequestServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		requestDAO=mock(RequestDAOImpl.class);
		userDAO=mock(UserDAOImpl.class);
		bookDAO=mock(BookDAOImpl.class);
	}

	@Test
	public void testAddRequest() throws IOException, ServiceException, DAOException {
		requestService.setBookDAO(bookDAO);
		requestService.setRequestDAO(requestDAO);
		requestService.setUserDAO(userDAO);
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
		
		 
		
		Request userRequest=new Request();
		userRequest.setUser(user);
		userRequest.setBook(book);
		when(requestDAO.addRequest(userRequest)).thenReturn(userRequest);
		
		when(bookDAO.updateBook(book)).thenReturn(book);
		when(userDAO.updateUser(user)).thenReturn(user);
		assertEquals(userRequest,requestService.addRequest(userRequest));
	}
	
	@Test(expected=TestException.class)
	public void testAddRequestCatchBlock() throws TestException
	{
	    try{
	        requestService.setBookDAO(bookDAO);
	        requestService.setRequestDAO(requestDAO);
	        requestService.setUserDAO(userDAO);
	        Users user=null;
	        Book book=null;
	        Request userRequest=new Request();
	        userRequest.setUser(user);
	        userRequest.setBook(book);
	        doThrow(DAOException.class).when(requestDAO).addRequest(userRequest);
	        
	        doThrow(DAOException.class).when(bookDAO).updateBook(book);
	        doThrow(DAOException.class).when(userDAO).updateUser(user);
	        assertEquals(new ServiceException(),requestService.addRequest(userRequest));
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exceprion occured in testAddREquestCatchBlock",e);
	    }
	}
	
	@Test
	public void testGetRequestByStringStatus() throws IOException, ServiceException, DAOException
	{
		requestService.setRequestDAO(requestDAO);
		List<Request> list=new ArrayList<Request>();
		String status="Pending";
		when(requestDAO.getRequests(status)).thenReturn(list);
		assertEquals(list,requestService.getRequests(status));
	}
	
	@Test(expected=TestException.class)
    public void testGetRequestByStringStatusCatchBlock() throws TestException
    {
        try{
            requestService.setRequestDAO(requestDAO);
            String status="Pending";
            doThrow(DAOException.class).when(requestDAO).getRequests(status);
            assertEquals(new ServiceException(),requestService.getRequests(status));
        }
        catch(Exception e)
        {
            throw new TestException("exceprion occured in testAddREquestCatchBlock",e);
        }
    }
	
	
	@Test
	public void testGetRequestById() throws IOException, DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		List<Request> list=new ArrayList<Request>();
		int userId=1;
		when(requestDAO.getRequest(userId)).thenReturn(list);
		assertEquals(list,requestService.getRequests(userId));
	}
	
	@Test(expected=TestException.class)
    public void testGetRequestByIdCatchBlock() throws TestException
    {
        try{
            requestService.setRequestDAO(requestDAO);
            int userId=1;
            doThrow(DAOException.class).when(requestDAO).getRequest(userId);
            assertEquals(new ServiceException(),requestService.getRequests(userId));
        }
        catch(Exception e)
        {
            throw new TestException("exceprion occured in testGetREquestByIdCatchBlock",e);
        }
    }

	@Test
	public void testGetUserByUserAndStatus() throws IOException, DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		List<Request> list=new ArrayList<Request>();
		Users user=new Users();
		int status=0;
		when(requestDAO.getRequests(user, status)).thenReturn(list);
		assertEquals(list,requestService.getRequests(user, status));
	}
	
	@Test(expected=TestException.class)
	public void testGetuserByUserAndStatusCatchBlock()throws TestException
	{
	    try{
	        requestService.setRequestDAO(requestDAO);
	        Users user=null;
	        int status=0;
	        doThrow(DAOException.class).when(requestDAO).getRequests(user, status);
	        assertEquals(new ServiceException(),requestService.getRequests(user, status));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	
	@Test
	public void testCancelRequest() throws IOException, DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		Request request=new Request();
		int requestId=1;
		String role="a";
		when(requestDAO.cancelRequest(requestId, role)).thenReturn(request);
		assertEquals(request,requestService.cancelRequest(requestId, role));
	}
	
	@Test(expected=TestException.class)
	public void testCancelRequestCatchBlock()throws TestException
	{
	    try{
	        requestService.setRequestDAO(requestDAO);
	        int requestId=1;
	        String role="a";
	        doThrow(DAOException.class).when(requestDAO).cancelRequest(requestId, role);
	        assertEquals(new ServiceException(),requestService.cancelRequest(requestId, role));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	
	@Test
	public void testCancelReturnRequest() throws IOException, DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		Request request=new Request();
		int requestId=1;
		String role="a";
		when(requestDAO.cancelReturnRequest(requestId, role)).thenReturn(request);
		assertEquals(request,requestService.cancelReturnRequest(requestId, role));
	}
	
	@Test(expected=TestException.class)
	public void testCancelReturnRequestCatchBlock()throws TestException
	{
	    try{
	        requestService.setRequestDAO(requestDAO);
	        int requestId=1;
	        String role="a";
	        doThrow(DAOException.class).when(requestDAO).cancelReturnRequest(requestId, role);
	        assertEquals(new ServiceException(),requestService.cancelReturnRequest(requestId, role));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGenerateReturnRequest() throws IOException, DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		Request request=new Request();
		int requestId=1;
		when(requestDAO.generateReturnRequest(requestId)).thenReturn(request);
		assertEquals(request,requestService.generateReturnRequest(requestId));
	}
	
	@Test(expected=TestException.class)
	public void testGenerateReturnRequestCatchBlock()throws TestException
	{
	    try{
	        requestService.setRequestDAO(requestDAO);
	        int requestId=1;
	        doThrow(DAOException.class).when(requestDAO).generateReturnRequest(requestId);
	        assertEquals(new ServiceException(),requestService.generateReturnRequest(requestId));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetRequestList() throws DAOException, ServiceException
	{
		requestService.setRequestDAO(requestDAO);
		List<Integer> list=new ArrayList<Integer>();
		when(requestDAO.getRequests()).thenReturn(list);
		assertEquals(list,requestService.getRequests());
	}
	
	@Test(expected=TestException.class)
	public void testGetRequestListCatchBlock()throws TestException
	{
	    try{
	        requestService.setRequestDAO(requestDAO);
	        doThrow(DAOException.class).when(requestDAO).getRequests();
	        assertEquals(new ServiceException(),requestService.getRequests());
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
}
