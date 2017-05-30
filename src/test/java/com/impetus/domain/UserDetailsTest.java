package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UserDetailsTest {
    
    @InjectMocks
    private UserDetails userDetails;

    private Integer userDetailsId;

    /** The subscription end date. */
    private Date dob, subscriptionStartDate, subscriptionEndDate;

    private Long usercontact;
    /** The name. */
    private String name;

    /** The gender. */
    private String gender;

    /** The language. */
    private Language language;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        userDetails.setDob(dob);
        userDetails.setGender(gender);
        userDetails.setLanguage(language);
        userDetails.setName(name);
        userDetails.setSubscriptionEndDate(subscriptionEndDate);
        userDetails.setSubscriptionStartDate(subscriptionStartDate);
        userDetails.setUserContactInfo(usercontact);
        userDetails.setUserDetailsId(userDetailsId);
        
        assertEquals(dob,userDetails.getDob());
        assertEquals(gender,userDetails.getGender());
        assertEquals(language,userDetails.getLanguage());
        assertEquals(name,userDetails.getName());
        assertEquals(subscriptionEndDate,userDetails.getSubscriptionEndDate());
        assertEquals(subscriptionStartDate,userDetails.getSubscriptionStartDate());
        assertEquals(usercontact,userDetails.getUserContactInfo());
        assertEquals(userDetailsId,userDetails.getUserDetailsId());
        
        
            
    }

}
