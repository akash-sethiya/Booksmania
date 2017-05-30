package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDAO.
 */
public interface UserDAO {

    /**
     * Adds the user.
     * 
     * @param user
     *            the user
     * @return
     * @throws DAOException 
     */
    Users addUser(Users user) throws DAOException;

    /**
     * Gets the user.
     * 
     * @param email
     *            the email
     * @return the user
     * @throws DAOException 
     */
    Users getUser(String email) throws DAOException;

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
     * @throws DAOException 
     */
    Users updateUser(Users user) throws DAOException;

    /**
     * Gets the user.
     * 
     * @param userId
     *            the user id
     * @return the user
     * @throws DAOException 
     */
    Users getUser(Integer userId) throws DAOException;

    /**
     * Gets the list of subscribed user.
     * 
     * @return the list of subscribed user
     * @throws DAOException 
     */
    List<Integer> getListOfSubscribedUser() throws DAOException;

    /**
     * Gets the active users.
     * 
     * @return the active users
     * @throws DAOException 
     */
    List<Users> getActiveUsers() throws DAOException;

}
