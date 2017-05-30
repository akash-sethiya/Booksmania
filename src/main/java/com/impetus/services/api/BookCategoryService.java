package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.impetus.domain.BookCategory;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookCategoryService.
 */
@Transactional(readOnly = false)
public interface BookCategoryService {

    /**
     * Gets the book category.
     * 
     * @return the book category
     * @throws DAOException 
     * @throws ServiceException 
     */
    List<BookCategory> getBookCategory();

    /**
     * Gets the book category.
     * 
     * @param category
     *            the category
     * @return the book category
     * @throws DAOException 
     * @throws ServiceException 
     */
    BookCategory getBookCategory(String category);

}
