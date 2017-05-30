package com.impetus.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.BookDAO;
import com.impetus.dao.api.RequestDAO;
import com.impetus.dao.api.UserDAO;
import com.impetus.domain.Book;
import com.impetus.domain.Request;
import com.impetus.domain.Users;
import com.impetus.services.api.RequestService;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestServiceImpl.
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    /** The request dao. */
    @Autowired
    private RequestDAO requestDAO;

    /** The user dao. */
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookDAO bookDAO;

    public void setRequestDAO(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#addRequest(com.impetus.domain
     * .Request)
     */
    public Request addRequest(Request userRequest)  {
        try{
        Request request = requestDAO.addRequest(userRequest);
        Users user = request.getUser();
        user.setRequestBookCount(user.getRequestBookCount() + 1);
        Book book = request.getBook();
        book.setAvailability(book.getAvailability() - 1);
        user = userDAO.updateUser(user);
        book = bookDAO.updateBook(book);
        return userRequest;
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in addRequest",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#getRequests(java.lang.String)
     */
    public List<Request> getRequests(String string)  {
        try{
            return requestDAO.getRequests(string);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getRequests method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#getRequests(java.lang.Integer)
     */
    public List<Request> getRequests(Integer userId)  {
        try{
            return requestDAO.getRequest(userId);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getRequests(userId) method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#getRequests(com.impetus.domain
     * .Users, java.lang.Integer)
     */
    public List<Request> getRequests(Users user, Integer status)
             {
        try{
            return requestDAO.getRequests(user, status);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getRequests(user,status) method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#cancelRequest(java.lang.Integer,
     * java.lang.String)
     */
    public Request cancelRequest(Integer requestId, String role)
             {
        try{
            return requestDAO.cancelRequest(requestId, role);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in cancelRequests method",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RequestService#cancelReturnRequest(java.lang
     * .Integer, java.lang.String)
     */
    public Request cancelReturnRequest(Integer requestId, String roleName)
             {
        try{
            return requestDAO.cancelReturnRequest(requestId, roleName);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in cancelReturbRequests method",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.RequestService#generateReturnRequest(int)
     */
    public Request generateReturnRequest(int requestId)  {
        try{
            return requestDAO.generateReturnRequest(requestId);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in generateREturnRequests method",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.RequestService#getRequests()
     */
    public List<Integer> getRequests()  {
        try{
            return requestDAO.getRequests();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in List<Integer> sgetRequests method",e);
        }
    }

}
