package com.impetus.dao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.impetus.commons.exception.DAOException;
// TODO: Auto-generated Javadoc
/**
 * The Interface PdfDAO.
 */
public interface PdfDAO {

    /**
     * Generate pdf file in date range.
     * 
     * @param from
     *            the from
     * @param to
     *            the to
     * @param author
     *            the author
     * @param category
     *            the category
     * @return the map< string, list<? extends object>>
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws HibernateException
     *             the hibernate exception
     * @throws DocumentException
     *             the document exception
     * @throws ServiceException 
     * @throws DAOException 
     */
    Map<String, List<? extends Object>> generatePdfFileInDateRange(Date from,
            Date to, String author, String category)throws DAOException;
}
