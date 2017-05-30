package com.impetus.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.impl.SubscriptionDAOImpl;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;

public class SubscriptionDAOImplTest {
    @InjectMocks
    private SubscriptionDAOImpl subscriptionDAOImpl;
    
    @Mock
    private HibernateTemplate hibernateTemplate;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSubscriptionPlans() throws DAOException {
        Subscription subscription=new Subscription();
        subscription.setMaxBook(5);
        subscription.setPeriodOfSubscription(1);
        subscription.setSubscriptionAmount(10);
        subscription.setSubscriptionDetails("asdsa");
        subscription.setSubscriptionId(1);
        subscription.setSubscriptionName("asdsa");
        subscription.setSubscriptionStatus("active");
        
        hibernateTemplate.save(subscription);
        subscriptionDAOImpl.addSubscriptionPlans(subscription);
        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetSubscriptionPlans() throws DAOException {
        
        List subscriptionList=new ArrayList<Subscription>();
        
        Subscription subscription=new Subscription();
        subscription.setMaxBook(5);
        subscription.setPeriodOfSubscription(1);
        subscription.setSubscriptionAmount(10);
        subscription.setSubscriptionDetails("asdsa");
        subscription.setSubscriptionId(1);
        subscription.setSubscriptionName("asdsa");
        subscription.setSubscriptionStatus("active");
        
        subscriptionList.add(subscription);
        when(hibernateTemplate.find("from Subscription where subscriptionStatus=?", "Active")).thenReturn(subscriptionList);
        assertEquals(subscriptionList,subscriptionDAOImpl.getSubscriptionPlans());
        
    }

    @Test
    public void testDeleteSubscriptionPlan() throws DAOException {
        Subscription subscription=new Subscription();
        subscription.setMaxBook(5);
        subscription.setPeriodOfSubscription(1);
        subscription.setSubscriptionAmount(10);
        subscription.setSubscriptionDetails("asdsa");
        subscription.setSubscriptionId(1);
        subscription.setSubscriptionName("asdsa");
        subscription.setSubscriptionStatus("active");
        
        hibernateTemplate.delete(subscription);
        subscriptionDAOImpl.deleteSubscriptionPlan(subscription.getSubscriptionId());
        
        
    }

    @Test
    public void testGetSubscriptionInteger() throws DAOException {
        
        Object obj=new Subscription();
        when(hibernateTemplate.get(Subscription.class,1)).thenReturn((Subscription) obj);
        assertEquals(obj,subscriptionDAOImpl.getSubscription(1));
    }

    @Test
    public void testSaveUserSubscriptionHistory() throws DAOException {
        SubscriptionHistory subscriptionHistory=new SubscriptionHistory();
        hibernateTemplate.save(subscriptionHistory);
        assertEquals(subscriptionHistory,subscriptionDAOImpl.saveUserSubscriptionHistory(subscriptionHistory));
    }

}
