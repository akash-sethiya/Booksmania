package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RequestTest {
    
    @InjectMocks
    private Request request;

    private Integer requestId;

    /** The return date. */
    private Date issuedDate, expectedReturnedDate, returnDate;

    /** The delievery address. */
    private String delieveryAddress;

    /** The delievery requeststatus. */
    private Integer delieveryRequeststatus;

    /** The return request status. */
    private Integer returnRequestStatus;

    /** The user. */
    private Users user;

    /** The book. */
    private Book book;

    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    
    @Test
    public void testSetRequestId() {
        request.setBook(book);
        request.setDelieveryAddress(delieveryAddress);
        request.setDelieveryRequeststatus(delieveryRequeststatus);
        request.setExpectedReturnedDate(expectedReturnedDate);
        request.setIssuedDate(issuedDate);
        request.setRequestId(requestId);
        request.setReturnDate(returnDate);
        request.setReturnRequestStatus(returnRequestStatus);
        request.setUser(user);
        
        assertEquals(book,request.getBook());
        assertEquals(delieveryAddress,request.getDelieveryAddress());
        assertEquals(delieveryRequeststatus,request.getDelieveryRequeststatus());
        assertEquals(expectedReturnedDate,request.getExpectedReturnedDate());
        assertEquals(issuedDate,request.getIssuedDate());
        assertEquals(requestId,request.getRequestId());
        assertEquals(returnDate,request.getReturnDate());
        assertEquals(returnRequestStatus,request.getReturnRequestStatus());
        assertEquals(user,request.getUser());
    }

}
