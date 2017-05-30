package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Book;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface MyShelfDAO.
 */
public interface MyShelfDAO {

    /**
     * Adds the to shelf.
     * 
     * @param myShelf
     *            the my shelf
     * @return
     * @ 
     */
    UserShelf addToShelf(UserShelf myShelf)throws DAOException ;

    /**
     * Gets the shelf items.
     * 
     * @param user
     *            the user
     * @return the shelf items
     * @ 
     */
    List<Integer> getShelfItems(Users user)throws DAOException ;

    /**
     * Delete from shelf.
     * 
     * @param bookId
     *            the book id
     * @param userId
     *            the user id
     * @ 
     */
    void deleteFromShelf(Integer bookId, Integer userId)throws DAOException ;

    /**
     * Gets the shelf.
     * 
     * @param user
     *            the user
     * @return the shelf
     * @ 
     */
    List<Book> getShelf(Users user)throws DAOException ;

    /**
     * Gets the shelf items.
     * 
     * @return the shelf items
     * @ 
     */
    List<Integer> getShelfItems() throws DAOException;

}
