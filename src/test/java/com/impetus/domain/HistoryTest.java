package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class HistoryTest {

    @InjectMocks
    private History history;
    
    private Integer requestId;

    /** The return date. */
    private Date issuedDate, expectedReturnedDate, returnDate;

    /** The delievery address. */
    private String delieveryAddress;

    /** The delievery requeststatus. */
    private Integer delieveryRequeststatus;

    /** The return request status. */
    private Integer returnRequestStatus;

    /** The user id. */
    private Integer userId;

    /** The book id. */
    private Integer bookId;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHistory() {
        history.setBookId(bookId);
        history.setDelieveryAddress(delieveryAddress);
        history.setDelieveryRequeststatus(delieveryRequeststatus);
        history.setExpectedReturnedDate(expectedReturnedDate);
        history.setIssuedDate(issuedDate);
        history.setRequestId(requestId);
        history.setReturnDate(returnDate);
        history.setReturnRequestStatus(returnRequestStatus);
        history.setUserId(userId);
        
        assertEquals(bookId,history.getBookId());
        assertEquals(delieveryAddress,history.getDelieveryAddress());
        assertEquals(delieveryRequeststatus,history.getDelieveryRequeststatus());
        assertEquals(expectedReturnedDate,history.getExpectedReturnedDate());
        assertEquals(issuedDate,history.getIssuedDate());
        assertEquals(requestId,history.getRequestId());
        assertEquals(returnDate,history.getReturnDate());
        assertEquals(returnRequestStatus,history.getReturnRequestStatus());
        assertEquals(userId,history.getUserId());
    }
    
}
