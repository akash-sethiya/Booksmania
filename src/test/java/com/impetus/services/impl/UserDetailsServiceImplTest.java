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
import com.impetus.dao.api.UserDetailsDAO;
import com.impetus.dao.impl.UserDetailsDAOImpl;
import com.impetus.domain.UserDetails;
import com.impetus.services.impl.UserDetailsServiceImpl;

public class UserDetailsServiceImplTest {

	@Autowired UserDetailsDAO userDetailsDAO;
	
	UserDetailsServiceImpl userDetailsService=new UserDetailsServiceImpl();
	@Before
	public void setUp() throws Exception {
		userDetailsDAO=mock(UserDetailsDAOImpl.class);
	}

	@Test
	public void testSaveUserDetails()throws DAOException, ServiceException {
		userDetailsService.setUserdetailsDAO(userDetailsDAO);
		UserDetails userDetails=new UserDetails();
		when(userDetailsDAO.saveUserDetails(userDetails)).thenReturn(userDetails);
		assertEquals(userDetails,userDetailsService.saveUserDetails(userDetails));
	}
	
	@Test(expected=TestException.class)
	public void testSaveUserDetailsCatchBlock()throws TestException
	{
	    try{
	        userDetailsService.setUserdetailsDAO(userDetailsDAO);
	        UserDetails userDetails=null;
	        doThrow(DAOException.class).when(userDetailsDAO).saveUserDetails(userDetails);
	        assertEquals(new ServiceException(),userDetailsService.saveUserDetails(userDetails));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testUpdateUserDetails()throws DAOException, ServiceException
	{
		userDetailsService.setUserdetailsDAO(userDetailsDAO);
		UserDetails userDetails=new UserDetails();
		when(userDetailsDAO.updateUserDetails(userDetails)).thenReturn(userDetails);
		assertEquals(userDetails,userDetailsService.updateUserDetails(userDetails));
	}
	
	@Test(expected=TestException.class)
    public void testUpdateUserDetailsCatchBlock()throws TestException
    {
	    try{
	        userDetailsService.setUserdetailsDAO(userDetailsDAO);
	        UserDetails userDetails=new UserDetails();
	        doThrow(DAOException.class).when(userDetailsDAO).updateUserDetails(userDetails);
	        assertEquals(new ServiceException(),userDetailsService.updateUserDetails(userDetails));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
    }
	
	@Test
	public void testGetUserDetailsList()throws DAOException, ServiceException
	{
		userDetailsService.setUserdetailsDAO(userDetailsDAO);
		List<UserDetails> list=new ArrayList<UserDetails>();
		list.add(new UserDetails());
		when(userDetailsDAO.getUserDetailsList()).thenReturn(list);
		assertEquals(list,userDetailsService.getUserDetailsList());
	}
	
	@Test(expected=TestException.class)
    public void testGetUserDetailsListCatchBlock()throws TestException
    {
	    try{
	        userDetailsService.setUserdetailsDAO(userDetailsDAO);
	        doThrow(DAOException.class).when(userDetailsDAO).getUserDetailsList();
	        assertEquals(new ServiceException(),userDetailsService.getUserDetailsList());
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
    }
	
	@Test
	public void testGetUserDetails()throws DAOException, ServiceException
	{
		userDetailsService.setUserdetailsDAO(userDetailsDAO);
		UserDetails userDetails=new UserDetails();
		when(userDetailsDAO.getUserDetails()).thenReturn(userDetails);
		assertEquals(userDetails,userDetailsService.getUserDetails());
	}
	
	@Test(expected=TestException.class)
    public void testGetUserDetailsCatchBlock()throws TestException
    {
	    try{
	        userDetailsService.setUserdetailsDAO(userDetailsDAO);
	        doThrow(DAOException.class).when(userDetailsDAO).getUserDetails();
	        assertEquals(new ServiceException(),userDetailsService.getUserDetails());
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
    }
	
	@Test
	public void testDeleteUserDetails()throws DAOException, ServiceException
	{
		userDetailsService.setUserdetailsDAO(userDetailsDAO);
		UserDetails userDetails=new UserDetails();
		doNothing().when(userDetailsDAO).deleteUserDetails(userDetails);
		userDetailsService.deleteUserDetails(userDetails);
	}
	
	@Test(expected=TestException.class)
    public void testDeleteUserDetailsCatchBlock()throws TestException
    {
	    try{
	        userDetailsService.setUserdetailsDAO(userDetailsDAO);
	        UserDetails userDetails=null;
	        doThrow(DAOException.class).when(userDetailsDAO).deleteUserDetails(userDetails);
	        userDetailsService.deleteUserDetails(userDetails);
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
    }

}
