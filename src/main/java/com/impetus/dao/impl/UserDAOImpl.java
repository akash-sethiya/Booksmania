package com.impetus.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.UserDAO;
import com.impetus.domain.Subscription;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAOImpl.
 */
@Repository("userDAO")
@Transactional(readOnly = false)
public class UserDAOImpl implements UserDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    
    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#addUser(com.impetus.domain.Users)
     */
    public Users addUser(Users user) throws DAOException {
        try{
            logger.info("in addUser(User) method");
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);

            hibernateTemplate.save(user);
            return user;
        }
        catch(Exception e)
        {
            logger.error("exception in addUser() method::::"+e);
            throw new DAOException("exception occured in addUSer",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#getUser(java.lang.Integer)
     */
    public Users getUser(Integer userId) throws DAOException {
        try{
            logger.info("getUser(userId)");
            return (Users) hibernateTemplate.get(Users.class, userId);
        }
        catch(Exception e)
        {
            logger.error("exception hit in getUSer(User)::::"+e);
            throw new DAOException("exception hitted",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#getUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Users getUser(String email) throws DAOException {
        try{
         logger.info("in getUser(email) method");
        List<Users> user = (List<Users>) hibernateTemplate.find(
                "from Users where email=?", email);
        if(user.size()>0){
            logger.info("Size of list is >0");
            return user.get(0);
        }
        else{
            logger.info("size of list <=0");
            return null;
        }
        }
        catch(Exception e)
        {
            logger.error("exception occured in getUser(email)::::"+e);
            throw new DAOException("exception hitted",e);
        }
            
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#deleteUser(com.impetus.domain.Users)
     */
    public void deleteUser(Users user) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#updateUser(com.impetus.domain.Users)
     */
    public Users updateUser(Users user) throws DAOException {
        try{
            logger.info("in updateUser(user)");
            hibernateTemplate.update(user);
            return user;
        }
        catch(Exception e)
        {
            logger.error("exception occured in updateUser(user) method");
            throw new DAOException("exception hit",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#getListOfSubscribedUser()
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getListOfSubscribedUser() throws DAOException {
        try{
            logger.info("in getListOfSubscribed User method");
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Users.class);
        dCriteria.setProjection(Projections.projectionList().add(
                Projections.property("subscription")));
        List<Subscription> sqlList = (List<Subscription>) hibernateTemplate
                .findByCriteria(dCriteria);

        List<Integer> userSubscribedIdList = new ArrayList<Integer>();
        for (Subscription o : sqlList) {
            Integer id = o.getSubscriptionId();

            userSubscribedIdList.add(id);
        }
        return userSubscribedIdList;
        }
        catch(Exception e)
        {
            logger.error("EXCEPTION OCCURED IN GET_LIST_OF_SUBSCRIBED_USER::::"+e);
            throw new DAOException("exception occured in fetching list of subscribed user", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDAO#getActiveUsers()
     */
    @SuppressWarnings("unchecked")
    public List<Users> getActiveUsers() throws DAOException {
        try{
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Users.class);
        dCriteria.add(Restrictions.eq("status", true));
        return (List<Users>) hibernateTemplate.findByCriteria(dCriteria);
        }
        catch(Exception e)
        {
            logger.error("Exception occured in getActiveUsers() method");
            throw new DAOException("exception hit",e);
        }
    }
}