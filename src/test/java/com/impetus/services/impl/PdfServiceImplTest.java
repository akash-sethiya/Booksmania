package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.TestException;
import com.impetus.commons.pdf.Pdf;
import com.impetus.dao.api.PdfDAO;
import com.impetus.dao.impl.PdfDAOImpl;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.lowagie.text.DocumentException;


public class PdfServiceImplTest {
	
	@Autowired private PdfDAO pdfDAO;
	private PdfServiceImpl pdfService=new PdfServiceImpl();
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	private Pdf pdf;
	@Before
	public void setUp() throws Exception {
		pdfDAO=mock(PdfDAOImpl.class);
		pdf=mock(Pdf.class);
	}

	@Test
	public void testGeneratePdfFileInDateRange() throws ParseException, FileNotFoundException, HibernateException, DocumentException, DAOException, ServiceException {
		pdfService.setPdfDAO(pdfDAO);
		pdfService.setPdf(pdf);
		Map<String, List<? extends Object>> map=new HashMap<String, List<? extends Object>>();
		List<History> historyList=new ArrayList<History>();
		List<Users> userList=new ArrayList<Users>();
		List<Book> bookList=new ArrayList<Book>();
		map.put("user",userList);
		map.put("book",bookList);
		map.put("history",historyList);
		
		Date from, to;
		String author,category;
		OutputStream outputStream=new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				 
				
			}
		};
		from=formatter.parse("2014-04-01");
		to=formatter.parse("2014-04-01");
		author="a";
		category="a";
		boolean result=true;
		
		when(pdfDAO.generatePdfFileInDateRange(from, to, author, category)).thenReturn(map);
		when(pdf.generate(map, outputStream)).thenReturn(result);
		assertEquals(result,pdfService.generatePdfFileInDateRange(from, to, author, category, outputStream));
	}
	
	@Test(expected=TestException.class)
	public void testGeneratePdfFileInDateRangeCatchBlock()throws TestException
	{
	    try{
	        pdfService.setPdfDAO(pdfDAO);
	        pdfService.setPdf(pdf);
	        Map<String, List<? extends Object>> map=new HashMap<String, List<? extends Object>>();
	        map.put("user",null);
	        map.put("book",null);
	        map.put("history",null);
	        
	        Date from, to;
	        String author,category;
	        OutputStream outputStream=new OutputStream() {
	            
	            @Override
	            public void write(int b) throws IOException {
	                 
	                
	            }
	        };
	        from=formatter.parse("2014-04-01");
	        to=formatter.parse("2014-04-01");
	        author="a";
	        category="a";
	        boolean result=true;
	        doThrow(DAOException.class).when(pdfDAO).generatePdfFileInDateRange(from, to, author, category);
	        doThrow(ServiceException.class).when(pdf).generate(map, outputStream);
	        assertEquals(result,pdfService.generatePdfFileInDateRange(from, to, author, category, outputStream));
	    }
	    catch(Exception e)
	    {
	        throw new TestException("exception occured in Testing PDF Generation code.",e);
	    }
	}

}
