package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
@Entity
public class Role implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The role id. */
    @Id
    @GeneratedValue
    private Integer roleId;

    /** The role name. */
    private String roleName;

    /**
     * Gets the role id.
     * 
     * @return the role id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role id.
     * 
     * @param roleId
     *            the new role id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the role name.
     * 
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     * 
     * @param roleName
     *            the new role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
