/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import ente.Book;
import ente.Customer;
import ente.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 
Yavor Nanev,           11060247
 */
@Stateless
public class bean implements beanLocal {
    @PersistenceContext(unitName = "newtwo-ejbPU")
    private EntityManager em;
    
   

    
    
    
    @Override
    public boolean isIn(String username,String password) 
    {   
   
        Query q= em.createNamedQuery("Users.findByUsernameAndPass");
              q.setParameter("username", username);
              q.setParameter("password", password);
              
              List <Users> isin=q.getResultList();
      
      if(!isin.isEmpty())
      {
          return true;
      }
        else
        {  
            
          return false;
        }
   
    }
    @Override
    public Users user(String username) {
        Query q= em.createNamedQuery("Users.findByUsername");
              q.setParameter("username", username);
              
              Users user=(Users) q.getSingleResult();
           
              
            //  List <Users> isin=q.getResultList();
      
      if(user !=null)
      {
          return user ;
      }
        else
        {  
            Users u=new Users();
            
          return u;
        }
   
    }
   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Book> gbook() {
   //  Query qu=em.createNamedQuery("Book.findByIsbn");
        //  qu.setParameter("isbn", 29);
          //  Book b= (Book) qu.getSingleResult();
     //   List<Book> b=qu.getResultList();
        return null;
    
    
    
    }
    
    

   

   
}
