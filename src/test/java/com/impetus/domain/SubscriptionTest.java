package com.impetus.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SubscriptionTest {

    @InjectMocks
    Subscription subscription;
    
    private Integer subscriptionId;

    /** The subscription name. */
    private String subscriptionName;

    /** The subscription status. */
    private String subscriptionStatus;

    /** The max book. */
    private Integer maxBook;

    /** The subscription amount. */
    private Integer subscriptionAmount;

    /** The subscription details. */
    private String subscriptionDetails;

    /** The period of subscription. */
    private Integer periodOfSubscription;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetSubscriptionId() {
        subscription.setMaxBook(maxBook);
        subscription.setPeriodOfSubscription(periodOfSubscription);
        subscription.setSubscriptionAmount(subscriptionAmount);
        subscription.setSubscriptionDetails(subscriptionDetails);
        subscription.setSubscriptionId(subscriptionId);
        subscription.setSubscriptionName(subscriptionName);
        subscription.setSubscriptionStatus(subscriptionStatus);
        
        assertEquals(maxBook,subscription.getMaxBook());
        assertEquals(periodOfSubscription,subscription.getPeriodOfSubscription());
        assertEquals(subscriptionAmount,subscription.getSubscriptionAmount());
        assertEquals(subscriptionDetails,subscription.getSubscriptionDetails());
        assertEquals(subscriptionId,subscription.getSubscriptionId());
        assertEquals(subscriptionName,subscription.getSubscriptionName());
        assertEquals(subscriptionStatus,subscription.getSubscriptionStatus());
    }

}
