/*

Yavor Nanev,           11060247
 */
package jjb;

import beans.booksbean;
import beans.booksbeanLocal;
import ente.Book;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rock n Roll
 */
public class PstArg {
    booksbeanLocal booksbean = lookupbooksbeanLocal();
    
    
    public static String isbn="";
    public static Book book;
    
    /**
     *
     * @param s
     * @return
     */
    public static String escapeString(String s)
    {
       if(s.contains("<"))
       {
           s = s.replaceAll("<", "&lt");
           s = s.replaceAll(">", "&gt");
       }
       return s;
    }
    
    
    public static void setBook(Book book) {
  
        PstArg.book = book;
    }

    public static Book getBook() {
        return book;
    }

    public static void setIsbn(String isbn) {
        PstArg.isbn = isbn;
    }

    public static String getIsbn() {
        return isbn;
    }

    private booksbeanLocal lookupbooksbeanLocal() {
        try {
            Context c = new InitialContext();
            return (booksbeanLocal) c.lookup("java:global/newtwo/newtwo-ejb/booksbean!beans.booksbeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
