/*
 
Yavor Nanev,           11060247
 */
package beans;

import ente.BookComments;
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
public class bookcomments implements bookcommentsLocal {
    @PersistenceContext(unitName = "newtwo-ejbPU")
    private EntityManager em;

    
    
    @Override
    public List<BookComments> getBookComments(int bookisbn) {
        Query q=em.createNamedQuery("BookComments.findByBookIsbn");
        q.setParameter("bookIsbn", bookisbn);
        List<BookComments> comList=q.getResultList();
        
        return comList;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void insetComent(String isbn, String text, String user) {
     BookComments bc=new BookComments();
     bc.setBookIsbn(Integer.parseInt(isbn));
     bc.setComment(text);
     bc.setUsername(user);
     
     int id = (Integer) em.createNamedQuery("BookComments.getHighestCustomerID").getSingleResult();
        // id is current highest, increment to next id
        id++;
     bc.setId(id);
     em.persist(bc);
     
     
    }

    @Override
    public void removeAllBookCooments(int isbn) {
       List<BookComments> comList=this.getBookComments(isbn);
     if(comList.size()!=0)
     {
       for(int i=0;i<comList.size();i++)
       {
           em.remove(comList.get(i));
       }
     }
    }

  
}
