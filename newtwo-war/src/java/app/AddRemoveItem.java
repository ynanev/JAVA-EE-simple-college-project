/*

Yavor Nanev,           11060247
 */
package app;

import beans.bookcommentsLocal;
import beans.booksbeanLocal;
import ente.Book;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jjb.CurUs;
import jjb.MyCheck;

/**
 *
 * @author Rock n Roll
 */
public class AddRemoveItem extends HttpServlet {
    @EJB
    private bookcommentsLocal bookcomments;
    @EJB
    private booksbeanLocal booksbean;
    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
        HttpSession session1= request.getSession(false);
        int token=0;
          try
           {
            
             token = Integer.parseInt(request.getParameter("token"));
           }
          catch(javax.ejb.NoSuchEJBException | java.lang.NullPointerException |java.lang.NumberFormatException e)
              {
                 
              }
        
        
        
        if(session1.getAttribute("UN").toString()!=null 
                && session1.getAttribute("UN").toString().equalsIgnoreCase(CurUs.getName()) 
                && session1.getAttribute("UT").toString().equalsIgnoreCase("admin") && token==MyCheck.getBToken() )
        {
            String addOrRemove="";
            try
           {
            addOrRemove=request.getParameter("remove"); 
          
           }
          catch(javax.ejb.NoSuchEJBException | java.lang.NullPointerException |java.lang.NumberFormatException e)
              {
                 addOrRemove="add";
              }
         if(addOrRemove.equalsIgnoreCase("add"))
         {       
          String isbn="";
          String title="";
          String amount="";
           int amountValidate=0;
           int isbnValidate=0;
          try
           {
           isbn=request.getParameter("isbn");
           title=request.getParameter("title");
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
                    out.println("<h1>Amount and Isbn must to be a numbers!"+amountValidate+";;;"+isbnValidate+"</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
              }
         if(booksbean.getInfo(isbn) !=null)
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
                    out.println("<h1>Allready exist in the database</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
         }
         try
           {
            booksbean.addBook(isbnValidate, title,amountValidate);
             
       
               String line="Added:  Isbn :"+isbnValidate+" :"+dateFormat.format(date)+" Title: "+title+" Amount: "+amountValidate+" addmin:"+session1.getAttribute("UN").toString();
                 toOrderLogFile(line);
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
                    out.println("<h1>Isbn: "+isbnValidate+" Title: "+title+"Amount: "+amountValidate+"</h1>");
                    out.println("<h1>Has been Successfully added to the database</h1>");
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
         } /////////////////////// removing //////////////////////////
         else 
         {
              String isbn="";
             int isbnValidate=0;
                 try
           {
              isbn=request.getParameter("isbn");  
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
              
              isbnValidate=Integer.parseInt(isbn);
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
                    out.println("<h1>Isbn must to be a numbers!"+isbnValidate+";"+isbnValidate+"</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
              }
             
              try
           {
             Book book= (Book) booksbean.getInfo(""+isbnValidate);
              booksbean.removeBook(isbnValidate);
              String s=""+book.getIsbn();
              String line="Removed:  Isbn"+s+" :"+dateFormat.format(date)+" Title: "+book.getBookname()+" Amount : "+book.getAmount()+"\t All related comments!"+" addmin:"+session1.getAttribute("UN").toString();;
              toOrderLogFile(line);
              bookcomments.removeAllBookCooments(isbnValidate);
              
             try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                     out.println("<a href=\"additem.jsp\">Back</a>");                    
                    out.println("<h1>Book</h1>");
                    out.println("<h1>Isbn: "+isbnValidate+"</h1>");
                    out.println("<h1>Has been Successfully Removed from the database</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
            
           }
          catch( javax.ejb.EJBException |  java.lang.NullPointerException |java.lang.NumberFormatException e)
          {
              try (PrintWriter out = response.getWriter()) 
                    {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title></title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href=\"additem.jsp\">Back</a>");
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Book not found!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
          }
           
         }
   
         
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
                    out.println("<h1>Error</h1>");
                    out.println("<h1>Access Denied!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
        }
    
    }
    public void toOrderLogFile(String line) throws FileNotFoundException, IOException
 {
       try(PrintWriter output = new PrintWriter(new FileWriter("C:\\netbeanProjects\\newtwo\\newtwo-war\\src\\java\\app\\logProduct.txt",true))) 
		 {
		     output.println(line);
		 } 
		 catch (Exception e) {}	
       
 
	
 }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
