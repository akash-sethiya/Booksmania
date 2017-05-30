package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.UserAddressDAO;
import com.impetus.domain.Address;
import com.impetus.services.impl.UserAddressServiceImpl;

public class UserAddressServiceImplTest {

	
	@Autowired private UserAddressDAO userAddressDAO;
	private UserAddressServiceImpl userAddressService=new UserAddressServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		userAddressDAO=mock(UserAddressDAO.class);
	}

	@Test
	public void testAddAddress() throws DAOException, ServiceException {
		userAddressService.setAddressDAO(userAddressDAO);
		Address userAddress=new Address();
		when(userAddressDAO.addAddress(userAddress)).thenReturn(userAddress);
		assertEquals(userAddress,userAddressService.addAddress(userAddress));
	}
	
	@Test(expected=TestException.class)
	public void testAddAddressCatchBlock() throws TestException
	{
	    try{
	        userAddressService.setAddressDAO(userAddressDAO);
	        Address userAddress=null;
	        doThrow(DAOException.class).when(userAddressDAO).addAddress(userAddress);
	        assertEquals(new ServiceException(),userAddressService.addAddress(userAddress));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetAddress()throws DAOException, ServiceException
	{
		userAddressService.setAddressDAO(userAddressDAO);
		Address userAddress=new Address();
		when(userAddressDAO.getAddress()).thenReturn(userAddress);
		assertEquals(userAddress,userAddressService.getAddress());
	}
	
	@Test(expected=TestException.class)
	public void testGetAddressCatchBlock()throws TestException
	{
	    try{
	        userAddressService.setAddressDAO(userAddressDAO);
	        doThrow(DAOException.class).when(userAddressDAO).getAddress();
	        assertEquals(new ServiceException(),userAddressService.getAddress());
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	
	@Test
	public void testUpdateAddress()throws DAOException, ServiceException
	{
		userAddressService.setAddressDAO(userAddressDAO);
		Address userAddress=new Address();
		when(userAddressDAO.updateAddress(userAddress)).thenReturn(userAddress);
		assertEquals(userAddress,userAddressService.updateAddress(userAddress));
	}
	
	@Test(expected=TestException.class)
	public void testUpdateAddressCatchBlock()throws TestException
	{
	    try{
	        userAddressService.setAddressDAO(userAddressDAO);
	        Address userAddress=null;
	        doThrow(DAOException.class).when(userAddressDAO).updateAddress(userAddress);
	        assertEquals(new ServiceException(),userAddressService.updateAddress(userAddress));
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
	}
	
	@Test
	public void testDeleteAddress()throws DAOException, ServiceException
	{
		userAddressService.setAddressDAO(userAddressDAO);
		Address userAddress=new Address();
		doNothing().when(userAddressDAO).deleteAddress(userAddress);
		userAddressService.deleteAddress(userAddress);
	}
	
	@Test(expected=TestException.class)
	public void testDeleteAddressCatchBlock()throws TestException
	{
	    try{
	        userAddressService.setAddressDAO(userAddressDAO);
	        Address userAddress=null;
	        doThrow(DAOException.class).when(userAddressDAO).deleteAddress(userAddress);
	        userAddressService.deleteAddress(userAddress);
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	

}
