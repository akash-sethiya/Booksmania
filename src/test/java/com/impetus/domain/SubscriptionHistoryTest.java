package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SubscriptionHistoryTest {
    
    @InjectMocks
    private SubscriptionHistory subscriptionHistory;
    
    private Integer subscriptionHistoryId;

    /** The user id. */
    private Integer userId;

    /** The subscription id. */
    private Integer subscriptionId;

    /** The requested book count. */
    private Integer requestedBookCount;

    /** The subscription end date. */
    private Date subscriptionStartDate, subscriptionEndDate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubscriptionHistory() {
        subscriptionHistory.setRequestedBookCount(requestedBookCount);
        subscriptionHistory.setSubscriptionEndDate(subscriptionEndDate);
        subscriptionHistory.setSubscriptionHistoryId(subscriptionHistoryId);
        subscriptionHistory.setSubscriptionId(subscriptionId);
        subscriptionHistory.setSubscriptionStartDate(subscriptionStartDate);
        subscriptionHistory.setUserId(userId);
        
        assertEquals(requestedBookCount,subscriptionHistory.getRequestedBookCount());
        assertEquals(subscriptionEndDate,subscriptionHistory.getSubscriptionEndDate());
        assertEquals(subscriptionHistoryId,subscriptionHistory.getSubscriptionHistoryId());
        assertEquals(subscriptionId,subscriptionHistory.getSubscriptionId());
        assertEquals(subscriptionStartDate,subscriptionHistory.getSubscriptionStartDate());
        assertEquals(userId,subscriptionHistory.getUserId());
        
    }

}
