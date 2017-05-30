package com.impetus.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.ServiceException;
import com.impetus.domain.Users;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomController.
 */
@Controller
@RequestMapping("/")
public class CustomController {

    /** The user service. */
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(CustomController.class);

    /**
     * Sets the cutom page.
     * 
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     * @throws ServiceException 
     */
    @RequestMapping(value = "custom")
    public String setCutomPage(Model model, Principal principal,
            HttpSession session) throws ServiceException {
        logger.info("IN CUSTOMCONTROLLER: in custom mapping");
        Authentication a = SecurityContextHolder.getContext()
                .getAuthentication();
        UserDetails userDetails = (UserDetails) a.getPrincipal();
        Users user = userService.getUser(userDetails.getUsername());
        if (user.getRole().getRoleName().equals("ROLE_ADMIN")) {
            logger.info("IN CUSTOMCONTROLLER: role is admin");
            return "redirect:admin/home";
        } else {
            logger.info("IN CUSTOMCONTROLLER: role is User");
            return "redirect:welcome/home";
        }
    }
    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleAllException(ServiceException e)
    {
       logger.error("System Exception generated"+ e);
       ModelAndView mv= new ModelAndView();
       
        mv.setViewName("errorpage");
        mv.addObject("errorMsg", e.getMessage());
        return mv;
    }

}
