package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UsersTest {

    @InjectMocks
    private Users user;
    
    private Integer userId;

    /** The email. */
    
    private String email;

    /** The is account non expired. */
    private boolean isAccountNonExpired;

    /** The is credentials non expired. */
    private boolean isCredentialsNonExpired;

    /** The is account non locked. */
    private boolean isAccountNonLocked;

    /** The is enabled. */
    private boolean isEnabled;

    /** The user password. */
    private String userPassword;

    /** The status. */
    private boolean status;

    /** The request book count. */
    private Integer requestBookCount;

    /** The role. */
    
    private Role role;

    /** The subscription. */
    
    private Subscription subscription;

    /** The user details. */
    
    private UserDetails userDetails;

    /** The useraddress. */
    
    private Address useraddress;

    /** The request. */
    
    private Set<Request> request = new HashSet<Request>();
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUsers() {
        user.setAccountNonExpired(isAccountNonExpired);
        user.setAccountNonLocked(isAccountNonLocked);
        user.setCredentialsNonExpired(isCredentialsNonExpired);
        user.setEmail(email);
        user.setEnabled(isEnabled);
        user.setRequest(request);
        user.setRequestBookCount(requestBookCount);
        user.setRole(role);
        user.setStatus(status);
        user.setStatus(status);
        user.setSubscription(subscription);
        user.setUseraddress(useraddress);
        user.setUserDetails(userDetails);
        user.setUserId(userId);
        user.setUserPassword(userPassword);
        
        assertEquals(email,user.getEmail());
        assertEquals(request,user.getRequest());
        assertEquals(requestBookCount,user.getRequestBookCount());
        assertEquals(role,user.getRole());
        assertEquals(status,user.getStatus());
        assertEquals(subscription,user.getSubscription());
        assertEquals(useraddress,user.getUseraddress());
        assertEquals(userDetails,user.getUserDetails());
        assertEquals(userId,user.getUserId());
        assertEquals(userPassword,user.getUserPassword());
        assertEquals(isEnabled,user.isEnabled());
        assertEquals(isAccountNonExpired,user.isAccountNonExpired());
        assertEquals(isAccountNonLocked,user.isAccountNonLocked());
        assertEquals(isCredentialsNonExpired,user.isCredentialsNonExpired());
    }

}
