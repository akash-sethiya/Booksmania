package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.impetus.domain.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDetailsService.
 */
@Transactional(readOnly = false)
public interface UserDetailsService {

    /**
     * Save user details.
     * 
     * @param userDetails
     *            the user details
     * @return
     * @ 
     */
    UserDetails saveUserDetails(UserDetails userDetails) ;

    /**
     * Gets the user details.
     * 
     * @return the user details
     * @ 
     */
    UserDetails getUserDetails() ;

    /**
     * Delete user details.
     * 
     * @param userDetails
     *            the user details
     * @ 
     */
    void deleteUserDetails(UserDetails userDetails) ;

    /**
     * Update user details.
     * 
     * @param userDetails
     *            the user details
     * @return
     * @ 
     */
    UserDetails updateUserDetails(UserDetails userDetails) ;

    /**
     * Gets the user details list.
     * 
     * @return the user details list
     * @ 
     */
    List<UserDetails> getUserDetailsList() ;

}
