package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
@Entity
public class Address implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The addres id. */
    @Id
    @GeneratedValue
    private Integer addresId;

    /** The primary address. */
    private String primaryAddress;

    /** The secondary address. */
    private String secondaryAddress;

    /** The zip. */
    private Integer zIP;

    /** The city. */
    private String city;

    /** The state. */
    private String state;

    /** The Country. */
    private String country;

    /**
     * Gets the addres id.
     * 
     * @return the addres id
     */
    public Integer getAddresId() {
        return addresId;
    }

    /**
     * Sets the addres id.
     * 
     * @param addresId
     *            the new addres id
     */
    public void setAddresId(Integer addresId) {
        this.addresId = addresId;
    }

    /**
     * Sets the zip.
     * 
     * @param zIP
     *            the new zip
     */
    public void setZIP(Integer zIP) {
        this.zIP = zIP;
    }

    /**
     * Gets the primary address.
     * 
     * @return the primary address
     */
    public String getPrimaryAddress() {
        return primaryAddress;
    }

    /**
     * Sets the primary address.
     * 
     * @param primaryAddress
     *            the new primary address
     */
    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    /**
     * Gets the secondary address.
     * 
     * @return the secondary address
     */
    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    /**
     * Sets the secondary address.
     * 
     * @param secondaryAddress
     *            the new secondary address
     */
    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    /**
     * Gets the zip.
     * 
     * @return the zip
     */
        /**
     * Sets the zip.
     * 
     * @param zIP
     *            the new zip
     */
    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     * 
     * @param city
     *            the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state.
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     * 
     * @param state
     *            the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the country.
     * 
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     * 
     * @param country
     *            the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZIP() {
        return zIP;
    }
    
    
}
