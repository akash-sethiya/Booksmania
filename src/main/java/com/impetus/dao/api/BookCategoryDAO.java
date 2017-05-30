package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.BookCategory;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookCategoryDAO.
 */
public interface BookCategoryDAO {

    /**
     * Gets the book category.
     * 
     * @return the book category
     * @throws DAOException 
     */
    List<BookCategory> getBookCategory()throws DAOException;

    /**
     * Gets the book category.
     * 
     * @param category
     *            the category
     * @return the book category
     * @throws DAOException 
     */
    BookCategory getBookCategory(String category)throws DAOException;
}
