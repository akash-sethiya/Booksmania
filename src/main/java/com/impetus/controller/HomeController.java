package com.impetus.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.mail.MailToUser;
import com.impetus.domain.Address;
import com.impetus.domain.Book;
import com.impetus.domain.Language;
import com.impetus.domain.Subscription;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
import com.impetus.services.api.BookService;
import com.impetus.services.api.LanguageService;
import com.impetus.services.api.SubscriptionService;
import com.impetus.services.api.UserAddressService;
import com.impetus.services.api.UserDetailsService;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeController.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The subscription service. */
    @Autowired
    private SubscriptionService subscriptionService;

    /** The language service. */
    @Autowired
    private LanguageService languageService;

    /** The user details service. */
    @Autowired
    private UserDetailsService userDetailsService;

    /** The user address service. */
    @Autowired
    private UserAddressService userAddressService;
    
    @Autowired
    private BookService bookService;
    
    /*@Autowired
    RegistrationValidator registerValidator;*/

    /** The mail to user. */
    private MailToUser mailToUser = new MailToUser();

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Sets the homepage.
     * 
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     */
    @RequestMapping(value = "/")
    public String setHomepage(Model model, Principal principal,
            HttpSession session) {
        logger.info("IN HOMECONTROLLER: in / mapping setting homepage");
        if (principal != null) {
            logger.info("IN HOMECONTROLLER: principal object is not null");
            Users user = (Users) session.getAttribute("user");
            if (user.getRole().getRoleName().equals("ROLE_USER")) {
                logger.info("IN HOMECONTROLLER: role is USER");
                return "redirect:welcome/home";
            } else {
                logger.info("IN HOMECONTROLLER: role is ADMIN");
                return "redirect:admin/home";
            }
        }
        return "redirect:home";
    }

    /**
     * Sets the home page.
     * 
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = "home")
    public String setHomePage(Model model, Principal principal,
            HttpSession session) throws DAOException, ServiceException {
        logger.info("IN HOMECONTROLLER: in home mapping");
        if (principal != null) {
            logger.info("IN HOMECONTROLLER: redirect to welcome/home mapping");
            return "redirect:welcome/home";
        } else {
            logger.info("IN HOMECONTROLLER: redirect to /home mapping");
            List<Book> bookList=bookService.recentlyAddedBooks();
            model.addAttribute("bookList",bookList);
            return "home";
        }
    }

    /**
     * Sets the registeration page.
     * 
     * @param obj
     *            the obj
     * @param result
     *            the result
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param succeed
     *            the succeed
     * @param failed
     *            the failed
     * @return the string
     * @throws DAOException 
     * @throws ServiceException 
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String setRegisterationPage(
            @ModelAttribute("subscription") Subscription obj,
            BindingResult result, ModelMap model, Principal principal,
            HttpSession session,
            @RequestParam(required = false) String succeed, String failed) throws ServiceException, DAOException {
        logger.info("IN HOMECONTROLLER: in request mapping");
        if (principal != null) {
            logger.info("IN HOMECONTROLLER: redirect to welcome/home");
            return "redirect:welcome/home";
        }
        String msg = "successMSG";
        List<Language> languageList = languageService.getLanguages();
        List<Subscription> subscribes = subscriptionService
                .getSubscriptionPlans();
        model.addAttribute("subscriptions", subscribes);
        model.addAttribute("languageList", languageList);
        model.addAttribute(msg, "");
        if (succeed != null) {
            logger.info("IN HOMECONTROLLER: You are Registered Successfully");
            model.addAttribute(msg, "You are Registered Successfully");
        } else if (failed != null) {
            logger.info("IN HOMECONTROLLER: User Already Exists");
            model.addAttribute(msg, "User Already Exists");
        }
        logger.info("IN HOMECONTROLLER: out request mapping");
        return "register";
    }

    /**
     * Gets the registration details.
     * 
     * @param subscription
     *            the subscription
     * @param user
     *            the user
     * @param userDetails
     *            the user details
     * @param userAddresses
     *            the user addresses
     * @param language
     *            the language
     * @param model
     *            the model
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the registration details
     * @throws ParseException
     *             the parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String getRegistrationDetails(
            @ModelAttribute("subscription") Subscription subscription,
            Users user, UserDetails userDetails, Address userAddresses,
            Language language,BindingResult result, Model model, Principal principal)
            throws ParseException, IOException, ServiceException {
        logger.info("IN HOMECONTROLLER: in registration MApping");
        
        if (principal != null) {
            return "welcome/home";
        }

        if (userService.getUser(user.getEmail()) != null) {
            logger.info("IN HOMECONTROLLER: user is null");
            return "redirect:register?failed";
        }

        logger.info("IN HOMECONTROLLER: Saving USER DETAILS object");
        userDetails.setSubscriptionStartDate(new Date());
        Date expiryDate = subscriptionService.getSubscriptionExpiryDate(
                new Date(), subscription.getSubscriptionId());
        userDetails.setSubscriptionEndDate(expiryDate);
        userDetails.setLanguage(language);
        userDetailsService.saveUserDetails(userDetails);

        logger.info("IN HOMECONTROLLER: Saving USER ADDRESS object");
        userAddressService.addAddress(userAddresses);

        logger.info("IN HOMECONTROLLER: Saving USER object");
        user.setSubscription(subscription);
        user.setUserDetails(userDetailsService.getUserDetails());
        user.setUseraddress(userAddressService.getAddress());
        user.setRequestBookCount(0);

        userService.registerUser(user);

        logger.info("IN HOMECONTROLLER: before Shooting mail");
        mailToUser.shootMail("registration",
                userService.getUser(user.getEmail()), null);
        logger.info("IN HOMECONTROLLER: after shooting mail");
        model.addAttribute("successMSG", "Registered Successfully");
        logger.info("IN HOMECONTROLLER: out registration mapping");
        return "redirect:register?succeed";
    }
    
    @RequestMapping(value="search",method=RequestMethod.POST)
    public String guestSearch(HttpServletRequest request,Model model) throws DAOException, ServiceException
    {
        String searchQuery=request.getParameter("search");
        List<Book> bookList=bookService.getBook(searchQuery);
        model.addAttribute("bookList",bookList);
        return "search";
    }
    
    @RequestMapping(value="showBook")
    public String showSearchedBook(HttpServletRequest request,Model model,@RequestParam("bookId")String bookId) throws NumberFormatException, DAOException, ServiceException
    {
        Book book=bookService.getBook(Integer.parseInt(bookId));
        model.addAttribute("book",book);
        return "showBook";
    }
    
    @RequestMapping(value="contact")
    public String getContactUSPage()
    {
        return "contact";
    }
    
    @RequestMapping(value="forgetPassword")
    public @ResponseBody String forgetPassword(@RequestParam("email") String email) throws IOException, ServiceException
    {
        logger.info("IN HOMECONTROLLER: forget Password mapping EMAIL="+email);
        Users user=userService.getUser(email);
        if(user!=null)
        {
            logger.info("IN HOMECONTROLLER: user is not null, user existed");
            MailToUser mailToUser=new MailToUser();
            mailToUser.shootMail(user);
            return "your password has been sent to "+user.getEmail()+".";
        }
        else
        {
            logger.info("IN HOMECONTROLLER: user is  null, user not existed");
            return "username not existed in our database";
        }
    }
    

    /**
     * Error page1.
     * 
     * @param sessionExpired
     *            the session expired
     * @param sessionAuthentication
     *            the session authentication
     * @param denied
     *            the denied
     * @param model
     *            the model
     * @return the string
     */
    @RequestMapping(value = "error")
    public String errorPage1(
            @RequestParam(required = false) String sessionExpired,
            String sessionAuthentication, String denied, Model model) {
        String errorMessage = "<p>Oops The page you are looking for is not is this</p><br>back to homepage <a href=\"\">Click here to go Homepage</a> ";
        if (sessionExpired != null) {
            errorMessage = "<p>Your Session is Expired.<br>back to homepage.</p><a href=\"\">Click here to go Homepage</a></p>";
        }
        if (sessionAuthentication != null) {
            errorMessage = "<p>Error occured in session authentication</p><br><a href=\"\">Click here to go Homepage</a>";
        }
        if (denied != null) {
            errorMessage = "<p>You have not permit to access admin functionality</p><br><p>Go back and never try to intrude.</p><br>Thank You. Go back to home page:<a href=\"\">Click here to go Homepage</a></p>";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
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
