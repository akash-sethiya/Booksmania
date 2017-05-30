package com.impetus.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.mail.MailToUser;
import com.impetus.domain.Book;
import com.impetus.domain.Request;
import com.impetus.domain.Users;
import com.impetus.services.api.BookService;
import com.impetus.services.api.RequestService;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestController.
 */
@Controller
@RequestMapping("/")
public class RequestController {

    /** The request service. */
    @Autowired
    private RequestService requestService;

    /** The book service. */
    @Autowired
    private BookService bookService;

    /** The mail to user. */
    private MailToUser mailToUser = new MailToUser();

    /** The Constant DELIEVERY_PENDING_STATUS. */
    private static final Integer DELIEVERY_PENDING_STATUS = 0;

    /** The Constant RETURN_PENDING_STATUS. */
    private static final Integer RETURN_PENDING_STATUS = 1;

    /** The Constant NULL_RETURN_STATUS. */
    private static final Integer NULL_RETURN_STATUS = 2;

    /** The Constant DAYSEXTENSION. */
    private static final Integer DAYSEXTENSION = 30;

    private String reqList = "requestList";

    private Logger logger = LoggerFactory.getLogger(RequestController.class);

    /**
     * User request for book.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param bookId
     *            the book id
     * @param model
     *            the model
     * @return the string
     * @throws ServiceException 
     * @throws DAOException 
     * @throws NumberFormatException 
     */
    @RequestMapping(value = "welcome/request")
    public String userRequestForBook(HttpSession session,
            HttpServletRequest request, Principal principal,
            @RequestParam("bookId") String bookId, Model model) throws NumberFormatException, DAOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/request Mapping");
        Book book = bookService.getBook(Integer.parseInt(bookId));
        model.addAttribute("book", book);
        logger.info("IN REQUEST CONTROLLER: out welcome/request Mapping");
        return "welcome/request";
    }

    /**
     * Sets the user requests.
     * 
     * @param userRequest
     *            the user request
     * @param book
     *            the book
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @param bookId
     *            the book id
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     * @throws DAOException 
     * @throws NumberFormatException 
     */
    @RequestMapping(value = "welcome/userRequests", method = RequestMethod.POST)
    public String setUserRequests(
            @ModelAttribute("request") Request userRequest, Book book,
            HttpSession session, Principal principal, Model model,
            @RequestParam("bookId") String bookId) throws IOException,
            ServiceException, NumberFormatException, DAOException {
        logger.info("IN REQUEST CONTROLLER: in welcome/userRequests Mapping");
        Users user = (Users) session.getAttribute("user");
        userRequest.setUser(user);
        userRequest.setBook(bookService.getBook(Integer.parseInt(bookId)));
        userRequest.setIssuedDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userRequest.getIssuedDate());
        calendar.add(Calendar.DATE, DAYSEXTENSION);
        userRequest.setExpectedReturnedDate(calendar.getTime());
        requestService.addRequest(userRequest);
        logger.info("IN REQUEST CONTROLLER: Before sending mail");
        mailToUser.shootMail("USER_DELIEVERY_REQUEST", user, userRequest);
        logger.info("IN REQUEST CONTROLLER: after sending mail");
        logger.info("IN REQUEST CONTROLLER: out welcome/request Mapping");
        return "redirect:home";
    }

    /**
     * User all requests.
     * 
     * @return the string
     */
    @RequestMapping(value = "welcome/allRequests")
    public String userAllRequests() {
        logger.info("IN REQUEST CONTROLLER: in welcome/allRequests Mapping");
        return "welcome/allRequests";
    }

    /**
     * Gets the user requests.
     * 
     * @param userRequest
     *            the user request
     * @param book
     *            the book
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @return the user requests
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/delieveryRequests")
    public String getUserRequests(Request userRequest, Book book,
            HttpSession session, HttpServletRequest request,
            Principal principal, Model model) throws IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/delieveryRequests Mapping");
        Users user = (Users) session.getAttribute("user");
        List<Request> requestList = requestService.getRequests(user,
                DELIEVERY_PENDING_STATUS);
        model.addAttribute(reqList, requestList);
        logger.info("IN REQUEST CONTROLLER: out welcome/delieveryRequests Mapping");
        return "welcome/delieveryRequests";
    }

    /**
     * Cancel user request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @param requestId
     *            the request id
     * @return the string
     * @throws NumberFormatException
     *             the number format exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "welcome/cancelRequest")
    public String cancelUserRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model,
            @RequestParam("requestId") String requestId)
            throws NumberFormatException, IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/cancelRequest Mapping");
        Users user = (Users) session.getAttribute("user");
        Request userRequest = requestService.cancelRequest(
                Integer.parseInt(requestId), user.getRole().getRoleName());
        logger.info("IN REQUEST CONTROLLER: before Sending mail");
        mailToUser.shootMail("USER_DELIEVERY_CANCEL_REQUEST", user, userRequest);
        logger.info("IN REQUEST CONTROLLER: after sending mail");
        logger.info("IN REQUEST CONTROLLER: out welcome/cancelRequest Mapping");
        return "redirect:delieveryRequests";
    }

    /**
     * Admin get user reqeusts.
     * 
     * @param userRequest
     *            the user request
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    @RequestMapping(value = "admin/userRequests")
    public String adminGetUserReqeusts(Request userRequest,
            HttpSession session, HttpServletRequest request,
            Principal principal, Model model) throws IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/userRequests Mapping");
        List<Request> delieveryRequestList = requestService
                .getRequests("PendingDelievery");
        List<Request> returnRequestList = requestService
                .getRequests("PendingReturn");
        model.addAttribute(reqList, delieveryRequestList);
        model.addAttribute("returnRequestList", returnRequestList);
        logger.info("IN REQUEST CONTROLLER: out welcome/userRequests Mapping");
        return "admin/userRequests";
    }

    /**
     * Admin cancel request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @param requestId
     *            the request id
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "admin/closeRequest")
    public String adminCancelRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model,
            @RequestParam("id") String requestId) throws IOException,
            ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/closeRequest Mapping");
        Request userRequest = requestService.cancelRequest(
                Integer.parseInt(requestId), null);
        logger.info("IN REQUEST CONTROLLER: before sending mail");
        mailToUser.shootMail("ADMIN_DELIEVERY_CLOSE_REQUEST",userRequest.getUser(), userRequest);
        logger.info("IN REQUEST CONTROLLER: after sending mail");
        return "redirect:userRequests";
    }

    /**
     * Admin close return request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param result
     *            the result
     * @param requestId
     *            the request id
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "admin/closeReturnRequest")
    public String adminCloseReturnRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model,
            @RequestParam("id") String requestId) throws IOException,
            ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/closeReturnRequest Mapping");
        Request userRequest = requestService.cancelReturnRequest(Integer.parseInt(requestId), null);
        logger.info("IN REQUEST CONTROLLER: before sending mail");
        mailToUser.shootMail("ADMIN_RETURN_CLOSE_REQUEST",userRequest.getUser(), userRequest);
        logger.info("IN REQUEST CONTROLLER: after sending mail");
        return "redirect:userRequests";
    }

    /**
     * User return requests.
     * 
     * @param userRequest
     *            the user request
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/returnRequests")
    public String userReturnRequests(Request userRequest, HttpSession session,
            HttpServletRequest request, Principal principal, Model model)
            throws IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/returnRequests Mapping");
        Users user = (Users) session.getAttribute("user");
        List<Request> requestList = requestService.getRequests(user,
                NULL_RETURN_STATUS);
        model.addAttribute(reqList, requestList);
        logger.info("IN REQUEST CONTROLLER: out welcome/returnRequest Mapping");
        return "welcome/returnRequests";
    }

    /**
     * User return request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param requestId
     *            the request id
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "welcome/returnRequest")
    public String userReturnRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model,
            @RequestParam("requestId") String requestId) throws IOException,
            ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/returnRequest Mapping");
        Users user = (Users) session.getAttribute("user");
        Request userRequest = requestService.generateReturnRequest(Integer
                .parseInt(requestId));
        logger.info("IN REQUEST CONTROLLER: before sending mail");
        mailToUser.shootMail("USER_RETURN_REQUEST", user, userRequest);
        logger.info("IN REQUEST CONTROLLER: after sending mail");
        return "redirect:returnRequests";
    }

    /**
     * User return pending request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    @RequestMapping(value = "welcome/returnPendingRequests")
    public String userReturnPendingRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model)
            throws IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/returnPendingRequests mapping");
        Users user = (Users) session.getAttribute("user");
        List<Request> requestList = requestService.getRequests(user,
                RETURN_PENDING_STATUS);
        model.addAttribute(reqList, requestList);
        logger.info("IN REQUEST CONTROLLER: out welcome/returnPendingRequests mapping");
        return "welcome/returnPendingRequests";
    }

    /**
     * User cancel return request.
     * 
     * @param session
     *            the session
     * @param request
     *            the request
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @param requestId
     *            the request id
     * @return the string
     * @throws NumberFormatException
     *             the number format exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    @RequestMapping(value = "welcome/cancelReturnRequest")
    public String userCancelReturnRequest(HttpSession session,
            HttpServletRequest request, Principal principal, Model model,
            @RequestParam("requestId") String requestId)
            throws NumberFormatException, IOException, ServiceException {
        logger.info("IN REQUEST CONTROLLER: in welcome/cancelReturnRequest mapping");
        Users user = (Users) session.getAttribute("user");
        Request userRequest = requestService.cancelReturnRequest(
                Integer.parseInt(requestId), user.getRole().getRoleName());
        if (userRequest != null) {
            logger.info("IN REQUEST CONTROLLER: in IF before sending mail");
            mailToUser.shootMail("USER_RETURN_CANCEL_REQUEST", user,userRequest);
            logger.info("IN REQUEST CONTROLLER: in IF after sending mail");
        }
        logger.info("IN REQUEST CONTROLLER: out welcome/cancelReturnRequest mapping");
        return "redirect:returnPendingRequests";
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
