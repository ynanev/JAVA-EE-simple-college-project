/*

Yavor Nanev,           11060247
 */
package beans;

import ente.Book;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rock n Roll
 */
@Local
public interface booksbeanLocal {
    
    public List<Book> books();
    public Book getInfo(String isbn);
    public boolean increasQ(String isbn,int amount);
    public boolean decreaseQ(String isbn,int amount);
    public void returnBooks(List<Book> books);
    public Book getInfoByName(String bookname);
    public void addBook(int isbn,String title,int amunt);
    public void removeBook(int isbn);
    
}
