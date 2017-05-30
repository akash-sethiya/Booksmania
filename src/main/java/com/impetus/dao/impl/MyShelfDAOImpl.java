package com.impetus.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.MyShelfDAO;
import com.impetus.domain.Book;
import com.impetus.domain.UserShelf;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class MyShelfDAOImpl.
 */
@SuppressWarnings("unchecked")
public class MyShelfDAOImpl implements MyShelfDAO {

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    
    private Logger logger = LoggerFactory.getLogger(MyShelfDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.MyShelfDAO#addToShelf(com.impetus.domain.UserShelf)
     */
    public UserShelf addToShelf(UserShelf myShelf) throws DAOException {
        try{
            logger.info("in addToShelf() method");
            hibernateTemplate.save(myShelf);
            return myShelf;
        }
        catch(Exception e){
            logger.error("error occured during adding book into shelf::::"+e);
            throw new DAOException("error in addToShelf()",e);
            
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.MyShelfDAO#getShelfItems(com.impetus.domain.Users)
     */
    public List<Integer> getShelfItems(Users user)throws DAOException {
        try{
            logger.info("in getShelfItems(Users user");
        List<Integer> bookIdList = new ArrayList<Integer>();
        List<UserShelf> result = (List<UserShelf>) hibernateTemplate.find(
                "from UserShelf where user_userId=?", user.getUserId());
        for (UserShelf userShelf : result) {
            int bookId = userShelf.getBook().getBookId();
            bookIdList.add(bookId);
        }
        return bookIdList;
        }
        catch(Exception e){
            logger.error("error occured in getShelfItems(Users users::::"+e);
            throw new DAOException("error in getShelfItems(Users user",e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.MyShelfDAO#deleteFromShelf(java.lang.Integer,
     * java.lang.Integer)
     */
    public void deleteFromShelf(Integer bookId, Integer userId)throws DAOException {
        try{
            logger.info("in deleteFromShelf()");
        
        Users user = (Users) hibernateTemplate.get(Users.class, userId);
        Book book = (Book) hibernateTemplate.get(Book.class, bookId);

        DetachedCriteria dCriteria = DetachedCriteria.forClass(UserShelf.class);
        dCriteria.add(Restrictions.and(Restrictions.eq("user", user),
                Restrictions.eq("book", book)));
        List<UserShelf> shelf = (List<UserShelf>) hibernateTemplate
                .findByCriteria(dCriteria);
        hibernateTemplate.delete(shelf.get(0));
        }
        catch(Exception e){
            logger.error("error occured in deleteFromShelf(Integer bookId,Integer userId:::"+e);
            throw new DAOException("exception in deleteFromShelf()",e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.MyShelfDAO#getShelf(com.impetus.domain.Users)
     */
    public List<Book> getShelf(Users user)throws DAOException {
        try{
            logger.info("in getShelf(Users user) method");
        List<UserShelf> result = (List<UserShelf>) hibernateTemplate.find(
                "from UserShelf where user_userId=?", user.getUserId());
        List<Book> bookList = new ArrayList<Book>();
        for (UserShelf userShelf : result) {
            bookList.add(userShelf.getBook());
        }
        return bookList;
        }
        catch(Exception e)
        {
            logger.error("exception occured in getShelf(Users user):::;"+e);
            throw new DAOException("exception occured in getShelf()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.MyShelfDAO#getShelfItems()
     */
    public List<Integer> getShelfItems()throws DAOException {
        try{
        logger.info("in getShelfItems() ");
        DetachedCriteria dCriteria = DetachedCriteria.forClass(UserShelf.class);
        dCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        List<UserShelf> bookList = (List<UserShelf>) hibernateTemplate
                .findByCriteria(dCriteria);
        List<Integer> shelfItemList = new ArrayList<Integer>();
        for (UserShelf b : bookList) {
            shelfItemList.add(b.getBook().getBookId());
        }
        return shelfItemList;
        }
        catch(Exception e)
        {
            logger.error("exception occured in getShelfItems :::: "+e);
            throw new DAOException("Exception occured in getShelfITems",e);
        }
    }

}
