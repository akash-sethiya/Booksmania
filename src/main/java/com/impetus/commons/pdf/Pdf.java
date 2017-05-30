package com.impetus.commons.pdf;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Users;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class Pdf.
 */
@Service("pdf")
public class Pdf {

    private static final int COLUMN_COUNT=7;
    /**
     * Generate.
     * 
     * @param map
     *            the map
     * @param outputStream
     *            the output stream
     * @return true, if successful
     * @throws DocumentException
     *             the document exception
     */
    @SuppressWarnings("unchecked")
    public boolean generate(Map<String, List<? extends Object>> map,
            OutputStream outputStream) throws DocumentException {
        List<Book> bookList = (List<Book>) map.get("book");
        List<Users> userList = (List<Users>) map.get("user");
        List<History> historyList = (List<History>) map.get("history");
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        
        PdfPTable table = new PdfPTable(COLUMN_COUNT);
        table.addCell("S.No");
        table.addCell("Title");
        table.addCell("Author");
        table.addCell("Category");
        table.addCell("Issued Date");
        table.addCell("Return Date");
        table.addCell("User");
        int i = 0;
        for (History obj : historyList) {
            Book book = bookList.get(i);
            Users user = userList.get(i);
            if (book != null) {
                table.addCell(Integer.toString(++i));
                table.addCell(book.getTitle());
                table.addCell(book.getAuthor());
                table.addCell(book.getCategory().getCategory());
                table.addCell(obj.getIssuedDate().toString());
                if(obj.getReturnDate()!=null){
                    table.addCell(obj.getReturnDate().toString());
                }
                else{
                    table.addCell("Book Request Cancelled");
                }
                table.addCell(user.getUserDetails().getName());
            }
        }
        document.add(table);
        document.close();
        return true;
    }

}
