package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.RoleDAO;
import com.impetus.dao.impl.RoleDAOImpl;
import com.impetus.domain.Role;

public class RoleServiceImplTest {

	@Autowired
	private RoleDAO roleDAO;
	
	RoleServiceImpl roleService=new RoleServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		roleDAO=mock(RoleDAOImpl.class);
	}

	@Test
	public void testGetRoleList() throws ServiceException, DAOException {
		roleService.setRoleDAO(roleDAO);
		Role role=new Role();
		role.setRoleId(1);
		role.setRoleName("Admin");
		when(roleDAO.getRoleList()).thenReturn(role);
		assertEquals(role,roleService.getRoleList());
	}
	
	@Test(expected=TestException.class)
	public void testGetRoleListCatchBlock()throws TestException
	{
	    try{
	        roleService.setRoleDAO(roleDAO);
	        Role role=new Role();
	        role.setRoleId(0);
	        doThrow(DAOException.class).when(roleDAO).getRoleList();
	        assertEquals(new ServiceException(),roleService.getRoleList());
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exception occured in testGetRoleListCatchBlock.",e);
	    }
	}
	
	@Test
	public void testAddRole() throws DAOException, ServiceException
	{
		roleService.setRoleDAO(roleDAO);
		Role role=new Role();
		role.setRoleId(1);
		role.setRoleName("Admin");
		doNothing().when(roleDAO).addRole(role);
		roleService.addRole(role);
	}
	
	@Test(expected=TestException.class)
	public void testAddRoleCatchBlock()throws TestException
	{
	    try{
	        roleService.setRoleDAO(roleDAO);
	        Role role=new Role();
	        role.setRoleId(0);
	        doThrow(DAOException.class).when(roleDAO).addRole(role);
	        roleService.addRole(role);
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exception occured in testAddRoleCatchBlock",e);
	    }
	}

}
