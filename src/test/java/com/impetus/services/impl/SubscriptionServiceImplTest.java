package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.SubscriptionDAO;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceImplTest {
	
	@InjectMocks
	private SubscriptionServiceImpl subscriptionService;
	
	@Mock
	private SubscriptionDAO subscriptionDAO;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddSubscription() throws DAOException, ServiceException {
		Subscription subscription=new Subscription();
		when(subscriptionDAO.addSubscriptionPlans(subscription)).thenReturn(subscription);
		assertEquals(subscription,subscriptionService.addSubscriptionPlans(subscription));
	}
	
	@Test(expected=TestException.class)
	public void testAddSubscriptionCatchBlock()throws TestException
	{
	    try{
	        Subscription subscription=null;
	        doThrow(DAOException.class).when(subscriptionDAO).addSubscriptionPlans(subscription);
	        assertEquals(new ServiceException(),subscriptionService.addSubscriptionPlans(subscription));
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetSubscriptionPlansList()throws DAOException, ServiceException
	{
		List<Subscription> list=new ArrayList<Subscription>();
		when(subscriptionDAO.getSubscriptionPlans()).thenReturn(list);
		assertEquals(list,subscriptionService.getSubscriptionPlans());
	}
	
	@Test(expected=TestException.class)
	public void testGetSubscriptionPlansListCatchBlock()throws TestException
	{
	    try{
	        doThrow(DAOException.class).when(subscriptionDAO).getSubscriptionPlans();
	        assertEquals(new ServiceException(),subscriptionService.getSubscriptionPlans());
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	    
	}
	
	@Test
	public void testGetSubscriptionExpiryDate()throws DAOException, ServiceException
	{
		Date expectedExpiryDate=new Date();
		Date startDate=new Date();
		int subscriptionId=1;
		when(subscriptionDAO.getSubscriptionExpiryDate(startDate, subscriptionId)).thenReturn(expectedExpiryDate);
		assertEquals(expectedExpiryDate,subscriptionService.getSubscriptionExpiryDate(startDate, subscriptionId));
	}
	
	@Test(expected=TestException.class)
	public void testGetSubscriptionExpiryDateCatchBlock()throws TestException
	{
	    try{
	        Date startDate=null;
	        int subscriptionId=1;
	        doThrow(DAOException.class).when(subscriptionDAO).getSubscriptionExpiryDate(startDate, subscriptionId);
	        assertEquals(new ServiceException(),subscriptionService.getSubscriptionExpiryDate(startDate, subscriptionId));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetSubscriptionPlanById()throws DAOException, ServiceException
	{
		int subscriptionId=1;
		Subscription subscription=new Subscription();
		when(subscriptionDAO.getSubscription(subscriptionId)).thenReturn(subscription);
		assertEquals(subscription,subscriptionService.getSubscriptionPlan(subscriptionId));
	}
	
	@Test(expected=TestException.class)
	public void testGetSubscriptionPlanByIdCatchBlock()throws TestException
	{
	    try{
	        int subscriptionId=0;
	        doThrow(DAOException.class).when(subscriptionDAO).getSubscription(subscriptionId);
	        assertEquals(new Subscription(),subscriptionService.getSubscriptionPlan(subscriptionId));
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	
	@Test
	public void testDeleteSubscription()throws DAOException, ServiceException
	{
		int subscriptionId=1;
		doNothing().when(subscriptionDAO).deleteSubscriptionPlan(subscriptionId);
		subscriptionService.deleteSubscription(subscriptionId);
	}
	
	@Test(expected=TestException.class)
	public void testDeleteSubscriptionCatchBlock()throws TestException
	{
	    try{
	        int subscriptionId=0;
	        doThrow(DAOException.class).when(subscriptionDAO).deleteSubscriptionPlan(subscriptionId);
	        subscriptionService.deleteSubscription(subscriptionId);
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	
	@Test
	public void testUpdateSubscriptionPlans()throws DAOException, ServiceException
	{
		Subscription subscription=new Subscription();
		when(subscriptionDAO.updateSubscription(subscription)).thenReturn(true);
		assertEquals(true,subscriptionService.updateSubscriptionPlans(subscription));
	}
	
	@Test(expected=TestException.class)
	public void testUpdateSubscriptionPlansCatchBlock()throws TestException
	{
	    try{
	        Subscription subscription=new Subscription();
	        doThrow(DAOException.class).when(subscriptionDAO).updateSubscription(subscription);
	        assertEquals(new ServiceException(),subscriptionService.updateSubscriptionPlans(subscription));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testSaveUserSubscriptionHistory()throws DAOException, ServiceException
	{
		SubscriptionHistory history=new SubscriptionHistory();
		when(subscriptionDAO.saveUserSubscriptionHistory(history)).thenReturn(history);
		assertEquals(history,subscriptionService.saveUserSubscriptionHistory(history));
	}
	
	@Test(expected=TestException.class)
	public void testSaveUserSubscriptionHistoryCatchBlock()throws TestException
	{
	    try{
	        SubscriptionHistory history=null;
	        doThrow(DAOException.class).when(subscriptionDAO).saveUserSubscriptionHistory(history);
	        assertEquals(new ServiceException(),subscriptionService.saveUserSubscriptionHistory(history));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetUserSubscriptionHistory()throws DAOException, ServiceException
	{
		Users user=new Users();
		List<SubscriptionHistory> list=new ArrayList<SubscriptionHistory>();
		when(subscriptionDAO.getUserSubscriptionHistory(user)).thenReturn(list);
		assertEquals(list,subscriptionService.getUserSubscriptionHistory(user));
	}
	
	@Test(expected=TestException.class)
	public void testGetUserSubscriptionHistoryCatchBlock()throws TestException
	{
	    try{
	        Users user=new Users();
	        doThrow(DAOException.class).when(subscriptionDAO).getUserSubscriptionHistory(user);
	        assertEquals(new ServiceException(),subscriptionService.getUserSubscriptionHistory(user));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	

}
