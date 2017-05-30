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
import com.impetus.commons.exception.TestException;
import com.impetus.dao.impl.LanguageDAOImpl;
import com.impetus.domain.Language;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LanguageDAOImplTest {

    @InjectMocks
    private LanguageDAOImpl languageDAOImpl;
    
    @Mock
    private HibernateTemplate hibernateTemplate;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);        
    }

    
    @Test
    public void testGetLanguages() throws DAOException {
        List expectedLangaugeList=new ArrayList<Language>();
        when(hibernateTemplate.find("from Language")).thenReturn(expectedLangaugeList);
        assertEquals(expectedLangaugeList,languageDAOImpl.getLanguages());
    }
    
    @SuppressWarnings("serial")
    @Test(expected=DAOException.class)
    public void testGetLanguagesCatchBlock()throws DAOException, TestException
    {
        try{
            when(hibernateTemplate.find("from Language")).thenThrow(new DataAccessException("this was the reason") {});
            languageDAOImpl.getLanguages();
        }
        catch(DAOException e)
        {
            throw new DAOException();
        }
    }
    
    @Test(expected=DAOException.class)
    public void testGetLanguagesCatchBlock1()throws TestException, DAOException
    {
        try{
            when(hibernateTemplate.find("from Language")).thenThrow(NullPointerException.class);
            languageDAOImpl.getLanguages();
        }
        catch(Exception e)
        {
            throw new DAOException();
        }
        
    }
 }
