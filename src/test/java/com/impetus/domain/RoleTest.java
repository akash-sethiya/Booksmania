package com.impetus.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RoleTest {

    @InjectMocks
    private Role role;
    
    private Integer roleId;
    private String roleName;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRole() {
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        
        assertEquals(roleId,role.getRoleId());
        assertEquals(roleName,role.getRoleName());
    }

}
