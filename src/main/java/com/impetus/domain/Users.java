package com.impetus.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

// TODO: Auto-generated Javadoc
/**
 * The Class Users.
 */
@Entity
public class Users implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user id. */
    @Id
    @GeneratedValue
    private Integer userId;

    /** The email. */
    @Column(unique = true)
    @NotEmpty(message="email field is empty") @Email(message="Not a valid Email") private String email;

    /** The is account non expired. */
    private boolean isAccountNonExpired;

    /** The is credentials non expired. */
    private boolean isCredentialsNonExpired;

    /** The is account non locked. */
    private boolean isAccountNonLocked;

    /** The is enabled. */
    private boolean isEnabled;

    /** The user password. */
    @NotEmpty(message="password field is empty") private String userPassword;

    /** The status. */
    private boolean status;

    /** The request book count. */
    private Integer requestBookCount;

    /** The role. */
    @ManyToOne
    private Role role;

    /** The subscription. */
    @ManyToOne
    private Subscription subscription;

    /** The user details. */
    @OneToOne
    private UserDetails userDetails;

    /** The useraddress. */
    @OneToOne
    private Address useraddress;

    /** The request. */
    @OneToMany(mappedBy = "user")
    private Set<Request> request = new HashSet<Request>();

    /**
     * Gets the request.
     * 
     * @return the request
     */
    public Set<Request> getRequest() {
        return request;
    }

    /**
     * Sets the request.
     * 
     * @param request
     *            the new request
     */
    public void setRequest(Set<Request> request) {
        this.request = request;
    }

    /**
     * Gets the useraddress.
     * 
     * @return the useraddress
     */
    public Address getUseraddress() {
        return useraddress;
    }

    /**
     * Sets the useraddress.
     * 
     * @param useraddress
     *            the new useraddress
     */
    public void setUseraddress(Address useraddress) {
        this.useraddress = useraddress;
    }

    /**
     * Gets the request book count.
     * 
     * @return the request book count
     */
    public Integer getRequestBookCount() {
        return requestBookCount;
    }

    /**
     * Sets the request book count.
     * 
     * @param requestBookCount
     *            the new request book count
     */
    public void setRequestBookCount(Integer requestBookCount) {
        this.requestBookCount = requestBookCount;
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            the new status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Sets the role.
     * 
     * @param role
     *            the new role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the subscription.
     * 
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription.
     * 
     * @param subscription
     *            the new subscription
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
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
     * Gets the user password.
     * 
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user password.
     * 
     * @param userPassword
     *            the new user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Checks if is account non expired.
     * 
     * @return true, if is account non expired
     */
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * Sets the account non expired.
     * 
     * @param isAccountNonExpired
     *            the new account non expired
     */
    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    /**
     * Checks if is credentials non expired.
     * 
     * @return true, if is credentials non expired
     */
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * Sets the credentials non expired.
     * 
     * @param isCredentialsNonExpired
     *            the new credentials non expired
     */
    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    /**
     * Checks if is account non locked.
     * 
     * @return true, if is account non locked
     */
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * Sets the account non locked.
     * 
     * @param isAccountNonLocked
     *            the new account non locked
     */
    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    /**
     * Checks if is enabled.
     * 
     * @return true, if is enabled
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Sets the enabled.
     * 
     * @param isEnabled
     *            the new enabled
     */
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Gets the role.
     * 
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the email.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email
     *            the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user details.
     * 
     * @return the user details
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Sets the user details.
     * 
     * @param userDetails
     *            the new user details
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}