package com.impetus.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.domain.Address;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Request;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;
import com.impetus.services.api.BookService;
import com.impetus.services.api.HistoryService;
import com.impetus.services.api.MyShelfService;
import com.impetus.services.api.RequestService;
import com.impetus.services.api.SubscriptionService;
import com.impetus.services.api.UserAddressService;
import com.impetus.services.api.UserDetailsService;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class WelcomeController.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The user details service. */
    @Autowired
    private UserDetailsService userDetailsService;

    /** The user address service. */
    @Autowired
    private UserAddressService userAddressService;

    /** The book service. */
    @Autowired
    private BookService bookService;

    /** The my shelf service. */
    @Autowired
    private MyShelfService myShelfService;

    /** The request service. */
    @Autowired
    private RequestService requestService;

    /** The history service. */
    @Autowired
    private HistoryService historyService;

    /** The subscription service. */
    @Autowired
    private SubscriptionService subscriptionService;
    
    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    private String reqList = "requestList";
    private String bList = "bookList";

    private static final String WELCOMEHOME = "welcome/home";

    /**
     * Gets the user homepage.
     * 
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param model
     *            the model
     * @return the user homepage
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = WELCOMEHOME, method = RequestMethod.GET)
    public String getUserHomepage(Principal principal, HttpSession session,
            Model model) throws IOException, DAOException, ServiceException {
        logger.info("User Home page after login");
        Authentication a = SecurityContextHolder.getContext()
                .getAuthentication();
        UserDetails userDetails = (UserDetails) a.getPrincipal();

        Users user = userService.getUser(userDetails.getUsername());

        session.setAttribute("user", user);

        List<Book> bookList = bookService.getBook();
        List<Integer> myShelf = myShelfService.getShelfItems(user);
        List<Integer> requestBook = new ArrayList<Integer>();
        List<Request> requestL = requestService.getRequests(user.getUserId());
        for (Request r : requestL) {
            requestBook.add(r.getBook().getBookId());
        }
        model.addAttribute(reqList, requestBook);
        model.addAttribute("shelfItemList", myShelf);
        model.addAttribute(bList, bookList);
        logger.info(user.getEmail()+" is logged in");
        return WELCOMEHOME;
    }

    /**
     * Gets the search page.
     * 
     * @param book
     *            the book
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param model
     *            the model
     * @param searchQuery
     *            the search query
     * @return the search page
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = WELCOMEHOME, method = RequestMethod.POST)
    public String getSearchPage(@ModelAttribute("book") Book book,
            Principal principal, HttpSession session,Model model,
            @RequestParam("search") String searchQuery) throws IOException, DAOException, ServiceException {
        logger.info("in method which perform search query");
        if (searchQuery == null || searchQuery.equals("")) {
            logger.info("query is null");
            return "redirect:home";
        }
        Users user = (Users) session.getAttribute("user");
        List<Integer> myShelf = myShelfService.getShelfItems(user);
        List<Book> bookList = bookService.getBook(searchQuery);
        List<Integer> requestBook = new ArrayList<Integer>();
        List<Request> requestL = requestService.getRequests(user.getUserId());
        for (Request r : requestL) {
            requestBook.add(r.getBook().getBookId());
        }
        model.addAttribute(reqList, requestBook);
        model.addAttribute(bList, bookList);
        model.addAttribute("shelfItemList", myShelf);
        logger.info("send user to welcome/home page");
        return WELCOMEHOME;
    }

    /**
     * Gets the profile page.
     * 
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param model
     *            the model
     * @return the profile page
     */
    @RequestMapping(value = "welcome/profile")
    public String getProfilePage(Principal principal, HttpSession session,
            Model model) {
        logger.info("User's profile page/Mapping");
        return "welcome/profile";
    }

    /**
     * Show update profile page.
     * 
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param model
     *            the model
     * @return the string
     */
    @RequestMapping(value = "welcome/update")
    public String showUpdateProfilePage(Principal principal,
            HttpSession session, Model model) {
        logger.info("welcom/update get method");
        return "welcome/update";
    }

    /**
     * Update user profile.
     * 
     * @param subscriptions
     *            the subscriptions
     * @param userAddress
     *            the user address
     * @param user
     *            the user
     * @param userDetails
     *            the user details
     * @param result
     *            the result
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param model
     *            the model
     * @return the string
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/update", method = RequestMethod.POST)
    public String updateUserProfile(
            @ModelAttribute("subscription") Subscription subscriptions,
            Address userAddress, Users user,
            com.impetus.domain.UserDetails userDetails, BindingResult result,
            HttpSession session, Model model) throws ServiceException {
        logger.info("welcome/update mapping post method");
        Users tempUser = (Users) session.getAttribute("user");
        Address tempUserAddress = tempUser.getUseraddress();
        com.impetus.domain.UserDetails tempUserDetails = tempUser
                .getUserDetails();

        /*----------Update User Address----------------*/
        userAddress.setCountry(tempUserAddress.getCountry());
        userAddressService.updateAddress(userAddress);
        logger.info("user addresses updated");
        /*------------------------------------------------*/

        /*------------Update User Details----------------------*/
        userDetails.setDob(tempUserDetails.getDob());
        userDetails.setSubscriptionStartDate(tempUserDetails
                .getSubscriptionStartDate());
        userDetails.setSubscriptionEndDate(tempUserDetails
                .getSubscriptionEndDate());
        userDetails.setLanguage(tempUserDetails.getLanguage());
        userDetails.setGender(tempUserDetails.getGender());
        userDetailsService.updateUserDetails(userDetails);
        logger.info("user details updated");
        /*-----------------------------------------------------*/

        /*---------------------Update User----------------------------*/
        user.setAccountNonExpired(tempUser.isAccountNonExpired());
        user.setAccountNonLocked(tempUser.isAccountNonLocked());
        user.setCredentialsNonExpired(tempUser.isCredentialsNonExpired());
        user.setEnabled(tempUser.isEnabled());
        user.setUserPassword(tempUser.getUserPassword());
        user.setRole(tempUser.getRole());
        user.setSubscription(tempUser.getSubscription());
        user.setStatus(true);
        user.setUserDetails(userDetails);
        user.setUseraddress(userAddress);
        user.setRequestBookCount(tempUser.getRequestBookCount());
        logger.info("user updated");

        /*-------------------------------------------------------------*/

        userService.updateUser(user);
        return "redirect:home";
    }

    /**
     * Adds the to shelf.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param bookId
     *            the book id
     * @return the string
     * @throws ServiceException 
     * @throws DAOException 
     * @throws NumberFormatException 
     */
    @RequestMapping(value = "welcome/add")
    public String addToShelf(HttpSession session, HttpServletRequest request,
            Principal principal, @RequestParam("bookId") String bookId) throws NumberFormatException, DAOException, ServiceException {
        logger.info("addToShlef:::: welcome/add mapping");
        Book book = bookService.getBook(Integer.parseInt(bookId));
        Users user = (Users) session.getAttribute("user");
        myShelfService.addToShelf(book, user);
        return "redirect:home";
    }

    /**
     * Removes the from shelf.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param bookId
     *            the book id
     * @return the string
     * @throws ServiceException 
     * @throws DAOException 
     * @throws NumberFormatException 
     */
    @RequestMapping(value = "welcome/remove")
    public String removeFromShelf(HttpSession session,
            HttpServletRequest request, Principal principal,
            @RequestParam("bookId") String bookId) throws NumberFormatException, DAOException, ServiceException {
        logger.info("remove from shelf:::: welcome/remove mapping");
        Users user = (Users) session.getAttribute("user");
        myShelfService.deleteFromShelf(Integer.parseInt(bookId),
                user.getUserId());
        return "redirect:myShelf";
    }

    /**
     * My shelf page.
     * 
     * @param session
     *            the session
     * @param principal
     *            the principal
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = "welcome/myShelf")
    public String myShelfPage(HttpSession session, Principal principal,
            HttpServletRequest request, Model model) throws IOException, DAOException, ServiceException {
        logger.info("welcome/myShelf mapping:::: Viewing virtual-shelf");
        Users user = (Users) session.getAttribute("user");
        List<Book> bookList = myShelfService.getShelf(user);
        List<Integer> requestBook = new ArrayList<Integer>();
        List<Request> requestL = requestService.getRequests(user.getUserId());
        for (Request r : requestL) {
            requestBook.add(r.getBook().getBookId());
        }
        model.addAttribute(reqList, requestBook);
        model.addAttribute(bList, bookList);
        return "welcome/myShelf";
    }

    /**
     * Recommend books to user.
     * 
     * @param session
     *            the session
     * @param principal
     *            the principal
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = "welcome/recommend")
    public String recommendBooksToUser(HttpSession session,
            Principal principal, HttpServletRequest request, Model model)
            throws IOException, DAOException, ServiceException {
        logger.info("in welcome/recommend mapping:::: Recommendation books to user");
        Users user = (Users) session.getAttribute("user");
        List<Book> bookList = historyService.reCommendBook(user);

        List<Integer> myShelf = myShelfService.getShelfItems(user);
        List<Integer> requestBook = new ArrayList<Integer>();
        List<Request> requestL = requestService.getRequests(user.getUserId());
        for (Request r : requestL) {
            requestBook.add(r.getBook().getBookId());
        }
        model.addAttribute(reqList, requestBook);
        model.addAttribute(bList, bookList);
        model.addAttribute("shelfItemList", myShelf);
        return "welcome/recommend";
    }

    /**
     * Gets the user subscription page.
     * 
     * @param session
     *            the session
     * @param principal
     *            the principal
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the user subscription page
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/mySubscription")
    public String getUserSubscriptionPage(HttpSession session,
            Principal principal, HttpServletRequest request, Model model) throws ServiceException {
        logger.info("gerUserSubscrpition Page:::::welcome/mySubscription maping");
        Users tempUser = (Users) session.getAttribute("user");
        Users user = userService.getUser(tempUser.getUserId());
        model.addAttribute("user", user);

        List<Subscription> subscriptionList = subscriptionService
                .getSubscriptionPlans();
        model.addAttribute("subscriptionList", subscriptionList);
        /*-----------------Subscription History Code-----------------*/
        logger.info("maintaing Subscription History of user");
        List<SubscriptionHistory> subscriptionHistoryList = subscriptionService
                .getUserSubscriptionHistory(user);
        List<Subscription> subscriptionUserList = new ArrayList<Subscription>();
        for (SubscriptionHistory history : subscriptionHistoryList) {
            subscriptionUserList.add(subscriptionService
                    .getSubscriptionPlan(history.getSubscriptionId()));
        }
        model.addAttribute("userSubscriptionService", subscriptionUserList);
        model.addAttribute("subscriptionHistory", subscriptionHistoryList);
        /*----------------------------------------------------------------*/

        return "welcome/mySubscription";
    }

    /**
     * Update user subscription plan.
     * 
     * @param session
     *            the session
     * @param principal
     *            the principal
     * @param request
     *            the request
     * @param model
     *            the model
     * @param id
     *            the id
     * @return the string
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/updateUserSubscription")
    public String updateUserSubscriptionPlan(HttpSession session,
            Principal principal, HttpServletRequest request, Model model,
            @RequestParam("id") String id) throws ServiceException {
        logger.info("updating user subscription Plan");
        SubscriptionHistory subscriptionHistory = new SubscriptionHistory();
        Users user = (Users) session.getAttribute("user");
        com.impetus.domain.UserDetails userDetails = user.getUserDetails();
        Integer subscriptionId = Integer.parseInt(id);
        Subscription subscription = subscriptionService
                .getSubscriptionPlan(subscriptionId);
        logger.info("SUBSCRIPTION NAME="+subscription.getSubscriptionName());
        
        /*---------Saving user subscription history*/
        logger.info("saving user subscription HIstory");
        subscriptionHistory.setSubscriptionId(user.getSubscription().getSubscriptionId());
        subscriptionHistory.setUserId(user.getUserId());
        subscriptionHistory.setSubscriptionStartDate(userDetails
                .getSubscriptionStartDate());
        subscriptionHistory.setSubscriptionEndDate(userDetails
                .getSubscriptionEndDate());
        subscriptionHistory.setRequestedBookCount(user.getRequestBookCount());
        subscriptionService.saveUserSubscriptionHistory(subscriptionHistory);
        /*------------------------------------------------*/

        user.setSubscription(subscription);
        user.setStatus(true);
        user.setRequestBookCount(0);
        
        userDetails.setSubscriptionStartDate(new Date());
        Date expiryDate = subscriptionService.getSubscriptionExpiryDate(
                new Date(), subscription.getSubscriptionId());
        userDetails.setSubscriptionEndDate(expiryDate);
        userDetailsService.updateUserDetails(userDetails);

        userService.updateUser(user);
        logger.info("user subscription plan updated");

        return "redirect:mySubscription";
    }

    /**
     * User histtory.
     * 
     * @param session
     *            the session
     * @param principal
     *            the principal
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the string
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = "welcome/history")
    public String userHisttory(HttpSession session, Principal principal,
            HttpServletRequest request, Model model) throws DAOException, ServiceException {
        logger.info("welcome/history mapping");
        Users user = (Users) session.getAttribute("user");
        List<History> historyList = (List<History>) historyService
                .getHistory(user);

        List<Book> bookList = new ArrayList<Book>();

        for (History h : historyList) {
                bookList.add(bookService.getBook(h.getBookId()));
        }

        model.addAttribute("historyList", historyList);
        model.addAttribute(bList, bookList);
        return "welcome/history";
    }
    
    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleAllException(ServiceException e)
    {
       logger.error("System Exception generated"+ e);
       ModelAndView mv= new ModelAndView();
       
        mv.setViewName("welcome/errorpage");
        mv.addObject("errorMsg", e.getMessage());
        return mv;
    }

}
