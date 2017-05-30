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
import com.impetus.dao.api.UserDAO;
import com.impetus.dao.impl.UserDAOImpl;
import com.impetus.domain.Address;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
import com.impetus.services.api.RoleService;
import com.impetus.services.impl.RoleServiceImpl;
import com.impetus.services.impl.UserServiceImpl;

public class UserServiceImplTest {

	
	@Autowired UserDAO userDAO;
	@Autowired RoleService roleService;
	UserServiceImpl userService=new UserServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		userDAO=mock(UserDAOImpl.class);
		roleService=mock(RoleServiceImpl.class);
	}

	@Test
	public void testRegisterUser()throws DAOException, ServiceException {
		userService.setUserDAO(userDAO);
		userService.setRoleService(roleService);
		Users user=new Users();
		Role role=new Role();
		when(roleService.getRoleList()).thenReturn(role);
		user.setRole(role);
		when(userDAO.addUser(user)).thenReturn(user);
		assertEquals(user,userService.registerUser(user));
	}
	
	@Test(expected=TestException.class)
	public void testRegisterUserCatchBlock()throws TestException
	{
	    try{
	        userService.setUserDAO(userDAO);
	        userService.setRoleService(roleService);
	        Users user=new Users();
	        Role role=new Role();
	        doThrow(DAOException.class).when(roleService).getRoleList();
	        user.setRole(role);
	        doThrow(DAOException.class).when(userDAO).addUser(user);
	        assertEquals(new ServiceException(),userService.registerUser(user));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
	}
	
	@Test
	public void testGetUserByEmail()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
		String email="ak@ak.com";
		Users user=new Users();
		when(userDAO.getUser(email)).thenReturn(user);
		assertEquals(user,userService.getUser(email));
		
	}
	
	@Test(expected=TestException.class)
    public void testGetUserByEmailCatchBlock()throws TestException
    {
	    try{
	        userService.setUserDAO(userDAO);
	        String email="ak@ak.com";
	        doThrow(DAOException.class).when(userDAO).getUser(email);
	        assertEquals(new ServiceException(),userService.getUser(email));
	    }
	    catch(Exception e){
	        throw new TestException(); 
	    }
        
    }
	
	@Test
	public void testGetUserById()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
		int userId=1;
		Users user=new Users();
		when(userDAO.getUser(userId)).thenReturn(user);
		assertEquals(user,userService.getUser(userId));
	}
	
	@Test(expected=TestException.class)
    public void testGetUserByIdCatchBlock()throws TestException
    {
	    try{
	        userService.setUserDAO(userDAO);
	        int userId=1;
	        doThrow(DAOException.class).when(userDAO).getUser(userId);
	        assertEquals(new ServiceException(),userService.getUser(userId));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
    }
	
	@Test
	public void testGetListOfSubscribedUser()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
		List<Integer> list=new ArrayList<Integer>();
		when(userDAO.getListOfSubscribedUser()).thenReturn(list);
		assertEquals(list,userService.getListOfSubscribedUser());
	}
	
	@Test(expected=TestException.class)
    public void testGetListOfSubscribedUserCatchBlock()throws TestException
    {
	    try{
	        userService.setUserDAO(userDAO);
	        doThrow(DAOException.class).when(userDAO).getListOfSubscribedUser();
	        assertEquals(new ServiceException(),userService.getListOfSubscribedUser());
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
    }
	
	@Test
	public void testUpdateUser()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
		Users user=new Users();
		when(userDAO.updateUser(user)).thenReturn(user);
		assertEquals(user,userService.updateUser(user));
	}
	
	@Test(expected=TestException.class)
    public void testUpdateUserCatchBlock()throws TestException
    {
	    try{
	        userService.setUserDAO(userDAO);
	        Users user=null;
	        doThrow(DAOException.class).when(userDAO).updateUser(user);
	        assertEquals(new ServiceException(),userService.updateUser(user));
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
    }
	
	@Test
	public void testGetActiveUser()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
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
		
		List<Users> userList=new ArrayList<Users>();
		userList.add(user);
		when(userDAO.getActiveUsers()).thenReturn(userList);
		assertEquals(userList,userService.getActiveUsers());
		
		
	}
	
	@Test(expected=TestException.class)
	public void testGetActiveUserCatchBlock()throws TestException
	{
	    try{
	        userService.setUserDAO(userDAO);
	        doThrow(DAOException.class).when(userDAO).getActiveUsers();
	        assertEquals(new ServiceException(),userService.getActiveUsers());
	    }
	    catch(Exception e)
	    {
	        throw new TestException();
	    }
        
	}
	@Test
	public void testDeleteUser()throws DAOException, ServiceException
	{
		userService.setUserDAO(userDAO);
		Users user=new Users();
		doNothing().when(userDAO).deleteUser(user);
		userService.deleteUser(user);
		
	}
	
	@Test(expected=TestException.class)
    public void testDeleteUserCatchBlock()throws TestException
    {
	    try{
	        userService.setUserDAO(userDAO);
	        Users user=new Users();
	        doThrow(DAOException.class).when(userDAO).deleteUser(user);
	        userService.deleteUser(user);
	    }
	    catch(Exception e){
	        throw new TestException();
	    }
        
    }
	

}
