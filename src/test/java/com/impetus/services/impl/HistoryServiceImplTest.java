package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.HistoryDAO;
import com.impetus.dao.impl.HistoryDAOImpl;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.impetus.services.impl.HistoryServiceImpl;

public class HistoryServiceImplTest {
	
	@Autowired private HistoryDAO historyDAO;
	private HistoryServiceImpl historyService=new HistoryServiceImpl();
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void setUp() throws Exception {
		historyDAO=mock(HistoryDAOImpl.class);
	}

	@Test
	public void testGetHistoryByFilter() throws ParseException, DAOException, ServiceException {
		historyService.setHistoryDAO(historyDAO);
		Date from,to;
		String author,category;
		author="a";
		category="a";
		from=formatter.parse("2014-04-01");
		to=formatter.parse("2014-04-01");
		List<History> historyList=new ArrayList<History>();
		when(historyDAO.getHistory(from, to, author, category)).thenReturn(historyList);
		assertEquals(historyList,historyService.getHistory(from, to, author, category));
	}
	
	@Test(expected=TestException.class)
	public void testGetHistoryByFilterCatchBlock()throws TestException
	{
	    try{
	        historyService.setHistoryDAO(historyDAO);
	        Date from = null,to = null;
	        String author = null,category = null;
            doThrow(DAOException.class).when(historyDAO).getHistory(from, to, author, category);
            assertEquals(new ServiceException(),historyService.getHistory(from, to, author, category));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	
	@Test 
	public void testGetHistoryByUser() throws DAOException, ServiceException
	{
		historyService.setHistoryDAO(historyDAO);
		Users user=new Users();
		List<History> historyList=new ArrayList<History>();
		when(historyDAO.getHistory(user)).thenReturn(historyList);
		assertEquals(historyList,historyService.getHistory(user));
	}
	
	@Test(expected=TestException.class)
    public void testGetHistoryByUserCatchBlock()throws TestException
    {
        try{
            historyService.setHistoryDAO(historyDAO);
            Users user=null;
            doThrow(DAOException.class).when(historyDAO).getHistory(user);
            assertEquals(new ServiceException(),historyService.getHistory(user));
        }
        catch(Exception e)
        {
            throw new TestException();
        }
    }
	
	@Test
	public void testRecommendBooks() throws DAOException, ServiceException
	{
		historyService.setHistoryDAO(historyDAO);
		Users user=new Users();
		List<Book> bookList=new ArrayList<Book>();
		when(historyDAO.reCommendBook(user)).thenReturn(bookList);
		assertEquals(bookList,historyService.reCommendBook(user));
	}
	
	@Test(expected=TestException.class)
    public void testRecommendBooksCatchBlock()throws TestException
    {
        try{
            historyService.setHistoryDAO(historyDAO);
            Users user=null;
            doThrow(DAOException.class).when(historyDAO).reCommendBook(user);
            assertEquals(new ServiceException(),historyService.reCommendBook(user));
        }
        catch(Exception e)
        {
            throw new TestException();
        }
    }

}
