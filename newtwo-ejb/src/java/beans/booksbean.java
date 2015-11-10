/*

Yavor Nanev,           11060247
 */
package beans;

import ente.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rock n Roll
 */
@Stateless
public class booksbean implements booksbeanLocal {
    @PersistenceContext(unitName = "newtwo-ejbPU")
    private EntityManager emm;
 

    
   @Override
   public List<Book> books() 
   {
   Query qu=emm.createNamedQuery("Book.findAll");
         // qu.setParameter("isbn", 29);
            //Book b= (Book) qu.getSingleResult();
        List<Book> b=qu.getResultList();
        return b;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        emm.persist(object);
    }

    @Override
    public Book getInfo(String isbn)
    {
        int is=Integer.parseInt(isbn);
        Query qu=emm.createNamedQuery("Book.findByIsbn");
        qu.setParameter("isbn", is);
        
        List <Book> isin=qu.getResultList();
        if(!isin.isEmpty())
      {
          return isin.get(0);
      }
        else
        {  
            
          return null;
        }
    }

    @Override
    public boolean increasQ(String isbn,int amount) {
       
         Book book=this.getInfo(isbn);
         book.setAmount(book.getAmount()+amount);
         emm.persist(book);
      
         return false;
   
    }

    @Override
    public boolean decreaseQ(String isbn,int amount) {
      
        Book book=this.getInfo(isbn);
        if(book.getAmount()>0 )
        {
        book.setAmount(book.getAmount()- amount);
        emm.persist(book);
        return true;
        }
        else
        return false;
    }

    @Override
    public void returnBooks(List<Book> books) {
        
        if(books.size()>0)
        {   
            for(int i=0;i<books.size();i++)
            {   Book book=this.getInfo(""+books.get(i).getIsbn());
                book.setAmount(books.get(i).getAmount()+book.getAmount());
                emm.persist(book);
            }
        }
        
        
        
    }
    
    
    
    
    @Override
    public Book getInfoByName(String bookname) {
       
        Query qu=emm.createNamedQuery("Book.findByBookname");
        qu.setParameter("bookname", bookname);
        
        List <Book> isin=qu.getResultList();
        if(!isin.isEmpty())
      {
          return isin.get(0);
      }
        else
        {  
            
          return null;
        }
    }

    @Override
    public void addBook(int isbn, String title, int amunt) {
        
        Book book=new Book();
        
        book.setIsbn(isbn);
        book.setBookname(title);
        book.setAmount(amunt);
        
        emm.persist(book);
    
    }

    @Override
    public void removeBook(int isbn) {
        
         Book book=this.getInfo(""+isbn);
         emm.remove(book);
        
        
    }
    }

  

