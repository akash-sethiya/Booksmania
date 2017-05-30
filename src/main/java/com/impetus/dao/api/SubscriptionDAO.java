package com.impetus.dao.api;

import java.util.Date;
import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubscriptionDAO.
 */
public interface SubscriptionDAO {

    /**
     * Adds the subscription plans.
     * 
     * @param subscription
     *            the subscription
     * @return
     * @throws DAOException 
     */
    Subscription addSubscriptionPlans(Subscription subscription) throws DAOException;

    /**
     * Delete subscription plan.
     * 
     * @param subscriptionId
     *            the subscription id
     * @throws DAOException 
     */
    void deleteSubscriptionPlan(Integer subscriptionId) throws DAOException;

    /**
     * Update subscription.
     * 
     * @param subscription
     *            the subscription
     * @return true, if successful
     * @throws DAOException 
     */
    boolean updateSubscription(Subscription subscription) throws DAOException;

    /**
     * Gets the subscription plans.
     * 
     * @return the subscription plans
     * @throws DAOException 
     */
    List<Subscription> getSubscriptionPlans() throws DAOException;

    /**
     * Gets the subscription expiry date.
     * 
     * @param date
     *            the date
     * @param subscriptionId
     *            the subscription id
     * @return the subscription expiry date
     * @throws DAOException 
     */
    Date getSubscriptionExpiryDate(Date date, Integer subscriptionId) throws DAOException;

    /**
     * Gets the subscription.
     * 
     * @param subscriptionId
     *            the subscription id
     * @return the subscription
     * @throws DAOException 
     */
    Subscription getSubscription(Integer subscriptionId) throws DAOException;

    /**
     * Save user subscription history.
     * 
     * @param subscriptionHistory
     *            the subscription history
     * @return
     * @throws DAOException 
     */
    SubscriptionHistory saveUserSubscriptionHistory(
            SubscriptionHistory subscriptionHistory) throws DAOException;

    /**
     * Gets the user subscription history.
     * 
     * @param user
     *            the user
     * @return the user subscription history
     * @throws DAOException 
     */
    List<SubscriptionHistory> getUserSubscriptionHistory(Users user) throws DAOException;

}
