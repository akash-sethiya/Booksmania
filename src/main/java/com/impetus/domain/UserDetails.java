package com.impetus.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetails.
 */
@Entity
@Table(name = "userdetails")
public class UserDetails implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user details id. */
    @Id
    @GeneratedValue
    private Integer userDetailsId;

    /** The subscription end date. */
    @Temporal(TemporalType.DATE)
    private Date dob, subscriptionStartDate, subscriptionEndDate;

    /** The user contact info. */
    private Long userContactInfo;

    /** The name. */
    private String name;

    /** The gender. */
    private String gender;

    /** The language. */
    @ManyToOne
    private Language language;

    /**
     * Gets the language.
     * 
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     * 
     * @param language
     *            the new language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Sets the user contact info.
     * 
     * @param userContactInfo
     *            the new user contact info
     */
    public void setUserContactInfo(Long userContactInfo) {
        this.userContactInfo = userContactInfo;
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
     * Gets the user details id.
     * 
     * @return the user details id
     */
    public Integer getUserDetailsId() {
        return userDetailsId;
    }

    /**
     * Sets the user details id.
     * 
     * @param userDetailsId
     *            the new user details id
     */
    public void setUserDetailsId(Integer userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    /**
     * Gets the user contact info.
     * 
     * @return the user contact info
     */
    public Long getUserContactInfo() {
        return userContactInfo;
    }

    /**
     * Sets the user contact info.
     * 
     * @param userContactInfo
     *            the new user contact info
     */
     /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the dob.
     * 
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the dob.
     * 
     * @param dob
     *            the new dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the gender.
     * 
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     * 
     * @param gender
     *            the new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
