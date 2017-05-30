package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.UserAddressDAOImpl;
import com.impetus.domain.Address;

public class UserAddressDAOImplTest {
    @InjectMocks
    private UserAddressDAOImpl userDAOImpl;
    @Mock
    private HibernateTemplate hibernateTemplate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddAddress() throws DAOException {
       Address userAddress=new Address();
       hibernateTemplate.save(userAddress);
       assertEquals(userAddress,userDAOImpl.addAddress(userAddress));
    }

    @Test
    public void testUpdateAddress() throws DAOException {
        Address userAddress=new Address();
        hibernateTemplate.update(userAddress);
        assertEquals(userAddress,userDAOImpl.updateAddress(userAddress));
    }

}
