package com.impetus.services.api;

import org.springframework.transaction.annotation.Transactional;


import com.impetus.domain.Role;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleService.
 */
@Transactional(readOnly = false)
public interface RoleService {

    /**
     * Adds the role.
     * 
     * @param role
     *            the role
     * @throws ServiceException 
     */
    void addRole(Role role);

    /**
     * Gets the role list.
     * 
     * @return the role list
     * @throws ServiceException 
     */
    Role getRoleList();

}
