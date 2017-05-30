package com.impetus.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.UserDetailsDAO;
import com.impetus.domain.UserDetails;
import com.impetus.services.api.UserDetailsService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetailsServiceImpl.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The userdetails dao. */
    @Autowired
    private UserDetailsDAO userdetailsDAO;

    public void setUserdetailsDAO(UserDetailsDAO userdetailsDAO) {
        this.userdetailsDAO = userdetailsDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserDetailsService#saveUserDetails(com.impetus
     * .domain.UserDetails)
     */
    public UserDetails saveUserDetails(UserDetails userDetails)  {
        try{
            return userdetailsDAO.saveUserDetails(userDetails);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in saceUserDetails:::",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserDetailsService#getUserDetails()
     */
    public UserDetails getUserDetails()  {
        try{
            return userdetailsDAO.getUserDetails();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getUserDetails:::",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserDetailsService#deleteUserDetails(com.impetus
     * .domain.UserDetails)
     */
    public void deleteUserDetails(UserDetails userDetails)  {
        try{
        userdetailsDAO.deleteUserDetails(userDetails);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in deleteUserDetails:::",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserDetailsService#updateUserDetails(com.impetus
     * .domain.UserDetails)
     */
    public UserDetails updateUserDetails(UserDetails userDetails)  {
        try{
            return userdetailsDAO.updateUserDetails(userDetails);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in updateUserDetails:::",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserDetailsService#getUserDetailsList()
     */
    public List<UserDetails> getUserDetailsList()  {
        try{
            return userdetailsDAO.getUserDetailsList();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getUserDetailsList:::",e);
        }
    }

}
