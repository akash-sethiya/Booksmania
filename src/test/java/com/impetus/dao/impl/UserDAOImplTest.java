package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.UserDAOImpl;
import com.impetus.domain.Users;

public class UserDAOImplTest {

    @InjectMocks
    private UserDAOImpl userDAOImpl;
    
    @Mock
    private HibernateTemplate hibernateTemplate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddUser() throws DAOException {
        Users user=new Users();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        
        hibernateTemplate.save(user);
        assertEquals(user,userDAOImpl.addUser(user));
    }
    @Test
    public void testUpdateUser() throws DAOException {
        Users user=new Users();
        hibernateTemplate.update(user);
        assertEquals(user,userDAOImpl.updateUser(user));
    }
    
    @Test
    public void testGetUser() throws DAOException
    {
       Object obj=new Users();
       int userId=1;
       when(hibernateTemplate.get(Users.class,userId)).thenReturn((Users) obj);
       assertEquals((Users)obj,userDAOImpl.getUser(userId));
    }

}
