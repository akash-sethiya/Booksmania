package com.impetus.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.DAORuntimeException;
import com.impetus.dao.api.BookDAO;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;
import com.impetus.services.api.BookCategoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class BookDAOImpl.
 */
@Repository("bookDAO")
@SuppressWarnings("unchecked")
public class BookDAOImpl implements BookDAO {

    private static final int LIMIT = 6;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /** The book category service. */
    @Autowired
    private BookCategoryService bookCategoryService;

    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#addBookData(com.impetus.domain.Book,
     * com.impetus.domain.Language, com.impetus.domain.BookCategory,
     * java.lang.String)
     */
    public Book addBookData(Book book, Language language,
            BookCategory category, String fileName) throws DAOException {
        try{
        logger.info("DAO: Adding New Book Into Database");
        book.setImageName(fileName);
        book.setBookLanguage(language);
        book.setCategory(category);
        book.setAvailability(book.getQuantity());
        hibernateTemplate.save(book);
        logger.info("DAO: Book with id=" + book.getBookId()
                + " added Successfully");
        return book;
        }
        catch(DataAccessException e)
        {
            logger.error("DATA ACCESS error occured in adding book to database::::"+e);
            throw new DAORuntimeException("DATA ACCESS Error occured in adding book to database",e);
        }
        
        catch(Exception e)
        {
            logger.error("error occured in adding book to database::::"+e);
            throw new DAOException("Error occured in adding book to database",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#getBook()
     */
    
    public List<Book> getBook() throws DAOException {
        try{
        logger.info("DAO: Fetching Book List");
        List<Book> bookList = (List<Book>) hibernateTemplate.find("from Book");
        logger.info("DAO: " + bookList.size() + " Book fetched succesfully");
        return bookList;
        }
        catch(DataAccessException e)
        {
            logger.error("DATA ACCESS error occured in fetching list of book::::"+e);
            throw new DAORuntimeException("DATA ACCESS error occured in fetching list of book",e);
        }
        catch(Exception e)
        {
            logger.error("error occured in fetching list of book::::"+e);
            throw new DAOException("error occured in fetching list of book",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#getBook(java.lang.Integer)
     */
    public Book getBook(Integer bookId) throws DAOException {
        try {
            logger.info("DAO: fetching book with specific ID = " + bookId);
            Object object = hibernateTemplate.get(Book.class, bookId);
            Book book = (Book) object;
            logger.info("DAO: book fetched");
            return book;
        }
        catch(DataAccessException e)
        {
            logger.error("DATA ACCESS error occured in fetching book with specific ID::::"+e);
            throw new DAORuntimeException("DATA ACCESS error occured in fetching book with specific ID", e);
        }
        catch(Exception e)
        {
            logger.error("error occured in fetching book with specific ID::::"+e);
            throw new DAOException("error occured in fetching book with specific ID", e);
        }
        finally {
            hibernateTemplate.clear();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#updateBook(com.impetus.domain.Book)
     */
    public Book updateBook(Book book) throws DAOException {
        try{
        logger.info("DAO: Update Request for Book with id=" + book.getBookId());
        hibernateTemplate.update(book);
        logger.info("DAO: Book Updated Successfully");
        return book;
        }
        catch(DataAccessException e)
        {
            logger.error("DATA ACCESS error occured in updating book::::"+e);
            throw new DAORuntimeException("DATA ACCESS error occured in updating book",e);
        }
        catch(Exception e)
        {
            logger.error("error occured in updating book::::"+e);
            throw new DAOException("error occured in updating book",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#deleteBook(java.lang.Integer)
     */
    public Book deleteBook(Integer bookId) throws DAOException {
        try{
        logger.info("Delete request for book with id=" + bookId);
        Book book = (Book) hibernateTemplate.get(Book.class, bookId);
        hibernateTemplate.delete(book);
        logger.info("Book deleted Successfully");
        return book;
        }
        catch(DataAccessException e){
            logger.error("DATA ACCESS error in deleting book::::"+e);
            throw new DAORuntimeException("DATA ACCESS error in deleting book",e);
        }
        catch(Exception e){
            logger.error("error in deleting book::::"+e);
            throw new DAOException("error in deleting book",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#getBook(java.lang.String)
     */
    
    public List<Book> getBook(String searchQuery) throws DAOException {
        try{
        logger.info("DAO: Request for searching book with QUERY=\""
                + searchQuery + "\"");
        BookCategory bookCategory = bookCategoryService
                .getBookCategory(searchQuery);
        List<Book> bookList;

        DetachedCriteria dCriteria = DetachedCriteria.forClass(Book.class);
        Criterion titleRestriction = Restrictions.like("title", searchQuery,
                MatchMode.ANYWHERE).ignoreCase();
        Criterion authorRestriction = Restrictions.like("author", searchQuery,
                MatchMode.ANYWHERE).ignoreCase();
        Criterion publisherRestriction = Restrictions.like("publisher",
                searchQuery, MatchMode.ANYWHERE).ignoreCase();
        Criterion isbnRestrction = Restrictions.eq("iSBN", searchQuery);
        Criterion result1 = Restrictions
                .or(titleRestriction, authorRestriction);
        Criterion result2 = Restrictions.or(
                Restrictions.or(result1, publisherRestriction), isbnRestrction);

        if (bookCategory != null) {
            Criterion categoryRestriction = Restrictions.eq("category",
                    bookCategory);
            Criterion result3 = Restrictions.or(result2, categoryRestriction);
            dCriteria.add(result3);
            bookList = (List<Book>) hibernateTemplate.findByCriteria(dCriteria);
        } else {
            dCriteria.add(result2);
            bookList = (List<Book>) hibernateTemplate.findByCriteria(dCriteria);
        }
        logger.info("DAO: " + bookList.size() + " Book Fetched Successfully");
        return bookList;
        }
        catch(DataAccessException e){
            logger.error("DATA ACCESS error occured in fetching books by filter::::"+e);
            throw new DAORuntimeException("DATA ACCESS error occured in fetching books by filter",e);
        }
        catch(Exception e)
        {
            logger.error("error occured in fetching books by filter::::"+e);
            throw new DAOException("error occured in fetching books by filter",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.BookDAO#getBookAuthor()
     */
    
    public List<Book> getBookAuthor() throws DAOException {
        try{
        logger.info("DAO: Fetching Distinct Author.....");
        List<Book> bookAuthorList = (List<Book>) hibernateTemplate
                .find("SELECT DISTINCT author FROM Book");
        logger.info("DAO: DISTINCT Author Fetched succesfully");
        return bookAuthorList;
        }
        catch(DataAccessException e){
            String errorMSG="DATA ACCESS error occured in fetching deistinct author";
            logger.error(errorMSG+"::::"+e);
            throw new DAORuntimeException(errorMSG,e);
        }
        catch(Exception e){
            String errorMSG="error occured in fetching deistinct author";
            logger.error(errorMSG+"::::"+e);
            throw new DAOException(errorMSG,e);
        }
    }
    
    
    public List<Book> recentlyAddedBooks() throws DAOException {
        try{
        logger.info("DAO: fetching recently Added Books");
        DetachedCriteria dCriteria=DetachedCriteria.forClass(Book.class);
        dCriteria.addOrder(Order.desc("bookId"));
        return (List<Book>)hibernateTemplate.findByCriteria(dCriteria, 0, LIMIT);
        }
        catch(DataAccessException e){
            logger.error("DATA ACCESS error in fetching recenlty added books:::"+e);
            throw new DAORuntimeException("DATA ACCESS error in fetching recently added books",e);
        }
        catch(Exception e)
        {
            logger.error("error in fetching recenlty added books:::"+e);
            throw new DAOException("error in fetching recently added books",e);
        }
    }

}
