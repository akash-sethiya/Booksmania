package com.impetus.dao.api;

import java.util.Date;
import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface HistoryDAO.
 */
public interface HistoryDAO {

    /**
     * Gets the history.
     * 
     * @param from
     *            the from
     * @param to
     *            the to
     * @param author
     *            the author
     * @param category
     *            the category
     * @return the history
     * @ 
     */
    List<History> getHistory(Date from, Date to, String author, String category)throws DAOException ;

    /**
     * Re commend book.
     * 
     * @param user
     *            the user
     * @return the list
     * @ 
     */
    List<Book> reCommendBook(Users user)throws DAOException ;

    /**
     * Gets the history.
     * 
     * @param user
     *            the user
     * @return the history
     * @ 
     */
    List<History> getHistory(Users user)throws DAOException ;
}
