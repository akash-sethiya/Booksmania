package com.impetus.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;

import com.impetus.commons.exception.ServiceException;
import com.impetus.domain.Address;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
import com.impetus.services.api.UserService;

public class AuthenticateUserServiceImplTest 
{
	@InjectMocks
	AuthenticateUserServiceImpl authenticateService;
	
	@Mock
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testloadUserByUserName() throws ServiceException {
		
		Users user=new Users();
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
		user.setUserDetails(new UserDetails());
		user.setUserId(1);
		 
		final String email=user.getEmail();
		
		
		org.springframework.security.core.userdetails.UserDetails userdetails=new org.springframework.security.core.userdetails.UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public String getUsername() {
				// TODO Auto-generated method stub
				return email;
			}
			
			public String getPassword() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		when(userService.getUser(user.getEmail())).thenReturn(user);
		assertEquals(userdetails.getUsername(),authenticateService.loadUserByUsername(email).getUsername());
		
		
	}
	
	

}
