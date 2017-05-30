package com.impetus.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionHistory.
 */
@Entity
public class SubscriptionHistory {

    /** The subscription history id. */
    @Id
    @GeneratedValue
    private Integer subscriptionHistoryId;

    /** The user id. */
    private Integer userId;

    /** The subscription id. */
    private Integer subscriptionId;

    /** The requested book count. */
    private Integer requestedBookCount;

    /** The subscription end date. */
    @Temporal(TemporalType.DATE)
    private Date subscriptionStartDate, subscriptionEndDate;

    /**
     * Gets the requested book count.
     * 
     * @return the requested book count
     */
    public Integer getRequestedBookCount() {
        return requestedBookCount;
    }

    /**
     * Sets the requested book count.
     * 
     * @param requestedBookCount
     *            the new requested book count
     */
    public void setRequestedBookCount(Integer requestedBookCount) {
        this.requestedBookCount = requestedBookCount;
    }

    /**
     * Gets the subscription start date.
     * 
     * @return the subscription start date
     */
    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    /**
     * Sets the subscription start date.
     * 
     * @param subscriptionStartDate
     *            the new subscription start date
     */
    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    /**
     * Gets the subscription end date.
     * 
     * @return the subscription end date
     */
    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    /**
     * Sets the subscription end date.
     * 
     * @param subscriptionEndDate
     *            the new subscription end date
     */
    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    /**
     * Gets the subscription history id.
     * 
     * @return the subscription history id
     */
    public Integer getSubscriptionHistoryId() {
        return subscriptionHistoryId;
    }

    /**
     * Sets the subscription history id.
     * 
     * @param subscriptionHistoryId
     *            the new subscription history id
     */
    public void setSubscriptionHistoryId(Integer subscriptionHistoryId) {
        this.subscriptionHistoryId = subscriptionHistoryId;
    }

    /**
     * Gets the user id.
     * 
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     * 
     * @param userId
     *            the new user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

}
