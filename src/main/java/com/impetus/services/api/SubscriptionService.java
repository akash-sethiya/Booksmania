package com.impetus.services.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubscriptionService.
 */
@Transactional(readOnly = false)
public interface SubscriptionService {

    /**
     * Adds the subscription plans.
     * 
     * @param subscription
     *            the subscription
     * @return
     * @ 
     */
    Subscription addSubscriptionPlans(Subscription subscription);

    /**
     * Gets the subscription plans.
     * 
     * @return the subscription plans
     * @ 
     */
    List<Subscription> getSubscriptionPlans() ;

    /**
     * Gets the subscription expiry date.
     * 
     * @param date
     *            the date
     * @param integer
     *            the integer
     * @return the subscription expiry date
     * @ 
     */
    Date getSubscriptionExpiryDate(Date date, Integer integer) ;

    /**
     * Adds the subscription.
     * 
     * @param path
     *            the path
     * @param subscriptionXMLFile
     *            the subscription xml file
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws SAXException
     *             the SAX exception
     * @ 
     */
    void addSubscription(String path, MultipartFile subscriptionXMLFile)
            throws IOException, ParserConfigurationException, SAXException;

    /**
     * Update subscription.
     * 
     * @param path
     *            the path
     * @param subscriptionXMLFile
     *            the subscription xml file
     * @return the int
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws SAXException
     *             the SAX exception
     * @ 
     */
    int updateSubscription(String path, MultipartFile subscriptionXMLFile)
            throws IOException, ParserConfigurationException, SAXException;

    /**
     * Delete subscription.
     * 
     * @param subscriptionId
     *            the subscription id
     * @ 
     */
    void deleteSubscription(Integer subscriptionId) ;

    /**
     * Gets the subscription plan.
     * 
     * @param subscriptionId
     *            the subscription id
     * @return the subscription plan
     * @ 
     */
    Subscription getSubscriptionPlan(Integer subscriptionId) ;

    /**
     * Save user subscription history.
     * 
     * @param subscriptionHistory
     *            the subscription history
     * @return
     * @ 
     */
    SubscriptionHistory saveUserSubscriptionHistory(
            SubscriptionHistory subscriptionHistory) ;

    /**
     * Gets the user subscription history.
     * 
     * @param user
     *            the user
     * @return the user subscription history
     * @ 
     */
    List<SubscriptionHistory> getUserSubscriptionHistory(Users user) ;

}
