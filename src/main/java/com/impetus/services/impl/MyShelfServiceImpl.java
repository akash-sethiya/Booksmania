package com.impetus.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.MyShelfDAO;
import com.impetus.domain.Book;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;
import com.impetus.services.api.MyShelfService;

// TODO: Auto-generated Javadoc
/**
 * The Class MyShelfServiceImpl.
 */
@Service("myShelfService")
public class MyShelfServiceImpl implements MyShelfService {

    /** The my shelf dao. */
    @Autowired
    private MyShelfDAO myShelfDAO;

    public void setMyShelfDAO(MyShelfDAO myShelfDAO) {
        this.myShelfDAO = myShelfDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.MyShelfService#addToShelf(com.impetus.domain
     * .Book, com.impetus.domain.Users)
     */
    public UserShelf addToShelf(Book book, Users user)  {
        try{
            UserShelf myShelf = new UserShelf();
            myShelf.setBook(book);
            myShelf.setUser(user);
            return myShelfDAO.addToShelf(myShelf);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in addToShelf Service",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.MyShelfService#getShelfItems(com.impetus.domain
     * .Users)
     */
    public List<Integer> getShelfItems(Users user)  {
        try{
            return myShelfDAO.getShelfItems(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getShelfItems Service",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.MyShelfService#deleteFromShelf(java.lang.Integer
     * , java.lang.Integer)
     */
    public void deleteFromShelf(Integer bookId, Integer userId)  {
        try{
            myShelfDAO.deleteFromShelf(bookId, userId);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in deleteFromShelf service",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.MyShelfService#getShelf(com.impetus.domain.Users
     * )
     */
    public List<Book> getShelf(Users user)  {
        try{
            return myShelfDAO.getShelf(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in geShelf(user)",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.MyShelfService#getShelfItems()
     */
    public List<Integer> getShelfItems()  {
        try{
            return myShelfDAO.getShelfItems();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getShelfItems Service",e);
        }

        
    }
}
