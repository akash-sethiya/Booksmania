package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Subscription.
 */
@Entity
public class Subscription implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The subscription id. */
    @Id
    @GeneratedValue
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

    /**
     * Gets the subscription id.
     * 
     * @return the subscription id
     */
    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the subscription id.
     * 
     * @param subscriptionId
     *            the new subscription id
     */
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * Gets the subscription name.
     * 
     * @return the subscription name
     */
    public String getSubscriptionName() {
        return subscriptionName;
    }

    /**
     * Sets the subscription name.
     * 
     * @param subscriptionName
     *            the new subscription name
     */
    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    /**
     * Gets the subscription status.
     * 
     * @return the subscription status
     */
    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    /**
     * Sets the subscription status.
     * 
     * @param subscriptionStatus
     *            the new subscription status
     */
    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    /**
     * Gets the max book.
     * 
     * @return the max book
     */
    public Integer getMaxBook() {
        return maxBook;
    }

    /**
     * Sets the max book.
     * 
     * @param maxBook
     *            the new max book
     */
    public void setMaxBook(Integer maxBook) {
        this.maxBook = maxBook;
    }

    /**
     * Gets the subscription amount.
     * 
     * @return the subscription amount
     */
    public Integer getSubscriptionAmount() {
        return subscriptionAmount;
    }

    /**
     * Sets the subscription amount.
     * 
     * @param subscriptionAmount
     *            the new subscription amount
     */
    public void setSubscriptionAmount(Integer subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    /**
     * Gets the subscription details.
     * 
     * @return the subscription details
     */
    public String getSubscriptionDetails() {
        return subscriptionDetails;
    }

    /**
     * Sets the subscription details.
     * 
     * @param subscriptionDetails
     *            the new subscription details
     */
    public void setSubscriptionDetails(String subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    /**
     * Gets the period of subscription.
     * 
     * @return the period of subscription
     */
    public Integer getPeriodOfSubscription() {
        return periodOfSubscription;
    }

    /**
     * Sets the period of subscription.
     * 
     * @param periodOfSubscription
     *            the new period of subscription
     */
    public void setPeriodOfSubscription(Integer periodOfSubscription) {
        this.periodOfSubscription = periodOfSubscription;
    }
}
