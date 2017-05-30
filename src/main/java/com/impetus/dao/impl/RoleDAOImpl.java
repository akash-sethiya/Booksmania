package com.impetus.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.RoleDAO;
import com.impetus.domain.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleDAOImpl.
 */
@Repository("roleDAO")
public class RoleDAOImpl implements RoleDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    private Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RoleDAO#addRole(com.impetus.domain.Role)
     */
    public void addRole(Role role) throws DAOException {
        try{
            logger.info("addRole()");
            hibernateTemplate.save(role);
        }
        catch(Exception e)
        {
            logger.error("exception occured in addRole(role) method");
            throw new DAOException("exception occured in addRole()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RoleDAO#getRoleList()
     */
    @SuppressWarnings("unchecked")
    public Role getRoleList() throws DAOException {
        try{
        List<Role> roleList = (List<Role>)hibernateTemplate.find("from Role where roleId=?", 2);
        return roleList.get(0);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getRoleList() method::::"+e);
            throw new DAOException("exception occured",e);
            
        }
    }
}
