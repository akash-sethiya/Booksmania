package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookDAO.
 */
public interface BookDAO {

    /**
     * Adds the book data.
     * 
     * @param book
     *            the book
     * @param language
     *            the language
     * @param category
     *            the category
     * @param fileName
     *            the file name
     * @return the book
     * @throws DAOException 
     */
    Book addBookData(Book book, Language language, BookCategory category,
            String fileName)throws DAOException ;

    /**
     * Gets the book.
     * 
     * @return the book
     * @throws DAOException 
     */
    List<Book> getBook()throws DAOException ;

    /**
     * Gets the book.
     * 
     * @param bookId
     *            the book id
     * @return the book
     * @throws DAOException 
     */
    Book getBook(Integer bookId)throws DAOException ;

    /**
     * Update book.
     * 
     * @param book
     *            the book
     * @return the book
     * @throws DAOException 
     */
    Book updateBook(Book book)throws DAOException ;

    /**
     * Delete book.
     * 
     * @param bookId
     *            the book id
     * @return the book
     * @throws DAOException 
     */
    Book deleteBook(Integer bookId)throws DAOException ;

    /**
     * Gets the book.
     * 
     * @param searchQuery
     *            the search query
     * @return the book
     * @throws DAOException 
     */
    List<Book> getBook(String searchQuery)throws DAOException ;

    /**
     * Gets the book author.
     * 
     * @return the book author
     * @throws DAOException 
     */
    List<Book> getBookAuthor()throws DAOException ;

    List<Book> recentlyAddedBooks()throws DAOException ;

}
