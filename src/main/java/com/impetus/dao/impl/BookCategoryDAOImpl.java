package com.impetus.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.BookCategoryDAO;
import com.impetus.domain.BookCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class BookCategoryDAOImpl.
 */
@Repository("bookCategoryDAO")
public class BookCategoryDAOImpl implements BookCategoryDAO {

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    private Logger logger = LoggerFactory.getLogger(BookCategoryDAOImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookCategoryDAO#getBookCategory()
     */
    @SuppressWarnings("unchecked")
    public List<BookCategory> getBookCategory() throws DAOException {
        try{
            logger.info("getBookCategory method");
            return (List<BookCategory>) hibernateTemplate.find("from BookCategory");
        }
        catch(DataAccessException e)
        {
            logger.error("DATA-ACCESS-EXCEPTION exceotion occured in getBookCategory()");
            throw new DAOException("IN BOokcategoryDAOImpl: DATA-ACCESS-EXCEPTION in fetching bookcategory",e);
        }
        catch(Exception e)
        {
            logger.error("exceotion occured in getBookCategory()");
            throw new DAOException("IN BOokcategoryDAOImpl: error in fetching bookcategory",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.BookCategoryDAO#getBookCategory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public BookCategory getBookCategory(String category) throws DAOException {
        try{
            logger.info("getBookCategory(String) method");
            List<BookCategory> bookCategory = (List<BookCategory>) hibernateTemplate
                .find("from BookCategory where category=?", category);
            if (bookCategory.size() != 0) {
                logger.info("list of bookCategory has some value");
                return bookCategory.get(0);
            } else {
                logger.info("list of bookcategory is null");
                return null;
            }
        }
        catch(DataAccessException e)
        {
            logger.error("DATA-ACCESS-EXCEPTION exceotion occured in getBookCategory(category)");
            throw new DAOException("IN BOokcategoryDAOImpl: DATA-ACCESS-EXCEPTION in fetching bookcategory by category String",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getBookCategory(category)");
            throw new DAOException("IN BOokcategoryDAOImpl: error in fetching bookcategory by category String", e);
        }
    }
}
