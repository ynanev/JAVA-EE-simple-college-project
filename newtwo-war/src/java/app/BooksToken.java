/*

Yavor Nanev,           11060247
 */
package app;

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
public class BooksToken extends HttpServlet {
    @EJB
    private booksbeanLocal booksbean;

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
        
      
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String n =  request.getParameter("my-nonce");
      if(MyCheck.validateBT(Integer.parseInt(n)))
      {
        this.getServletContext().getRequestDispatcher("/allbooks.jsp").forward(request, response);
     }
     else
      {
          
          try (PrintWriter out = response.getWriter()) 
          {
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error"+MyCheck.validateBT(Integer.parseInt(n))+"n"+"n is:"+n+"token is:"+MyCheck.getBToken()+"</h1>");
            out.println("</body>");
            out.println("</html>");
           }
             
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

}
