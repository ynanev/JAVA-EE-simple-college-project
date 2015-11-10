/*

Yavor Nanev,           11060247
 */
package beans;

import ente.Book;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rock n Roll
 */
@Local
public interface CartBeanLocal {
    
    /**
     * Adds a number of items to the shopping cart.
     * @param id - ID of the item to be added
     * @param quantity - number of items to be added
     * @return 
     */
    public List<Book> cartList();
    public String getUsername();
     public void setUsername(String username);
    public void addItem(String id, int quantity);
    public String getItemListLine();
    /**
     * Removes a number items from the shopping cart. If quantity exceeds
     * the number of present items, number is set to 0.
     * @param id - ID of the item to be removed
     * @param quantity - number of items to be removed. 
     */
    public void removeItem(String id, int quantity);
    /**
     * Proceed with the checkout by asking for billing information. Checkout 
     * will terminate the current session for the EJB.
     * @return Message indicating result of checkout.
     */
    public void clearList();
    public String checkout();
    /**
     * Cancels the current ordering process. Cancel will terminate the current 
     * session for the EJB.
     */
    public void cancel();
    
    /**
     * Returns a string representing content of shopping cart
     * @return 
     */
    public String getItemList();
}

    
    

