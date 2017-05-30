package com.impetus.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.SubscriptionDAO;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionDAOImpl.
 */
@Repository("subscriptionDAO")
@SuppressWarnings("unchecked")
public class SubscriptionDAOImpl implements SubscriptionDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    private Logger logger = LoggerFactory.getLogger(SubscriptionDAOImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#addSubscriptionPlans(com.impetus.
     * domain.Subscription)
     */
    public Subscription addSubscriptionPlans(Subscription subscription) throws DAOException {
        try{
            logger.info("in addSubscrptionPlans(subscription) method");
            hibernateTemplate.save(subscription);
            return subscription;
        }
        catch(Exception e)
        {
            logger.error("exception occured in addSubscriptionPlans::::"+e);
            throw new DAOException("exception occured in addSubscriptionPlans",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.SubscriptionDAO#getSubscriptionPlans()
     */
    public List<Subscription> getSubscriptionPlans() throws DAOException {
        try{
                logger.info("in getSubscrpitonPlans() method");
                return (List<Subscription>) hibernateTemplate.find("from Subscription where subscriptionStatus=?", "Active");
        }
        catch(Exception e)
        {
            logger.error("exception occured in getSubscriptionPlans():::::"+e);
            throw new DAOException("exception occured getSubscriptionPlans()", e);
        }
    }

    /**
     * Gets the subscription.
     * 
     * @param subscriptionName
     *            the subscription name
     * @return the subscription
     * @throws DAOException 
     */
    public Subscription getSubscription(String subscriptionName) throws DAOException {
        try{
        logger.info("in getSubscription(subscriptionNAme() method");
            DetachedCriteria dCriteria = DetachedCriteria
                .forClass(Subscription.class);
        dCriteria.add(Restrictions.eq("subscriptionName", subscriptionName)
                .ignoreCase());
        List<Subscription> tempSubscription = (List<Subscription>) hibernateTemplate
                .findByCriteria(dCriteria);
        if (tempSubscription.size() != 0) {
            return tempSubscription.get(0);
        }
        return null;
        }
        catch(Exception e)
        {
            logger.error("exception occured in getSubsctiption(subsctptionName) method");
            throw new DAOException("exception occured getSubsctiption(subsctptionName) method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#deleteSubscriptionPlan(java.lang.
     * Integer)
     */
    public void deleteSubscriptionPlan(Integer subscriptionId) throws DAOException {
        try{
            logger.info("in deleteSubscrpitonPlan() method");
        Subscription subscirption = (Subscription) hibernateTemplate.get(
                Subscription.class, subscriptionId);
        hibernateTemplate.delete(subscirption);
        }
        catch(Exception e)
        {
            logger.error("exception occured in deleteSubscrptionPlans(subscrptionId");
            throw new DAOException("exception occured in deleteSubscrptionPlans(subscrptionId)",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#updateSubscription(com.impetus.domain
     * .Subscription)
     */
    public boolean updateSubscription(Subscription subscription) throws DAOException {
        try{
            logger.info("in updateSubscription() method");
        Subscription existSubscription = getSubscription(subscription.getSubscriptionName());
        if (existSubscription == null) {
            logger.info("subscription is not existed");
            hibernateTemplate.save(subscription);
            return true;
        } else {
            logger.info("subscription is existed");
            if (existSubscription.getMaxBook() <= subscription.getMaxBook()
                    && existSubscription.getSubscriptionAmount() <= subscription
                            .getSubscriptionAmount()) {
                existSubscription.setMaxBook(subscription.getMaxBook());
                existSubscription.setPeriodOfSubscription(subscription
                        .getPeriodOfSubscription());
                existSubscription.setSubscriptionAmount(subscription
                        .getSubscriptionAmount());
                existSubscription.setSubscriptionDetails(subscription
                        .getSubscriptionDetails());
                existSubscription.setSubscriptionName(subscription
                        .getSubscriptionName());
                existSubscription.setSubscriptionStatus(subscription
                        .getSubscriptionStatus());

                hibernateTemplate.update(existSubscription);
                return true;
            }
            return false;
        }
        }
        catch(Exception e)
        {
            logger.error("exception occured in updateSubscription()::::"+e);
            throw new DAOException("exception occured in updateSubscription()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#getSubscription(java.lang.Integer)
     */
    public Subscription getSubscription(Integer subscriptionId) throws DAOException {
        try{
            logger.info("in getSubscription Method");
        return (Subscription) hibernateTemplate.get(Subscription.class,
                subscriptionId);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getSubscription method::::"+e);
            throw new DAOException("exception occured in getSubscription method ",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#getSubscriptionExpiryDate(java.util
     * .Date, java.lang.Integer)
     */
    public Date getSubscriptionExpiryDate(Date date, Integer subscriptionId) throws DAOException {
        try{
            logger.info("calculating subcription expiry date");
        List<Subscription> subscriptionPeriodList = (List<Subscription>) hibernateTemplate
                .find("from Subscription where subscriptionId=?",
                        subscriptionId);

        int daysExtend = subscriptionPeriodList.get(0)
                .getPeriodOfSubscription();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, daysExtend);
        return calendar.getTime();
        }
        catch(Exception e){
            logger.error("exception hitted  in getSubscriptionExpiryDate() function::::"+e);
            throw new DAOException("exception occured in susbcription expiry date",e); 
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#saveUserSubscriptionHistory(com.impetus
     * .domain.SubscriptionHistory)
     */
    public SubscriptionHistory saveUserSubscriptionHistory(
            SubscriptionHistory subscriptionHistory) throws DAOException {
        try{
            hibernateTemplate.save(subscriptionHistory);
            return subscriptionHistory;
        }
        catch(Exception e)
        {
            logger.error("exception hit in saveUSerSubscriptionHistory method::::"+e);
            throw new DAOException("Error occured in saveUSerSubscriptionHistory method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.SubscriptionDAO#getUserSubscriptionHistory(com.impetus
     * .domain.Users)
     */
    public List<SubscriptionHistory> getUserSubscriptionHistory(Users user) throws DAOException {
        try{
        DetachedCriteria dCriteria = DetachedCriteria
                .forClass(SubscriptionHistory.class);
        dCriteria.add(Restrictions.eq("userId", user.getUserId()));
        return (List<SubscriptionHistory>) hibernateTemplate
                .findByCriteria(dCriteria);
        }
        catch(Exception e){
            logger.error("exception occured in getUSerSubscrpitionHistory(User)::::"+e);
            throw new DAOException("exception hitt",e);
        }
    }
}
