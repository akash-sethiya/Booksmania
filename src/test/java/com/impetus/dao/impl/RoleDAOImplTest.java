package com.impetus.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.RoleDAOImpl;
import com.impetus.domain.Role;

public class RoleDAOImplTest {
    @InjectMocks
    private RoleDAOImpl roleDAOImpl;

    @Mock
    private HibernateTemplate hibernateTemplate;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRole() throws DAOException {
        Role role=new Role();
        hibernateTemplate.save(role);
        roleDAOImpl.addRole(role);
    
    }
 }
