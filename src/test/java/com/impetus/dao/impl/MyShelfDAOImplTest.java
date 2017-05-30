package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.MyShelfDAOImpl;
import com.impetus.domain.Book;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;

public class MyShelfDAOImplTest {
    
    @InjectMocks
    private MyShelfDAOImpl myShelfDAOImpl;
    
    @Mock
    private HibernateTemplate hibernateTemplate;
  
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToShelf() throws DAOException {
       UserShelf myShelf=new UserShelf();
       myShelf.setBook(new Book());
       myShelf.setUser(new Users());
       myShelf.setShelfId(1);
       hibernateTemplate.save(myShelf);
       assertEquals(myShelf.getShelfId(),myShelfDAOImpl.addToShelf(myShelf).getShelfId());
    }
    
  

}
