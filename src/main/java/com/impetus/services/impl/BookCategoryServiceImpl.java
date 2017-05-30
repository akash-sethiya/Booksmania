package com.impetus.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.DAORuntimeException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.ServiceRuntimeException;
import com.impetus.dao.api.BookCategoryDAO;
import com.impetus.domain.BookCategory;
import com.impetus.services.api.BookCategoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class BookCategoryServiceImpl.
 */
@Service("bookCategoryService")
public class BookCategoryServiceImpl implements BookCategoryService {

    /** The book category dao. */
    @Autowired
    private BookCategoryDAO bookCategoryDAO;

    public void setBookCategoryDAO(BookCategoryDAO bookCategoryDAO) {
        this.bookCategoryDAO = bookCategoryDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookCategoryService#getBookCategory()
     */
    public List<BookCategory> getBookCategory(){
        try{
            return bookCategoryDAO.getBookCategory();
        }
        catch(Exception e){
            throw new ServiceException("Error occured in fetching book categories.",e);
        }
        

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.BookCategoryService#getBookCategory(java.lang
     * .String)
     */
    public BookCategory getBookCategory(String category){
        try{
            return bookCategoryDAO.getBookCategory(category);
        }catch(DAORuntimeException e)
        {
            throw new ServiceRuntimeException("RuntimeException occured in fetching book with category="+category,e);
        }
        catch(DAOException e)
        {
            throw new ServiceException("Exception occured in fetching book with category="+category,e);
        }
    }

}
