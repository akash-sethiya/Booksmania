package com.impetus.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.ServiceRuntimeException;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.History;
import com.impetus.domain.Language;
import com.impetus.domain.Request;
import com.impetus.domain.Subscription;
import com.impetus.domain.Users;
import com.impetus.services.api.BookCategoryService;
import com.impetus.services.api.BookService;
import com.impetus.services.api.HistoryService;
import com.impetus.services.api.LanguageService;
import com.impetus.services.api.MyShelfService;
import com.impetus.services.api.PdfService;
import com.impetus.services.api.RequestService;
import com.impetus.services.api.SubscriptionService;
import com.impetus.services.api.UserService;
import com.lowagie.text.DocumentException;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 */
@Controller
@RequestMapping(value = "/")
public class AdminController {

    /** The language service. */
    @Autowired
    private LanguageService languageService;

    /** The book service. */
    @Autowired
    private BookService bookService;

    /** The book category servie. */
    @Autowired
    private BookCategoryService bookCategoryServie;

    /** The subscription service. */
    @Autowired
    private SubscriptionService subscriptionService;

    /** The pdf service. */
    @Autowired
    private PdfService pdfService;

    /** The history service. */
    @Autowired
    private HistoryService historyService;

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The my shelf service. */
    @Autowired
    private MyShelfService myShelfService;

    /** The request service. */
    @Autowired
    private RequestService requestService;
    
    

    private static final String ADMINHOME = "admin/home";
    private static final String ADMINOPERATIONS = "admin/operations";
    private static final String ADMINADDBOOK = "admin/addBook";
    private static final String ADMINVIEWBOOK = "admin/viewBook";
    private static final String ADMINSUBSCRIPTION = "admin/subscription";
    private static final String ADMINVIEWSUBSCRIPTION = "admin/viewSubscription";
    private static final String ADMINUPDATE = "admin/update";
    private static final String ADMINDELETESUBSCRIPTION = "admin/deleteSubscription";
    private static final String ADMINREPORT = "admin/report";
    private static final String ADMINACTIVEUSERS = "admin/activeUsers";
    private static final String ADMINDELETEBOOK = "admin/delete";
    
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * Admin home page.
     * 
     * @return the string
     * @throws IOException 
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    @RequestMapping(value = ADMINHOME)
    public String adminHomePage(Principal principal,HttpSession session, HttpServletRequest request,Model model) throws IOException, ServiceException, ServiceRuntimeException {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) a.getPrincipal();

        Users user = userService.getUser(userDetails.getUsername());

