package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



import com.impetus.domain.Book;
import com.impetus.domain.BookCategory;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookService.
 */
@Transactional(readOnly = false)
public interface BookService {

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
     * @throws ServiceRuntimeException 
     * @throws ServiceException 
     */
    Book addBookData(Book book, Language language, BookCategory category,
            String fileName);

    /**
     * File copy.
     * 
     * @param file
     *            the file
     * @param path
     *            the path
     * @param fileName
     *            the file name
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void fileCopy(MultipartFile file, String path, String fileName);

    /**
     * Gets the book.
     * 
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Book> getBook();

    /**
     * Gets the book.
     * 
     * @param bookId
     *            the book id
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    Book getBook(Integer bookId);

    /**
     * Update book.
     * 
     * @param book
     *            the book
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    Book updateBook(Book book);

    /**
     * Update admin book profile.
     * 
     * @param book
     *            the book
     * @param category
     *            the category
     * @param language
     *            the language
     * @param fileName
     *            the file name
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    Book updateAdminBookProfile(Book book, BookCategory category,
            Language language, String fileName);

    /**
     * Delete book.
     * 
     * @param bookId
     *            the book id
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    Book deleteBook(Integer bookId);

    /**
     * Gets the book.
     * 
     * @param searchQuery
     *            the search query
     * @return the book
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Book> getBook(String searchQuery);

    /**
     * Gets the book author.
     * 
     * @return the book author
     * @throws ServiceException 
     * @throws ServiceRuntimeException 
     */
    List<Book> getBookAuthor();

    List<Book> recentlyAddedBooks();

}
