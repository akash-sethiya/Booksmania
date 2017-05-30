package com.impetus.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.HistoryDAO;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.impetus.services.api.MyShelfService;

// TODO: Auto-generated Javadoc
/**
 * The Class HistoryDAOImpl.
 */
@Repository("historyDAO")
public class HistoryDAOImpl implements HistoryDAO {

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    private MyShelfService myShelfService;
    
    private Logger logger = LoggerFactory.getLogger(HistoryDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.HistoryDAO#getHistory(java.util.Date,
     * java.util.Date, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<History> getHistory(Date from, Date to, String author,
            String category) throws DAOException {
        try{
            logger.info("in getHistory Method()");
        String authorQuery, categoryQuery;
        java.sql.Date fromDate = new java.sql.Date(from.getTime());
        java.sql.Date toDate = new java.sql.Date(to.getTime());

        if (!author.equalsIgnoreCase("all")) {
            logger.info("author is specific");
            authorQuery = "SELECT bookId FROM Book WHERE author='" + author
                    + "'";
        } else {
            logger.info("author is all");
            authorQuery = "SELECT bookId from Book";
        }
        if (!category.equalsIgnoreCase("all")) {
            logger.info("category is specific");
            categoryQuery = "SELECT bookId FROM Book WHERE category_categoryId="
                    + Integer.parseInt(category);
        } else {
            logger.info("category is all");
            categoryQuery = "SELECT bookId FROM Book";
        }
        String historyQuery = "from History WHERE issuedDate BETWEEN '"
                + fromDate + "' AND '" + toDate + "' ";
        return (List<History>) hibernateTemplate.find(historyQuery
                + "AND bookId IN (" + authorQuery + ") AND bookId IN ( "
                + categoryQuery + " )");
        }
        catch(Exception e)
        {
            logger.error("exception occured in getHistory() method::::"+e);
            throw new DAOException("exception occured in getHistory()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.HistoryDAO#reCommendBook(com.impetus.domain.Users)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Book> reCommendBook(Users user) throws DAOException {
        
        try{
        logger.info("in reCommend (Users user method");
        String sql; 
        if(myShelfService.getShelf(user).size()>0)
        {
            logger.info("user shelf is not empty");
            sql= "SELECT COUNT(*),h.bookId,h.userId FROM History h,UserShelf u WHERE h.userId=u.user AND h.bookId = u.book AND h.userId=? GROUP BY h.userId,h.bookId";
        }
        else
        {
            logger.info("user shelf is empty");
            sql="SELECT COUNT(*),h.bookId,h.userId FROM History h WHERE h.userId=? GROUP BY h.userId,h.bookId";
        }
        List<Object[]> recommendedBookList = (List<Object[]>) hibernateTemplate
                .find(sql, user.getUserId());
        List<Book> bookList = null;
        if (recommendedBookList.size() != 0) {
            logger.info("list of recommendation book is not empty");
            Set<Book> bookSet = new HashSet<Book>();

            for (Object[] objects : recommendedBookList) {
                Integer bookId = (Integer) objects[1];
                Book book = (Book) hibernateTemplate.get(Book.class, bookId);
                if(book!=null){
                    DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
                dCriteria.add(Restrictions.eq("category", book.getCategory()));
                dCriteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
                dCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
                List<Book> tempBookList = (List<Book>) hibernateTemplate.findByCriteria(dCriteria, 0, 2);
                for (Book b : tempBookList) {
                    bookSet.add(b);
                }
                }
            }

            bookList = new ArrayList(bookSet);
        }
        return bookList;
        }
        catch(Exception e)
        {
            logger.error("error occured in recommendation::::"+e);
            throw new DAOException("error occured in recommendation", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.HistoryDAO#getHistory(com.impetus.domain.Users)
     */
    @SuppressWarnings("unchecked")
    public List<History> getHistory(Users user) throws DAOException {
        try{
        logger.info("in getHistory(Users user) method");

        DetachedCriteria dCriteria = DetachedCriteria.forClass(History.class);
        dCriteria.add(Restrictions.eq("userId", user.getUserId()));
        return (List<History>) hibernateTemplate.findByCriteria(dCriteria);
        }
        catch(Exception e){
            logger.error("error occured in fetching user hsitroy::::"+e);
            throw new DAOException("error occured in getHistory(Users user)",e);
        }
    }
}
