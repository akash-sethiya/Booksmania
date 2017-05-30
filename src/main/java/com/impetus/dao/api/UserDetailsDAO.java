package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDetailsDAO.
 */
public interface UserDetailsDAO {

    /**
     * Save user details.
     * 
     * @param userDetails
     *            the user details
     * @return
     * @throws DAOException 
     */
    UserDetails saveUserDetails(UserDetails userDetails) throws DAOException;

    /**
     * Gets the user details.
     * 
     * @return the user details
     * @throws DAOException 
     */
    UserDetails getUserDetails() throws DAOException;

    /**
     * Delete user details.
     * 
     * @param userDetails
     *            the user details
     */
    void deleteUserDetails(UserDetails userDetails);

    /**
     * Update user details.
     * 
     * @param userDetails
     *            the user details
     * @return
     * @throws DAOException 
     */
    UserDetails updateUserDetails(UserDetails userDetails) throws DAOException;

    /**
     * Gets the user details list.
     * 
     * @return the user details list
     * @throws DAOException 
     */
    List<UserDetails> getUserDetailsList() throws DAOException;

}
