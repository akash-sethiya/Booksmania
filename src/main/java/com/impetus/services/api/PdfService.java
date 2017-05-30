package com.impetus.services.api;

import java.io.OutputStream;
import java.util.Date;
import org.springframework.transaction.annotation.Transactional;



// TODO: Auto-generated Javadoc
/**
 * The Interface PdfService.
 */
@Transactional(readOnly = false)
public interface PdfService {

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
     * @param outputStream
     *            the output stream
     * @return true, if successful
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws HibernateException
     *             the hibernate exception
     * @throws DocumentException
     *             the document exception
     * @throws ServiceException 
     */
    boolean generatePdfFileInDateRange(Date from, Date to, String author,
            String category, OutputStream outputStream);
}
