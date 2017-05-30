package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.HistoryDAOImpl;
import com.impetus.domain.Address;
import com.impetus.domain.History;
import com.impetus.domain.Role;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
@SuppressWarnings("rawtypes")
public class HistoryDAOImplTest {

	@InjectMocks
	private HistoryDAOImpl historyDAO;
	
	@Mock
	private HibernateTemplate hibernateTemplate;
	
	@Mock
	private DetachedCriteria dCriteria;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
    @Test
	public void testGetHistoryByUsers() throws DAOException {
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
		
		dCriteria=DetachedCriteria.forClass(History.class);
		dCriteria.add(Restrictions.eq("userId", user.getUserId()));
		List list=new ArrayList();
		when(hibernateTemplate.findByCriteria(dCriteria)).thenReturn(list);
		assertEquals(list,historyDAO.getHistory(user));
		
	}
	
	

}
