package com.impetus.services.impl;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.pdf.Pdf;
import com.impetus.dao.api.PdfDAO;
import com.impetus.services.api.PdfService;

// TODO: Auto-generated Javadoc
/**
 * The Class PdfServiceImpl.
 */
@Service("pdfService")
public class PdfServiceImpl implements PdfService {

    /** The pdf dao. */
    @Autowired
    private PdfDAO pdfDAO;

    /** The pdf. */
    private Pdf pdf = new Pdf();

    public void setPdfDAO(PdfDAO pdfDAO) {
        this.pdfDAO = pdfDAO;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.PdfService#generatePdfFileInDateRange(java.util
     * .Date, java.util.Date, java.lang.String, java.lang.String,
     * java.io.OutputStream)
     */
    public boolean generatePdfFileInDateRange(Date from, Date to,
            String author, String category, OutputStream outputStream){
        try{
        Map<String, List<? extends Object>> map = pdfDAO
                .generatePdfFileInDateRange(from, to, author, category);
        return pdf.generate(map, outputStream);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception in service generatePDFinDATErange method",e);
        }
    }

}
