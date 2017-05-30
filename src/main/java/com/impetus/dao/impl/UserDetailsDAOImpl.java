package com.impetus.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.UserDetailsDAO;
import com.impetus.domain.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetailsDAOImpl.
 */
@Repository("userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserDetailsDAO#saveUserDetails(com.impetus.domain
     * .UserDetails)
     */
    public UserDetails saveUserDetails(UserDetails userDetails) throws DAOException {
        try{
            logger.info("in saveUserDetails(userDetails)");
            hibernateTemplate.save(userDetails);
            return userDetails;
        }
        catch(Exception e)
        {
            logger.error("exception occur in saveUserDetails(userDEtails)::::"+e);
            throw new DAOException("exception occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDetailsDAO#getUserDetails()
     */
    @SuppressWarnings("unchecked")
    public UserDetails getUserDetails() throws DAOException {
        try
        {
        logger.info("in getUserDetails method");    
        DetachedCriteria dCriteria = DetachedCriteria
                .forClass(UserDetails.class);
        dCriteria.addOrder(Order.desc("userDetailsId"));
        List<UserDetails> list = (List<UserDetails>) hibernateTemplate
                .findByCriteria(dCriteria);
        return list.get(0);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getUserDetails():::::"+e);
            throw new DAOException("exception Ocuured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserDetailsDAO#deleteUserDetails(com.impetus.domain
     * .UserDetails)
     */
    public void deleteUserDetails(UserDetails userDetails) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserDetailsDAO#updateUserDetails(com.impetus.domain
     * .UserDetails)
     */
    public UserDetails updateUserDetails(UserDetails userDetails) throws DAOException {
        try{
            logger.info("in updateUserDetails(userDetails) method");
            hibernateTemplate.update(userDetails);
            return userDetails;
        }
        catch(Exception e)
        {
            logger.error("exception occured in updateUSerDetails)(:::::"+e);
            throw new DAOException("exception Occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserDetailsDAO#getUserDetailsList()
     */
    @SuppressWarnings("unchecked")
    public List<UserDetails> getUserDetailsList() throws DAOException {
        try{
            logger.info("in getUserDetails Method");
            return (List<UserDetails>) hibernateTemplate.find("FROM UserDetails");
        }
        catch(Exception e)
        {
            logger.error("exception occured in getUSerdetails method::::"+e);
            throw new DAOException("exception occured",e);
        }
    }

}
