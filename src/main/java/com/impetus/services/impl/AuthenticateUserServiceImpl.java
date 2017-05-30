package com.impetus.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.commons.exception.ServiceException;
import com.impetus.controller.AdminController;
import com.impetus.domain.Users;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticateUserServiceImpl.
 */
@Service("userAuthenticateService")
@Transactional(readOnly = false)
public class AuthenticateUserServiceImpl implements UserDetailsService,
        Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The user. */
    private Users user;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @SuppressWarnings("serial")
    public UserDetails loadUserByUsername(final String email) {

        logger.info("IN AUTHENTICATIONUSERSERVICEIMPL : loading user by username starts........");
        try {
            user = userService.getUser(email);
        } catch (Exception e) {
            logger.info("Exception In Authentication Service" + e);
            throw new ServiceException("Exception occured during authenticating User",e);
        }
        if (user == null) {
            throw new UsernameNotFoundException(email + " not found");
        } else {
            return new UserDetails() {
                public Collection<GrantedAuthority> getAuthorities() {
                    GrantedAuthority authority = new GrantedAuthority() {
                        private static final long serialVersionUID = 4866292482579095227L;

                        public String getAuthority() {
                            return user.getRole().getRoleName();
                        }
                    };
                    Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
                    list.add(authority);
                    logger.info("IN AUTHENTICATIONUSERSERVICEIMPL : authorities Set");
                    return list;
                }

                public String getPassword() {
                    logger.info("IN AUTHENTICATIONUSERSERVICEIMPL : users password");
                    return user.getUserPassword();
                }

                public String getUsername() {
                    logger.info("IN AUTHENTICATIONUSERSERVICEIMPL : users username");
                    return user.getEmail();
                }

                public boolean isAccountNonExpired() {
                    return user.isAccountNonExpired();
                }

                public boolean isAccountNonLocked() {
                    return user.isAccountNonLocked();
                }

                public boolean isCredentialsNonExpired() {
                    return user.isCredentialsNonExpired();
                }

                public boolean isEnabled() {
                    return user.isEnabled();
                }
            };
        }
    }
}
