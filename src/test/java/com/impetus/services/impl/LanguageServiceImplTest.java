package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.dao.api.LanguageDAO;
import com.impetus.dao.impl.LanguageDAOImpl;
import com.impetus.domain.Language;
import com.impetus.services.impl.LanguageServiceImpl;

public class LanguageServiceImplTest {
	
	@Autowired
	private LanguageDAO languageDAO;
	
	LanguageServiceImpl languageService=new LanguageServiceImpl();
	

	@Before
	public void setUp() throws Exception {
		languageDAO=mock(LanguageDAOImpl.class);
	}

	@Test
	public void testGetLanguages() throws DAOException, ServiceException {
		languageService.setLanguageDAO(languageDAO);
		List<Language> list=new ArrayList<Language>();
		Language language=new Language();
		language.setLanguage("Hindi");
		language.setLanguageId(1);
		list.add(language);
		when(languageDAO.getLanguages()).thenReturn(list);
		assertEquals(list,languageService.getLanguages());
		
	}
	@Test(expected=TestException.class)
	public void testGetLangaugesCatchBlock()throws TestException
	{
	    try{
	        languageService.setLanguageDAO(languageDAO);
	        List<Language> list=new ArrayList<Language>();
	        Language language=new Language();
	        language.setLanguage("Hindi");
	        language.setLanguageId(1);
	        list.add(language);
	        doThrow(DAOException.class).when(languageDAO).getLanguages();
	        assertEquals(new ServiceException(),languageService.getLanguages());
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exception occured in testGetLangaugesCatch Block::",e);
	    }
	}

}
