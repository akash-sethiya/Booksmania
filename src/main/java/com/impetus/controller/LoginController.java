package com.impetus.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

    private static final String LOGIN = "login";
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Sets the get login page.
     * 
     * @param failed
     *            the failed
     * @param denied
     *            the denied
     * @param logout
     *            the logout
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     */
    @RequestMapping(value = LOGIN)
    public String setGetLoginPage(@RequestParam(required = true) String failed,
            String denied, String logout, Model model, Principal principal,
            HttpSession session) {
        logger.info("IN LOGIN CONTROLLER setGetLogin Method");
        if (principal != null) {
            logger.info("session is valid redirect to registered user homepage");
            return "welcome/home";
        }
        String loginMessage = "";
        if (failed != null) {
            loginMessage = "Wrong Credentials";
            logger.info(loginMessage+" Entered");
            model.addAttribute("loginMessage", loginMessage);
            return LOGIN;
        }
        if (denied != null) {
            logger.info("denied URL is try to access");
            return "error?denied";
        }
        if (logout != null) {
            logger.info("user logged out");
            return "home";
        }
        model.addAttribute("loginMessage", loginMessage);
        logger.info("send user to login page");
        return LOGIN;

    }

    /**
     * Sets the login page.
     * 
     * @param failed
     *            the failed
     * @param denied
     *            the denied
     * @param logout
     *            the logout
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     */
    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public String setLoginPage(@RequestParam(required = false) String failed,
            String denied, String logout, Model model, Principal principal,
            HttpSession session) {
        logger.info("IN LOGIN CONTROLLER setLoginPage Method");
        String loginMessage = "";
        if (failed != null) {
            logger.info(loginMessage+" Entered");
            loginMessage = "Wrong Credentials";
            model.addAttribute("loginMessage", loginMessage);
            return LOGIN;
        }
        if (denied != null) {
            logger.info("denied URL is try to access");
            return "error?denied";
        }
        if (logout != null) {
            logger.info("user logged out");
            return "home";
        }
        model.addAttribute("loginMessage", loginMessage);
        logger.info("send user to login page");
        return LOGIN;

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
