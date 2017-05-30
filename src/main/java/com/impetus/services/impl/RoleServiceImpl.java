package com.impetus.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.RoleDAO;
import com.impetus.domain.Role;
import com.impetus.services.api.RoleService;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleServiceImpl.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The role dao. */
    @Autowired
    private RoleDAO roleDAO;

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.RoleService#addRole(com.impetus.domain.Role)
     */
    public void addRole(com.impetus.domain.Role role)  {
        try{
            roleDAO.addRole(role);
        }
        catch(Exception e)
        {
            throw new ServiceException("excepton occured in addRole()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.RoleService#getRoleList()
     */
    public Role getRoleList()  {
        try{
            return roleDAO.getRoleList();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getRoleList() method",e);
        }

    }

}
