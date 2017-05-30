package com.impetus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionController.
 */
@Controller
@RequestMapping("/")
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * Gets the 404 page.
     * 
     * @return the 404 page
     */
    @RequestMapping(value = "404")
    public String get404Page() {
        logger.info("In EXCEPTIONCONTROLLER: 404 page");
        return "redirect:errorpage?pageNotFound";
    }
    
    @RequestMapping(value = "405")
    public String get405Page() {
        logger.info("In EXCEPTIONCONTROLLER: 405 page");
        return "redirect:errorpage?getNotSupported";
    }

    /**
     * Gets the 403 page.
     * 
     * @return the 403 page
     */
    @RequestMapping(value = "403")
    public String get403Page() {
        logger.info("In EXCEPTIONCONTROLLER: 403 page");
        return "redirect:errorpage?forbidden";
    }

    /**
     * Gets the 500 page.
     * 
     * @return the 500 page
     */
    @RequestMapping(value = "500")
    public String get500Page() {
        logger.info("In EXCEPTIONCONTROLLER: 500 page");
        return "redirect:errorpage?technicalIssue";
    }

    /**
     * Gets the 400 page.
     * 
     * @return the 400 page
     */
    @RequestMapping("400")
    public String get400Page() {
        logger.info("In EXCEPTIONCONTROLLER: 400 page");
        return "redirect:errorpage?badRequest";
    }

    /**
     * Gets the 404welcome page.
     * 
     * @return the 404welcome page
     */
    @RequestMapping(value = "welcom/404")
    public String get404welcomePage() {
        logger.info("In EXCEPTIONCONTROLLER: welcome/404 page");
        return "redirect:welcome/errorpage?pageNotFound";
    }
    
    @RequestMapping(value = "welcom/405")
    public String get405welcomePage() {
        logger.info("In EXCEPTIONCONTROLLER: welcome/405 page");
        return "redirect:welcome/errorpage?getNotSupported";
    }

    /**
     * Gets the 403welcome page.
     * 
     * @return the 403welcome page
     */
    @RequestMapping(value = "welcome/403")
    public String get403welcomePage() {
        logger.info("In EXCEPTIONCONTROLLER: welcome/403 page");
        return "redirect:welcome/errorpage?forbidden";
    }

    /**
     * Gets the 500welcome page.
     * 
     * @return the 500welcome page
     */
    @RequestMapping(value = "welcome/500")
    public String get500welcomePage() {
        logger.info("In EXCEPTIONCONTROLLER: welcome/500 page");
        return "redirect:welcome/errorpage?technicalIssue";
    }

    /**
     * Gets the 400welcome page.
     * 
     * @return the 400welcome page
     */
    @RequestMapping("welcome/400")
    public String get400welcomePage() {
        logger.info("In EXCEPTIONCONTROLLER: welcome/400 page");
        return "redirect:welcome/errorpage?badRequest";
    }

    /**
     * Gets the 404admin page.
     * 
     * @return the 404admin page
     */
    @RequestMapping(value = "admin/404")
    public String get404adminPage() {
        logger.info("In EXCEPTIONCONTROLLER: admin/404 page");
        return "redirect:admin/errorpage?pageNotFound";
    }

    @RequestMapping(value = "admin/405")
    public String get405adminPage() {
        logger.info("In EXCEPTIONCONTROLLER: admin/405 page");
        return "redirect:welcome/errorpage?getNotSupported";
    }
    
    /**
     * Gets the 403admin page.
     * 
     * @return the 403admin page
     */
    @RequestMapping(value = "admin/403")
    public String get403adminPage() {
        logger.info("In EXCEPTIONCONTROLLER: admin/403 page");
        return "redirect:admin/errorpage?forbidden";
    }

    /**
     * Gets the 500admin page.
     * 
     * @return the 500admin page
     */
    @RequestMapping(value = "admin/500")
    public String get500adminPage() {
        logger.info("In EXCEPTIONCONTROLLER: admin/500 page");
        return "redirect:admin/errorpage?technicalIssue";
    }

    /**
     * Gets the 400admin page.
     * 
     * @return the 400admin page
     */
    @RequestMapping("admin/400")
    public String get400adminPage() {
        logger.info("In EXCEPTIONCONTROLLER: admin/400 page");
        return "redirect:admin/errorpage?badRequest";
    }

    /**
     * Gets the error page.
     * 
     * @param pageNotFound
     *            the page not found
     * @param forbidden
     *            the forbidden
     * @param technicalIssue
     *            the technical issue
     * @param badRequest
     *            the bad request
     * @param model
     *            the model
     * @return the error page
     */
    @RequestMapping(value = "errorpage")
    public String getErrorPage(String pageNotFound, String forbidden,
            String technicalIssue, String badRequest,String getNotSupported, Model model) {
        logger.info("In EXCEPTIONCONTROLLER: ERROR page");
        String errorMsg = "";
        if (pageNotFound != null) {
            logger.info("In EXCEPTIONCONTROLLER: page not found error");
            errorMsg = "The page you are looking for is not available.";
        }
        if (forbidden != null) {
            logger.info("In EXCEPTIONCONTROLLER: forbidden error");
            errorMsg = "Sorry,You don't have permission to access this page.";
        }
        if (technicalIssue != null || badRequest != null) {
            logger.info("In EXCEPTIONCONTROLLER: technical issue error");
            errorMsg = "There is some technical Issue in site.We are looking for the solution.Plase access this some time later.Soory for inconvinience";
        }
        if(getNotSupported!=null){
            logger.info("In EXCEPTIONCONTROLLER: getNotSupported error");
            errorMsg = "You are not allowed to hit this URL directly from addressBar.";
        }
        model.addAttribute("errorMsg", errorMsg);
        logger.info("In EXCEPTIONCONTROLLER: out ERROR page");
        return "errorpage";
    }

    /**
     * Gets the welcome error page.
     * 
     * @param pageNotFound
     *            the page not found
     * @param forbidden
     *            the forbidden
     * @param technicalIssue
     *            the technical issue
     * @param badRequest
     *            the bad request
     * @param model
     *            the model
     * @return the welcome error page
     */
    @RequestMapping(value = "welcome/errorpage")
    public String getWelcomeErrorPage(String pageNotFound, String forbidden,
            String technicalIssue,String getNotSupported, String badRequest, Model model) {
        logger.info("In EXCEPTIONCONTROLLER: WELCOME ERROR page");
        String errorMsg = "";
        if (pageNotFound != null) {
            logger.info("In EXCEPTIONCONTROLLER: page not found error");
            errorMsg = "The page you are looking for is not available.";
        }
        if (forbidden != null) {
            logger.info("In EXCEPTIONCONTROLLER: forbidden error");
            errorMsg = "Sorry,You don't have permission to access this page.";
        }
        if (technicalIssue != null || badRequest != null) {
            logger.info("In EXCEPTIONCONTROLLER: technical issue error");
            errorMsg = "There is some technical Issue in site.We are looking for the solution.Plase access this some time later.Soory for inconvinience";
        }
        if(getNotSupported!=null){
            logger.info("In EXCEPTIONCONTROLLER: getNotSupported error");
            errorMsg = "You are not allowed to hit this URL directly from addressBar.";
        }
        model.addAttribute("errorMsg", errorMsg);
        logger.info("In EXCEPTIONCONTROLLER: out WELCOME ERROR page");
        return "welcome/errorpage";
    }

    /**
     * Gets the admin error page.
     * 
     * @param pageNotFound
     *            the page not found
     * @param forbidden
     *            the forbidden
     * @param technicalIssue
     *            the technical issue
     * @param badRequest
     *            the bad request
     * @param model
     *            the model
     * @return the admin error page
     */
    @RequestMapping(value = "admin/errorpage")
    public String getAdminErrorPage(String pageNotFound, String forbidden,
            String technicalIssue,String getNotSupported,String badRequest, Model model) {
        logger.info("In EXCEPTIONCONTROLLER: ADMIN ERROR page");
        String errorMsg = "";
        if (pageNotFound != null) {
            logger.info("In EXCEPTIONCONTROLLER: page not found error");
            errorMsg = "The page you are looking for is not available.";
        }
        if (forbidden != null) {
            logger.info("In EXCEPTIONCONTROLLER: forbidden error");
            errorMsg = "Sorry,You don't have permission to access this page.";
        }
        if (technicalIssue != null || badRequest != null) {
            logger.info("In EXCEPTIONCONTROLLER: technical issue error");
            errorMsg = "There is some technical Issue in site.We are looking for the solution.Plase access this some time later.Soory for inconvinience";
        }
        if(getNotSupported!=null){
            logger.info("In EXCEPTIONCONTROLLER: getNotSupported error");
            errorMsg = "You are not allowed to hit this URL directly from addressBar.";
        }
        model.addAttribute("errorMsg", errorMsg);
        logger.info("In EXCEPTIONCONTROLLER:out ADMIN ERROR page");
        return "admin/errorpage";
    }
}
