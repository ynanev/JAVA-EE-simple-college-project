/*

Yavor Nanev,           11060247
 */
package app;

import beans.CartBeanLocal;
import beans.bookcommentsLocal;
import beans.booksbeanLocal;
import com.sun.faces.util.HtmlUtils;
import ente.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jjb.CurUs;
import jjb.MyCheck;
import jjb.PstArg;

/**
 *
 * @author Rock n Roll
 */
public class submit extends HttpServlet {
    CartBeanLocal cartBean = lookupCartBeanLocal();
    @EJB
    private bookcommentsLocal bookcomments;
    @EJB
    private booksbeanLocal booksbean;
      MyCheck no=new MyCheck();
    


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      String n =  request.getParameter("my-nonce");
      if(MyCheck.validateBT(Integer.parseInt(n)))
      {  
      String text= request.getParameter("textarea");
      String isbn= request.getParameter("isbn");
      String s=CurUs.getName();
      bookcomments.insetComent(isbn,text,s);
     
           Book book=booksbean.getInfo(isbn);
           if(book != null)
           {
               PstArg.setBook(book);
      
                this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);
           }
      }
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       try
     {    
      String n =  request.getParameter("my-nonce");
      if(MyCheck.validateBT(Integer.parseInt(n)))
      {  
      String text= request.getParameter("textarea");
      String isbn= request.getParameter("isbn");
      String s=CurUs.getName();
      bookcomments.insetComent(isbn,text,s);
     
           Book book=booksbean.getInfo(isbn);
           if(book != null)
           {
               PstArg.setBook(book);
      
                this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);
           }
      }
       
    }
          catch (javax.ejb.NoSuchEJBException | java.lang.NullPointerException |java.lang.NumberFormatException e) {
         
            CurUs.setCurrentUserLogedIn(false);
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
         
        }  
    }
        
        
        
        
       
      
          
         
      
    
      
        
        
         
       
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CartBeanLocal lookupCartBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartBeanLocal) c.lookup("java:global/newtwo/newtwo-ejb/CartBean!beans.CartBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}