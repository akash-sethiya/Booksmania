package com.impetus.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.PdfDAO;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.impetus.services.api.BookService;
import com.impetus.services.api.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class PdfDAOImpl.
 */
@Repository("pdfDAO")
public class PdfDAOImpl implements PdfDAO {

    /** The book service. */
    @Autowired
    private BookService bookService;

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    private Logger logger = LoggerFactory.getLogger(PdfDAOImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.PdfDAO#generatePdfFileInDateRange(java.util.Date,
     * java.util.Date, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<? extends Object>> generatePdfFileInDateRange(
            Date from, Date to, String author, String category)
            throws DAOException {
        try{
        logger.info("in generatePDFfileIn DAte range method");
        String authorQuery, categoryQuery;

        java.sql.Date fromDate = new java.sql.Date(from.getTime());
        java.sql.Date toDate = new java.sql.Date(to.getTime());

        if (!author.equalsIgnoreCase("all")) {
            logger.info("author is specific");
            authorQuery = "SELECT bookId FROM Book WHERE author='" + author
                    + "'";
        } else {
            logger.info("author is all");
            authorQuery = "SELECT bookId from Book";
        }
        if (!category.equalsIgnoreCase("all")) {
            logger.info("category is specific");
            categoryQuery = "SELECT bookId FROM Book WHERE category_categoryId="
                    + Integer.parseInt(category);
        } else {
            logger.info("category is all");
            categoryQuery = "SELECT bookId FROM Book";
        }
        String historyQuery = "from History WHERE issuedDate BETWEEN '"
                + fromDate + "' AND '" + toDate + "' ";

        List<History> historyList = (List<History>) hibernateTemplate
                .find(historyQuery + "AND bookId IN (" + authorQuery
                        + ") AND bookId IN ( " + categoryQuery + " )");
        List<Users> userList = new ArrayList<Users>();
        List<Book> bookList = new ArrayList<Book>();
        for (History obj : historyList) {
            bookList.add(bookService.getBook(obj.getBookId()));
            userList.add(userService.getUser(obj.getUserId()));
        }
        Map<String, List<? extends Object>> historyMap = new HashMap<String, List<? extends Object>>();

        historyMap.put("user", userList);
        historyMap.put("book", bookList);
        historyMap.put("history", historyList);
        return historyMap;
        }
        catch(Exception e)
        {
            logger.error("exception occured in generatePDfFile in date ranfge function::::"+e);
            throw new DAOException("exception occured in generatePDF DAO:",e);
        }

    }
}
