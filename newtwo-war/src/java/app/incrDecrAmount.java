/*

Yavor Nanev,           11060247
 */
package app;

import beans.bookcommentsLocal;
import beans.booksbeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jjb.CurUs;

/**
 *
 * @author Rock n Roll
 */
public class incrDecrAmount extends HttpServlet {
    @EJB
    private booksbeanLocal booksbean;
    

    

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session1= request.getSession(false);
        
            String isbn="";
            String amount="";
            int amountValidate=0;
            int isbnValidate=0;
        
        if(session1.getAttribute("UN").toString()!=null 
                && session1.getAttribute("UN").toString().equalsIgnoreCase(CurUs.getName()) 
                && session1.getAttribute("UT").toString().equalsIgnoreCase("admin"))
        {
            String addOrRemove="decrement";
            try
           {
            addOrRemove=request.getParameter("set-amount");        
           }
          catch(java.lang.NullPointerException e)
              {
                 addOrRemove="decrement";
              }
           
             
         
          try
           {
           isbn=request.getParameter("isbn");        
           amount=request.getParameter("amount");
            }
          catch(java.lang.NullPointerException e)
              {
                    try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>All fields must be filled</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
               }   
              try
           {
              amountValidate=Integer.parseInt(amount);
              isbnValidate=Integer.parseInt(isbn);
            }
          catch(java.lang.NullPointerException |java.lang.NumberFormatException e)
              {
                 try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Amount and Isbn must to be a numbers!"+amountValidate+";"+isbnValidate+"</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
              }
              if(booksbean.getInfo(isbn) ==null)
         {
              try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Isbn: "+isbnValidate+"</h1>");
                    out.println("<h1>Book Not Found</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
         }
            
       if(addOrRemove.equalsIgnoreCase("increment"))
         {  
          try
           {    booksbean.increasQ(isbn, amountValidate);
             try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Isbn: "+isbnValidate+ "Amount: "+amountValidate+"</h1>");
                    out.println("<h1>Amount Has been Successfully Incremented</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
            
           }
          catch(javax.ejb.NoSuchEJBException | java.lang.NullPointerException |java.lang.NumberFormatException e)
          {
              try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Somthing went wrong!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
          }
             }
             else
             {
                  try
           {    booksbean.decreaseQ(isbn, amountValidate);
                
             try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href=\"additem.jsp\">Back</a>");    
                    out.println("<h1>New Book</h1>");
                    out.println("<h1>Isbn: "+isbnValidate+ "Amount: "+amountValidate+"</h1>");
                    out.println("<h1>Amount Has been Successfully Decremented</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
            
           }
          catch(javax.ejb.NoSuchEJBException | java.lang.NullPointerException |java.lang.NumberFormatException e)
          {
              try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Somthing went wrong!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
          }
             }
             }
            
            
    
        
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
