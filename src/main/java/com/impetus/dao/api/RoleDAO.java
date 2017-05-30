package com.impetus.dao.api;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Role;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleDAO.
 */
public interface RoleDAO {

    /**
     * Adds the role.
     * 
     * @param role
     *            the role
     * @throws DAOException 
     */
    void addRole(Role role) throws DAOException;

    /**
     * Gets the role list.
     * 
     * @return the role list
     * @throws DAOException 
     */
    Role getRoleList() throws DAOException;

}
