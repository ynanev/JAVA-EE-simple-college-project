/*
 * 
Yavor Nanev,           11060247
 */
package app;

import beans.bookcommentsLocal;
import beans.booksbeanLocal;
import ente.Book;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jjb.MyCheck;
import jjb.PstArg;

/**
 *
 * @author Rock n Roll
 */
public class GetByIsbn extends HttpServlet {
    @EJB
    private booksbeanLocal booksbean;
    @EJB
    private bookcommentsLocal bookcomments;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

         String isbn = request.getParameter("isbn");
        
      try {
             int a=Integer.parseInt(isbn);
          }
        catch (java.lang.NumberFormatException e) {
             
             String error="Book not founddddddddd!";
              request.setAttribute("error",error);
             this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response); 
        }
          
        
        
         Book book=booksbean.getInfo(isbn);
     
         if(book != null)
         {
             PstArg.setIsbn(isbn);
             PstArg.setBook(book);

             this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);  
         }
         else
         {
             String error="Book not there!";
             request.setAttribute("error",error);
             this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
         
         }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       String n =  request.getParameter("my-nonce");
       boolean b=true;

       String isbn = request.getParameter("isbn");
        
         try 
          {
               int a=Integer.parseInt(isbn);
          }
        catch (java.lang.NumberFormatException e) 
        {
            String error="Book not founddddddddd!";
            request.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response); 
        }
          
         try 
         {
             b=MyCheck.validateSBT(Integer.parseInt(n));
         }
        catch (Exception e)
        {           
            b=false;
        }
  if(b=true)
   {
         Book book=booksbean.getInfo(isbn);
         
                if(book != null)
                {
                    PstArg.setIsbn(isbn);
                    PstArg.setBook(book);

                    this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);  
                }
                else
                {
                    String error="Book not there!";
                    request.setAttribute("error",error);
                    this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
                }

     }
        
  }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
