package com.impetus.services.api;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;



import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface HistoryService.
 */
@Transactional(readOnly = false)
public interface HistoryService {

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
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<History> getHistory(Date from, Date to, String author, String category);

    /**
     * Re commend book.
     * 
     * @param user
     *            the user
     * @return the list
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Book> reCommendBook(Users user);

    /**
     * Gets the history.
     * 
     * @param user
     *            the user
     * @return the history
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<History> getHistory(Users user);
}
