package com.impetus.commons.pdf;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.impetus.domain.Address;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.History;
import com.impetus.domain.Language;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfTest {

    @InjectMocks
    Pdf pdf;
    
    @Mock
    Document document;
    
    @Mock
    PdfPTable pdfTable;
    
   /* @Mock
    List<Book> bookList;
    
    @Mock
    List<Users> userList;
    
    @Mock
    List<History> historyList;*/
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerate() throws DocumentException 
    {
        Date date=new Date();
        OutputStream outputStream=new ServletOutputStream() {
            
            @Override
            public void write(int b) throws IOException {
                // TODO Auto-generated method stub
                
            }
        };
        
        Map<String, List<? extends Object>> map=new HashMap<String, List<? extends Object>>();
        List<Book> bookList = new ArrayList<Book>();
        List<Users> userList = new ArrayList<Users>();
        List<History> historyList = new ArrayList<History>();
        
        map.put("book",bookList);
        map.put("user",userList);
        map.put("history",historyList);
        
       
        Book book=new Book();
        
        Language lang=new Language();
        lang.setLanguage("Hindi");
        lang.setLanguageId(1);
        
        BookCategory category=new BookCategory();
        category.setCategory("Autobiography");
        category.setCategoryId(1);
        
        book.setAuthor("test");
        book.setAvailability(10);
        book.setBookLanguage(lang);
        book.setCategory(category);
        book.setCount(10);
        book.setDescription("test");
        book.setImageName("test.jpg");
        book.setISBN("test");
        book.setPublisher("test");
        book.setQuantity(10);
        
        Users user=new Users();
        
        UserDetails userDetails=new UserDetails();
        userDetails.setDob(date);
        userDetails.setGender("a");
        userDetails.setName("a");
        userDetails.setSubscriptionEndDate(date);
        userDetails.setSubscriptionStartDate(date);
        userDetails.setUserContactInfo(3L);
        userDetails.setUserDetailsId(1);
        
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
        user.setUserDetails(userDetails);
        user.setUserId(1);
        
        
        
        History history=new History();
        history.setExpectedReturnedDate(date);
        history.setIssuedDate(date);
        history.setReturnDate(date);
        
        int i=0;
        
        bookList.add(book);
        userList.add(user);
        historyList.add(history);
        
        
        String str="abc";
        boolean expected=true;
        boolean status=true;
        
        PdfWriter.getInstance(document, outputStream);
        doNothing().when(document).open();
        doNothing().when(pdfTable).addCell(str);
        
        
        book=bookList.get(i);
        user=userList.get(i);
        history=historyList.get(i);
        
        when(document.add(pdfTable)).thenReturn(status);
        doNothing().when(document).close();
        assertEquals(expected,pdf.generate(map, outputStream));
        
    }

}
