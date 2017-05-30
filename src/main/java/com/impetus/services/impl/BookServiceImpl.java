package com.impetus.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.impetus.commons.exception.DAOException;
import com.impetus.commons.exception.DAORuntimeException;
import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.exception.ServiceRuntimeException;
import com.impetus.dao.api.BookDAO;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;
import com.impetus.services.api.BookService;

// TODO: Auto-generated Javadoc
/**
 * The Class BookServiceImpl.
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    /** The book dao. */
    @Autowired
    private BookDAO bookDAO;
    
    private static final String ERRORMSG="exception occur in bookService";
    
     
    /** The Constant MAXFILESIZE. */
    private static final Integer MAXFILESIZE = 10000000;
    
    /**
     * Sets the book dao.
     * 
     * @param bookDAO
     *            the new book dao
     */
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.BookService#addBookData(com.impetus.domain.Book,
     * com.impetus.domain.Language, com.impetus.domain.BookCategory,
     * java.lang.String)
     */
    public Book addBookData(Book book, Language language,
            BookCategory category, String fileName) {
        try{
        return bookDAO.addBookData(book, language, category, fileName);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Insert Operation failed!ISBN already exist in database.",e);
        }
        catch(DAOException e){
           throw new ServiceException(ERRORMSG+" adding book data",e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.BookService#fileCopy(org.springframework.web
     * .multipart.MultipartFile, java.lang.String, java.lang.String)
     */
    public void fileCopy(MultipartFile file, String path, String fileName) {
        try{
        File imagedirectory = new File(path + "\\resources\\images");
        if (!imagedirectory.exists()) {
            imagedirectory.mkdir();
        }
        InputStream fin = file.getInputStream();
        OutputStream fout = new FileOutputStream(new File(path
                + "\\resources\\images\\" + fileName));
        byte buffer[] = new byte[MAXFILESIZE];
        int len;
        while ((len = fin.read(buffer, 0, MAXFILESIZE)) != -1) {
            fout.write(buffer, 0, len);
        }
        fout.close();
        fin.close();
        }catch(Exception e){
            throw new ServiceException(ERRORMSG+" copying the file",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookService#getBook()
     */
    public List<Book> getBook(){
        try{
            return bookDAO.getBook();
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Error in fetching Books List",e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Error in fetching Books List",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookService#getBook(java.lang.Integer)
     */
    public Book getBook(Integer bookId){
        try{
            return bookDAO.getBook(bookId);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Error in fetching book with bookId="+bookId,e);
        }
        catch(DAOException e){
            throw new ServiceException("Error in fetching book with bookId="+bookId,e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.BookService#updateBook(com.impetus.domain.Book)
     */
    public Book updateBook(Book book){
        try{
            return bookDAO.updateBook(book);
        }catch(DAOException e)
        {
            throw new ServiceException("Error occured in updating book",e);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Error occured in updating book",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.BookService#updateAdminBookProfile(com.impetus
     * .domain.Book, com.impetus.domain.BookCategory,
     * com.impetus.domain.Language, java.lang.String)
     */
    public Book updateAdminBookProfile(Book book, BookCategory category,
            Language language, String fileName){
        try{
        Book tempBook = getBook(book.getBookId());
        book.setImageName(fileName);
        book.setBookLanguage(language);
        book.setCategory(category);
        book.setAvailability(book.getQuantity() + tempBook.getAvailability());
        book.setQuantity(book.getQuantity() + tempBook.getQuantity());
        book.setImageName(tempBook.getImageName());
        book.setISBN(tempBook.getISBN());

        return updateBook(book);
        }
        catch(Exception e)
        {
            throw new ServiceException(ERRORMSG+" updateAdminBookProfile()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookService#deleteBook(java.lang.Integer)
     */
    public Book deleteBook(Integer bookId) {
        try{
            return bookDAO.deleteBook(bookId);
        }catch(DAOException e){
            throw new ServiceException("Error occured in deleting book,can't delete",e);
        }catch(DAORuntimeException e){
            throw new ServiceException("Error occured in deleting book,can't delete",e);            
            
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookService#getBook(java.lang.String)
     */
    public List<Book> getBook(String searchQuery){
        try{
            return bookDAO.getBook(searchQuery);
        }catch(DAOException e){
            throw new ServiceException("Exception occured during Searching book with query="+searchQuery,e);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Exception occured during Searching book with query="+searchQuery,e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.BookService#getBookAuthor()
     */
    public List<Book> getBookAuthor() {
        try{
            return bookDAO.getBookAuthor();
        }catch(DAOException e){
            throw new ServiceException(ERRORMSG+" getBookAuthor()",e);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException("Can not perform operation,Exception occured in fetching distinct Authors.",e);
        }
    }
    
    public List<Book> recentlyAddedBooks() {
        try{
            return bookDAO.recentlyAddedBooks();
        }catch(DAOException e){
            throw new ServiceException(ERRORMSG+" recentlyAddedBooks()",e);
        }catch(DAORuntimeException e){
            throw new ServiceRuntimeException(ERRORMSG+" recentlyAddedBooks()",e);
        }
        
    }

}
