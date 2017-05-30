package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;



import com.impetus.domain.Book;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface MyShelfService.
 */
@Transactional(readOnly = false)
public interface MyShelfService {

    /**
     * Adds the to shelf.
     * 
     * @param book
     *            the book
     * @param user
     *            the user
     * @return
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    UserShelf addToShelf(Book book, Users user);

    /**
     * Gets the shelf items.
     * 
     * @param user
     *            the user
     * @return the shelf items
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Integer> getShelfItems(Users user);

    /**
     * Delete from shelf.
     * 
     * @param bookId
     *            the book id
     * @param userId
     *            the user id
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    void deleteFromShelf(Integer bookId, Integer userId);

    /**
     * Gets the shelf.
     * 
     * @param user
     *            the user
     * @return the shelf
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Book> getShelf(Users user);

    /**
     * Gets the shelf items.
     * 
     * @return the shelf items
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Integer> getShelfItems();

}
