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
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.BookCategoryDAOImpl;
import com.impetus.domain.BookCategory;
@SuppressWarnings({"rawtypes","unchecked","serial"})
public class BookCategoryDAOImplTest {
	
	@InjectMocks
	private BookCategoryDAOImpl bookCategoryDAO;
	
	@Mock
	private HibernateTemplate hibernateTemplate;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
    @Test
	public void testGetBookCategoryList() throws DAOException {
        List list=new ArrayList();
		when(hibernateTemplate.find("from BookCategory")).thenReturn(list);
		assertEquals(list,bookCategoryDAO.getBookCategory());
	}
    
    @Test(expected=DAOException.class)
    public void testGetBookCategoryListCatchBlock() throws DAOException {
        try{
            when(hibernateTemplate.find("from BookCategory")).thenThrow(NullPointerException.class);
            bookCategoryDAO.getBookCategory();
        }
        catch(DAOException e)
        {
            throw new DAOException();
        }
    }
    
    @Test(expected=DAOException.class)
    public void testGetBookCategoryListCatchBlockDataAccess() throws DAOException {
        try{
            when(hibernateTemplate.find("from BookCategory")).thenThrow(new DataAccessException("") {});
            bookCategoryDAO.getBookCategory();
        }
        catch(DAOException e)
        {
            throw new DAOException();
        }
    }
    
    @Test
	public void testGetBookCategoryByCategory() throws DAOException{
		List list=new ArrayList();
		BookCategory bookCategory=new BookCategory();
		String category="Autobiography";
		bookCategory.setCategory(category);
		bookCategory.setCategoryId(1);
		list.add(bookCategory);
		when(hibernateTemplate.find("from BookCategory where category=?", category)).thenReturn(list);
		assertEquals(list.get(0),bookCategoryDAO.getBookCategory(category));
	}
    
    @Test(expected=DAOException.class)
    public void testGetBookCategoryByCategoryCatchBlock() throws DAOException{
        try{
            String category="Autobiography";
            when(hibernateTemplate.find("from BookCategory where category=?", category)).thenThrow(NullPointerException.class);
            bookCategoryDAO.getBookCategory(category);
        }
        catch(DAOException e)
        {
            throw new DAOException();
        }
    }
    
    @Test(expected=DAOException.class)
    public void testGetBookCategoryByCategoryCatchBlockDataAccess() throws DAOException{
        try{
            String category="Autobiography";
            when(hibernateTemplate.find("from BookCategory where category=?", category)).thenThrow(new DataAccessException(""){});
            bookCategoryDAO.getBookCategory(category);
        }
        catch(DAOException e)
        {
            throw new DAOException();
        }
    }
	 
}
