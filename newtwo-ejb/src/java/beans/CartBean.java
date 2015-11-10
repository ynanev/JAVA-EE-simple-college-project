/*
 
Yavor Nanev,           11060247
 */
package beans;
import ente.Book;
import java.util.*;
import javax.ejb.*;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateful;
/**
 *
 * @author Rock n Roll
 */
@Stateful(mappedName="cart")
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)



public class CartBean implements CartBeanLocal {
    @EJB
    private booksbeanLocal booksbean;
    

    private HashMap<String, Integer> items = new HashMap<String, Integer>();
    public String username=" ";

   
    
    @Override
    public void addItem(String id, int quantity) {
       
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
         if(booksbean.decreaseQ(id,1))
        {
        orderQuantity += quantity;
        items.put(id, orderQuantity);
        }
    }
    
    
    
    @Override
    public void removeItem(String id, int quantity) {
       
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity -= quantity;
        if (orderQuantity <= 0) {
            // final quantity less equal 0 - remove from cart
            items.remove(id);
            booksbean.increasQ(id,1);
        } else {
            // final quantity > 0 - adjust quantity
            items.put(id, orderQuantity);
            booksbean.increasQ(id,1);
            
        }
       }
      
       
    

    @Override
    @Remove
    public String checkout() {
        // dummy checkout method that just returns message for successful 
        // checkout
        String message = "You checked out the following items:\n<br>" + getItemList();
        return message;
    }

    @Override
    @Remove
    public void cancel() {
        // no action required - annotation @Remove indicates
        // that calling this method should remove the EJB which will
        // automatically destroy instance variables
    }

    @Override
    public String getItemList() {
        String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            message += k + ", quantity: " + items.get(k) + "\n<br>";
        }
        return message;
    }

    @Override
    public String getItemListLine() {
        String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            message += k + ", quantity: " + items.get(k) + "; ";
        }
        return message;
    }

    @Override
    public List<Book> cartList() {
       List<Book> books=new ArrayList<Book>() ; 
         String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        
        while (it.hasNext()) {
            Book book=new Book();
            k = it.next();
            book.setIsbn(Integer.parseInt(k));
            book.setBookname(booksbean.getInfo(k).getBookname());
            book.setAmount(items.get(k));
            books.add(book);
            message += k + ", quantity: " + items.get(k) + "\n<br>";
        }
        return books;
        
        
    }

    @Override
    public void clearList() {
       this.items.clear();
    }

    @Override
    public void setUsername(String username) {
        this.username=username;
    }

    @Override
    public String getUsername() {
        return username;
    }

}


