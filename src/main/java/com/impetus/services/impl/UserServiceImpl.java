package com.impetus.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.UserDAO;
import com.impetus.domain.Role;
import com.impetus.domain.Users;
import com.impetus.services.api.RoleService;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user dao. */
    @Autowired
    private UserDAO userDAO;

    /** The role service. */
    @Autowired
    private RoleService roleService;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserService#getUser(java.lang.String)
     */
    public Users getUser(String email)  {
        try{
            return userDAO.getUser(email);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getUser",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserService#deleteUser(com.impetus.domain.Users)
     */
    public void deleteUser(Users user)  {
        try{
            userDAO.deleteUser(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in deleteUser",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserService#updateUser(com.impetus.domain.Users)
     */
    public Users updateUser(Users user) {
        try{
            return userDAO.updateUser(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in updateUser",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserService#registerUser(com.impetus.domain.
     * Users)
     */
    public Users registerUser(Users user) {
        try{
        Role role = roleService.getRoleList();
        user.setRole(role);
        user.setRequestBookCount(0);
        user.setStatus(true);
        return userDAO.addUser(user);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in registerUser",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserService#getUser(java.lang.Integer)
     */
    public Users getUser(Integer userId) {
        try{
            return userDAO.getUser(userId);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getUser(userId)",e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserService#getListOfSubscribedUser()
     */
    public List<Integer> getListOfSubscribedUser() {
        try{
        return userDAO.getListOfSubscribedUser();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getList of Subscribed User(userId)",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserService#getActiveUsers()
     */
    public List<Users> getActiveUsers() {
        try{
            return userDAO.getActiveUsers();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception occured in getActiveUser",e);
        }
    }

}