        session.setAttribute("user", user);
        logger.info("IN ADMINCONTROLLER: in ADMINHOME mapping");
        List<Book> bookList=bookService.getBook();
        List<Request> returnRequestList = requestService.getRequests("PendingReturn");
        List<Request> delieveryRequestList = requestService.getRequests("PendingDelievery");
        model.addAttribute("book",bookList.size());
        model.addAttribute("returnSize",returnRequestList.size());
        model.addAttribute("delievery",delieveryRequestList.size());
        
        
        return ADMINHOME;
    }

    /**
     * Admin operations.
     * 
     * @return the string
     */
    @RequestMapping(value = ADMINOPERATIONS)
    public String adminOperations(HttpSession session) {
        logger.info("IN ADMINCONTROLLER: in ADMINOPERATIONS mapping");
        return ADMINOPERATIONS;
    }

    /**
     * Adds the book.
     * 
     * @param book
     *            the book
     * @param result
     *            the result
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @return the string
     * @throws ServiceRuntimeException 
     * @throws ServiceException 
     * @throws  
     */
    @RequestMapping(value = ADMINADDBOOK)
    public String addBook(@ModelAttribute("book") Book book,
            BindingResult result, Model model, Principal principal,
            HttpSession session) throws ServiceException, ServiceRuntimeException {
        logger.info("IN ADMINCONTROLLER: in ADMINADDBOOK mapping");
        
        List<Language> languageList = languageService.getLanguages();
        List<BookCategory> categoryList = bookCategoryServie.getBookCategory();
        model.addAttribute("languageList", languageList);
        model.addAttribute("categoryList", categoryList);
        logger.info("IN ADMINCONTROLLER: out ADMINADDBOOK mapping");
        return ADMINADDBOOK;
    }

    /**
     * Adds the book.
     * 
     * @param book
     *            the book
     * @param language
     *            the language
     * @param category
     *            the category
     * @param result
     *            the result
     * @param model
     *            the model
     * @param principal
     *            the principal
     * @param session
     *            the session
     * @param request
     *            the request
     * @param file
     *            the file
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceRuntimeException 
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINADDBOOK, method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, Language language,
            BookCategory category, BindingResult result, Model model,
            HttpServletRequest request,HttpSession session,
            @RequestParam("image") MultipartFile file) throws IOException, ServiceRuntimeException, ServiceException,ServiceRuntimeException {
        logger.info("IN ADMINCONTROLLER: in ADMINADDBOOK post mapping");
        if(result.hasErrors())
        {
            logger.info("Error Occured");
            
        }
        List<Language> languageList = languageService.getLanguages();
        List<BookCategory> categoryList = bookCategoryServie.getBookCategory();
        model.addAttribute("languageList", languageList);
        model.addAttribute("categoryList", categoryList);
        String fileName = book.getTitle() + "."
                + FilenameUtils.getExtension(file.getOriginalFilename());
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        bookService.addBookData(book, language, category, fileName);
        bookService.fileCopy(file, path, fileName);
        model.addAttribute("addBookMessage", "Book Added Successfully");
        logger.info("IN ADMINCONTROLLER: out ADMINADDBOOK post mapping");
        return ADMINADDBOOK; 
    }

    /**
     * Admin view book.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @return the string
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    @RequestMapping(value = ADMINVIEWBOOK)
    public String adminViewBook(Model model, HttpServletRequest request,HttpSession session) throws ServiceRuntimeException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINVIEWBOOK mapping");
        List<Book> bookList = bookService.getBook();
        List<Integer> shelfItemList = myShelfService.getShelfItems();
        List<Integer> requestBookIdList = requestService.getRequests();
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        path += "\\resources\\images\\";
        model.addAttribute("imagePath", path);
        model.addAttribute("bookList", bookList);
        model.addAttribute("requestedBookIdList", requestBookIdList);
        model.addAttribute("shelfItemList", shelfItemList);
        logger.info("IN ADMINCONTROLLER: in ADMINVIEWBOOK mapping");
        return ADMINVIEWBOOK;
    }

    /**
     * Gets the POST subscription page.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param subscriptionXMLFile
     *            the subscription xml file
     * @return the POST subscription page
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws SAXException
     *             the SAX exception
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINSUBSCRIPTION, method = RequestMethod.POST)
    public String getPOSTSubscriptionPage(Model model,
            HttpServletRequest request,HttpSession session,
            @RequestParam("subscriptionFile") MultipartFile subscriptionXMLFile)
            throws IOException, ParserConfigurationException, SAXException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINSUBSCRIPTION mapping");
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        subscriptionService.addSubscription(path, subscriptionXMLFile);
        logger.info("IN ADMINCONTROLLER: out ADMINSUBSCRIPTION mapping");
        return "redirect:viewSubscription";
    }

    /**
     * Update book admin page.
     * 
     * @param model
     *            the model
     * @param bookId
     *            the book id
     * @return the string
     * @throws ServiceRuntimeException 
     * @throws ServiceException 
     */
    @RequestMapping(ADMINUPDATE)
    public String updateBookAdminPage(Model model,HttpSession session,
            @RequestParam("bookId") String bookId) throws ServiceException, ServiceRuntimeException {
        logger.info("IN ADMINCONTROLLER: in ADMINUPDATE mapping");
        List<Language> languageList = languageService.getLanguages();
        List<BookCategory> categoryList = bookCategoryServie.getBookCategory();
        model.addAttribute("languageList", languageList);
        model.addAttribute("categoryList", categoryList);

        Book book = bookService.getBook(Integer.parseInt(bookId));
        model.addAttribute("book", book);
        logger.info("IN ADMINCONTROLLER: out ADMINUPDATE mapping");
        return ADMINUPDATE;
    }

    /**
     * Update book admin post page.
     * 
     * @param book
     *            the book
     * @param language
     *            the language
     * @param category
     *            the category
     * @param result
     *            the result
     * @param model
     *            the model
     * @param request
     *            the request
     * @param imageFile
     *            the image file
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    @RequestMapping(value = ADMINUPDATE, method = RequestMethod.POST)
    public String updateBookAdminPOSTPage(@ModelAttribute("book") Book book,
            Language language, BookCategory category, BindingResult result,HttpSession session,
            Model model, HttpServletRequest request,
            @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException, ServiceRuntimeException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINUPDATE post mapping");
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        Book tempBook = bookService.getBook(book.getBookId());
        bookService.updateAdminBookProfile(book, category, language,
                tempBook.getImageName());
        bookService.fileCopy(imageFile, path, tempBook.getImageName());
        logger.info("IN ADMINCONTROLLER: out ADMINUPDATE post mapping");
        return "redirect:viewBook";
    }

    /**
     * View subscription admin page.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @return the string
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINVIEWSUBSCRIPTION)
    public String viewSubscriptionAdminPage(Model model,HttpSession session,
            HttpServletRequest request) throws ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINVIEWSUBSCRIPTION mapping");
        List<Subscription> subscriptionList = subscriptionService
                .getSubscriptionPlans();
        model.addAttribute("subscriptionList", subscriptionList);
        model.addAttribute("updateSubscriptionStatus", "");
        List<Integer> userSuscribedIdList = userService
                .getListOfSubscribedUser();
        model.addAttribute("userSuscribedIdList", userSuscribedIdList);
        logger.info("IN ADMINCONTROLLER: out ADMINVIEWSUBSCRIPTION mapping");
        return ADMINVIEWSUBSCRIPTION;
    }

    /**
     * Update post subscription.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param subscriptionXMLFile
     *            the subscription xml file
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws SAXException
     *             the SAX exception
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINVIEWSUBSCRIPTION, method = RequestMethod.POST)
    public String updatePOSTSubscription(Model model,
            HttpServletRequest request,HttpSession session,
            @RequestParam("subscriptionFile") MultipartFile subscriptionXMLFile)
            throws IOException, ParserConfigurationException, SAXException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINVIEWSUBSCRIPTION post mapping");
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        int status = subscriptionService.updateSubscription(path,subscriptionXMLFile);
        model.addAttribute("updateSubscriptionStatus", status
                + " Subscription Updated");

        List<Subscription> subscriptionList = subscriptionService
                .getSubscriptionPlans();
        model.addAttribute("subscriptionList", subscriptionList);

        List<Integer> userSuscribedIdList = userService
                .getListOfSubscribedUser();
        model.addAttribute("userSuscribedIdList", userSuscribedIdList);
        logger.info("IN ADMINCONTROLLER: out ADMINVIEWSUBSCRIPTION post mapping");
        return ADMINVIEWSUBSCRIPTION;
    }

    /**
     * Delete subscription by admin.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param id
     *            the id
     * @return the string
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINDELETESUBSCRIPTION)
    public String deleteSubscriptionByAdmin(Model model,HttpSession session,
            HttpServletRequest request, @RequestParam("id") String id) throws ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINDELETESUBSCRIPTION mapping");
        Integer subscriptionId = Integer.parseInt(id);
        subscriptionService.deleteSubscription(subscriptionId);
        logger.info("IN ADMINCONTROLLER: out ADMINDELETESUBSCRIPTION mapping");
        return "redirect:viewSubscription";
    }

    /**
     * Delete book by admin.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param bookId
     *            the book id
     * @return the string
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     * @throws NumberFormatException 
     */
    @RequestMapping(value = ADMINDELETEBOOK)
    public String deleteBookBYAdmin(Model model, HttpServletRequest request,HttpSession session,
            @RequestParam("bookId") String bookId) throws NumberFormatException, ServiceRuntimeException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINDELETEBOOK mapping");
        bookService.deleteBook(Integer.parseInt(bookId));
        logger.info("IN ADMINCONTROLLER: out ADMINDELETEBOOK mapping");
        return "redirect:viewBook";
    }

    /**
     * Generate pdf report.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @return the string
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     * @throws  
     */
    @RequestMapping(value = ADMINREPORT)
    public String generatePDFReport(Model model, HttpServletRequest request,HttpSession session) throws ServiceRuntimeException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINREPORT mapping");
        List<Book> bookAuthorList = bookService.getBookAuthor();
        List<BookCategory> categoryList = bookCategoryServie.getBookCategory();

        model.addAttribute("bookAuthorList", bookAuthorList);
        model.addAttribute("categoryList", categoryList);
        logger.info("IN ADMINCONTROLLER: out ADMINREPORT mapping");
        return ADMINREPORT;
    }

    /**
     * Gets the generated pdf report.
     * 
     * @param history
     *            the history
     * @param request
     *            the request
     * @param session
     *            the session
     * @param response
     *            the response
     * @param principal
     *            the principal
     * @param model
     *            the model
     * @return the generated pdf report
     * @throws ParseException
     *             the parse exception
     * @throws HibernateException
     *             the hibernate exception
     * @throws DocumentException
     *             the document exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     * @throws  
     */
    @RequestMapping(value = ADMINREPORT, method = RequestMethod.POST)
    public String getGeneratedPDFReport(
            @ModelAttribute("history") History history,HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response, Principal principal, Model model)
            throws ParseException, HibernateException, DocumentException,
            IOException, ServiceRuntimeException, ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINREPORT post mapping");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date from = formatter.parse((String) request.getParameter("from"));
        Date to = formatter.parse((String) request.getParameter("to"));
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        if (request.getParameter("filter") != null) {
            logger.info("IN ADMINCONTROLLER: filter is not null");
            List<History> historyList = historyService.getHistory(from, to,
                    author, category);
            List<Book> bookList = new ArrayList<Book>();
            List<Users> userList = new ArrayList<Users>();
            for (History h : historyList) {
                bookList.add(bookService.getBook(h.getBookId()));
                userList.add(userService.getUser(h.getUserId()));
            }
            List<Book> bookAuthorList = bookService.getBookAuthor();
            List<BookCategory> categoryList = bookCategoryServie
                    .getBookCategory();

            model.addAttribute("bookAuthorList", bookAuthorList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("bookList", bookList);
            model.addAttribute("historyList", historyList);
            model.addAttribute("userList", userList);
            return "admin/report";
        } else if (request.getParameter("generate") != null) {
            logger.info("IN ADMINCONTROLLER: generate value is not null");
            pdfService.generatePdfFileInDateRange(from, to, author, category,
                    response.getOutputStream());
            response.setContentType("application/pdf");

            return "redirect:report";
        }
        logger.info("IN ADMINCONTROLLER: out ADMINDREPORT post mapping");
        return "errorpage?pageNotFound";
    }

    /**
     * Gets the active users.
     * 
     * @param language
     *            the language
     * @param category
     *            the category
     * @param result
     *            the result
     * @param model
     *            the model
     * @param request
     *            the request
     * @return the active users
     * @throws ServiceException 
     */
    @RequestMapping(value = ADMINACTIVEUSERS, method = RequestMethod.GET)
    public String getActiveUsers(Language language, BookCategory category,HttpSession session,
            BindingResult result, Model model, HttpServletRequest request) throws ServiceException {
        logger.info("IN ADMINCONTROLLER: in ADMINACTIVEUSERS mapping");
        List<Users> userList = userService.getActiveUsers();
        model.addAttribute("userList", userList);
        logger.info("IN ADMINCONTROLLER: out ADMINACTIVEUSERS mapping");
        return ADMINACTIVEUSERS;
    }
    
    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleAllException(ServiceException e)
    {
       logger.error("System Exception generated"+ e);
       ModelAndView mv= new ModelAndView();
       
        mv.setViewName("admin/errorpage");
        mv.addObject("errorMsg", e.getMessage());
        return mv;
    }
    @ExceptionHandler(ServiceRuntimeException.class)
    public ModelAndView handleAllRuntimeException(ServiceRuntimeException e)
    {
       logger.error("System Exception generated"+ e);
       ModelAndView mv= new ModelAndView();
       
        mv.setViewName("admin/errorpage");
        mv.addObject("errorMsg", e.getMessage());
        return mv;
    }
}
