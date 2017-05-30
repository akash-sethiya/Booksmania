package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.UserDetailsDAOImpl;
import com.impetus.domain.UserDetails;

public class UserDetailsDAOImplTest {

    @InjectMocks
    private UserDetailsDAOImpl userDetailsDAOImpl;
    @Mock 
    private HibernateTemplate hibernateTemplate;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUserDetails() throws DAOException {
        UserDetails userDetails=new UserDetails();
        hibernateTemplate.save(userDetails);
        assertEquals(userDetails,userDetailsDAOImpl.saveUserDetails(userDetails));
    }

    @Test
    public void testUpdateUserDetails() throws DAOException {
        UserDetails userDetails=new UserDetails();
        hibernateTemplate.update(userDetails);
        assertEquals(userDetails,userDetailsDAOImpl.updateUserDetails(userDetails));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testGetUserDetailsList() throws DAOException {
        List userDetailsList=new ArrayList<UserDetails>();
        when(hibernateTemplate.find("FROM UserDetails")).thenReturn(userDetailsList);
        assertEquals(userDetailsList,userDetailsDAOImpl.getUserDetailsList());
    }

}
