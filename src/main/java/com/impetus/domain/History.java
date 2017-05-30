package com.impetus.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class History.
 */
@Entity
public class History implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The request id. */
    @Id
    private Integer requestId;

    /** The return date. */
    @Temporal(TemporalType.DATE)
    private Date issuedDate, expectedReturnedDate, returnDate;

    /** The delievery address. */
    private String delieveryAddress;

    /** The delievery requeststatus. */
    private Integer delieveryRequeststatus;

    /** The return request status. */
    private Integer returnRequestStatus;

    /** The user id. */
    private Integer userId;

    /** The book id. */
    private Integer bookId;

    /**
     * Gets the request id.
     * 
     * @return the request id
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Sets the request id.
     * 
     * @param requestId
     *            the new request id
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the issued date.
     * 
     * @return the issued date
     */
    public Date getIssuedDate() {
        return issuedDate;
    }

    /**
     * Sets the issued date.
     * 
     * @param issuedDate
     *            the new issued date
     */
    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    /**
     * Gets the expected returned date.
     * 
     * @return the expected returned date
     */
    public Date getExpectedReturnedDate() {
        return expectedReturnedDate;
    }

    /**
     * Sets the expected returned date.
     * 
     * @param expectedReturnedDate
     *            the new expected returned date
     */
    public void setExpectedReturnedDate(Date expectedReturnedDate) {
        this.expectedReturnedDate = expectedReturnedDate;
    }

    /**
     * Gets the delievery address.
     * 
     * @return the delievery address
     */
    public String getDelieveryAddress() {
        return delieveryAddress;
    }

    /**
     * Sets the delievery address.
     * 
     * @param delieveryAddress
     *            the new delievery address
     */
    public void setDelieveryAddress(String delieveryAddress) {
        this.delieveryAddress = delieveryAddress;
    }

    /**
     * Gets the delievery requeststatus.
     * 
     * @return the delievery requeststatus
     */
    public Integer getDelieveryRequeststatus() {
        return delieveryRequeststatus;
    }

    /**
     * Sets the delievery requeststatus.
     * 
     * @param delieveryRequeststatus
     *            the new delievery requeststatus
     */
    public void setDelieveryRequeststatus(Integer delieveryRequeststatus) {
        this.delieveryRequeststatus = delieveryRequeststatus;
    }

    /**
     * Gets the return request status.
     * 
     * @return the return request status
     */
    public Integer getReturnRequestStatus() {
        return returnRequestStatus;
    }

    /**
     * Sets the return request status.
     * 
     * @param returnRequestStatus
     *            the new return request status
     */
    public void setReturnRequestStatus(Integer returnRequestStatus) {
        this.returnRequestStatus = returnRequestStatus;
    }

    /**
     * Gets the return date.
     * 
     * @return the return date
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     * 
     * @param returnDate
     *            the new return date
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
     * Gets the book id.
     * 
     * @return the book id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book id.
     * 
     * @param bookId
     *            the new book id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}