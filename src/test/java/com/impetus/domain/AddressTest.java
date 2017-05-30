package com.impetus.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

@SuppressWarnings("unused")
public class AddressTest {

    @InjectMocks
    private Address adress;
    
    private Integer addresId;

    /** The primary address. */
    private String primaryAddress;

    /** The secondary address. */
    private String secondaryAddress;

    /** The zip. */
    private Integer zIP;
    
    
    /** The city. */
    private String city;

    /** The state. */
    private String state;

    /** The Country. */
    private String country;
    
    
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddress()
    {
        Address address=new Address();
        address.setAddresId(addresId);
        address.setCity(city);
        address.setCountry(country);
        address.setPrimaryAddress(primaryAddress);
        address.setSecondaryAddress(secondaryAddress);
        address.setState(state);
        address.setZIP(zIP);
        
        assertEquals(addresId,address.getAddresId());
        assertEquals(city,address.getCity());
        assertEquals(country,address.getCountry());
        assertEquals(primaryAddress,address.getPrimaryAddress());
        assertEquals(secondaryAddress,address.getSecondaryAddress());
        assertEquals(state,address.getState());
        assertEquals(zIP,address.getZIP());
        
    }
     
}
