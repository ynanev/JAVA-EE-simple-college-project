/*

Yavor Nanev,           11060247
 */
package beans;

import ente.BookComments;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rock n Roll
 */
@Local
public interface bookcommentsLocal {
    
    
    public List<BookComments> getBookComments(int bookisbn);
    public void insetComent(String isbn,String text,String user);
    public void removeAllBookCooments(int isbn);
   
}
