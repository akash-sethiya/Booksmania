package com.impetus.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.HistoryDAO;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.impetus.services.api.HistoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class HistoryServiceImpl.
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    /** The history dao. */
    @Autowired
    private HistoryDAO historyDAO;

    public void setHistoryDAO(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.HistoryService#getHistory(java.util.Date,
     * java.util.Date, java.lang.String, java.lang.String)
     */
    public List<History> getHistory(Date from, Date to, String author,
            String category)  {
        try{
            return historyDAO.getHistory(from, to, author, category);
        }
        catch(Exception e)
        {
            throw new ServiceException("error occured in getHistory Method", e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.HistoryService#reCommendBook(com.impetus.domain
     * .Users)
     */
    public List<Book> reCommendBook(Users user)  {
        try{
            return historyDAO.reCommendBook(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("Some Exception is occured in recommending book,We will  get back to you soon.",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.HistoryService#getHistory(com.impetus.domain
     * .Users)
     */
    public List<History> getHistory(Users user)  {
        try{
            return historyDAO.getHistory(user);
        }
        catch(Exception e){
            throw new ServiceException("error occured in getHistory(Users user)",e);
        }
        
           
    }
}
