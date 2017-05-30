package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
@Transactional(readOnly = false)
public interface UserService {

    /**
     * Register user.
     * 
     * @param user
     *            the user
     * @return
     */
    Users registerUser(Users user);

    /**
     * Gets the user.
     * 
     * @param email
     *            the email
     * @return the user
     */
    Users getUser(String email);

    /**
     * Delete user.
     * 
     * @param user
     *            the user
     */
    void deleteUser(Users user);

    /**
     * Update user.
     * 
     * @param user
     *            the user
     * @return
     */
    Users updateUser(Users user);

    /**
     * Gets the user.
     * 
     * @param userId
     *            the user id
     * @return the user
     */
    Users getUser(Integer userId);

    /**
     * Gets the list of subscribed user.
     * 
     * @return the list of subscribed user
     */
    List<Integer> getListOfSubscribedUser();

    /**
     * Gets the active users.
     * 
     * @return the active users
     */
    List<Users> getActiveUsers();
}
