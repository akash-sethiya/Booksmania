package com.impetus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.domain.Book;
import com.impetus.services.api.BookService;

// TODO: Auto-generated Javadoc
/**
 * The Class RESTController.
 */
@RestController
@RequestMapping("/")
public class RESTController {

    /** The book service. */
    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(RestController.class);
    /**
     * Provide books to service.
     * 
     * @param query
     *            the query
     * @return the list
     * @throws ServiceException 
     * @throws DAOException 
     */
    @RequestMapping(value = { "/search/{var}" })
    public List<Book> provideBooksToService(@PathVariable("var") String query) throws DAOException, ServiceException {
        logger.info("In Rest Controller provideBooksToService Method");
        if (query == null) {
            logger.info("query from url is null or empty");
            return bookService.getBook();
        }
        logger.info("Returning list of book to the client");
        return bookService.getBook(query);
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
