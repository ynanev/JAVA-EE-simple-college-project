/*
 
Yavor Nanev,           11060247
 */
package app;

import beans.CartBeanLocal;
import beans.bookcomments;
import beans.bookcommentsLocal;
import beans.booksbean;
import beans.booksbeanLocal;
import ente.Book;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import jjb.CurUs;
import jjb.MyCheck;
import jjb.PstArg;

/**
 *
 * @author Rock n Roll
 */
public class CartHandler extends HttpServlet {
    @EJB
    private bookcommentsLocal bookcomments;
    @EJB
    private booksbeanLocal booksbean;
    
    CartBeanLocal cartBean = lookupCartBeanLocal();
    
    
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
      }

   
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        boolean one=false;
            try
        {
           String n =  request.getParameter("my-nonce");
           String isbn= request.getParameter("isbnn");
           Book book=  booksbean.getInfo(isbn);
           PstArg.setBook(book);
           cartBean.addItem(isbn, 1);
           CurUs.setCartBean(cartBean);
           one=true;
           this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);
        }
           catch ( javax.ejb.EJBException | java.lang.NullPointerException | ServletException | IOException  e) {
             
              one=false;
           
        }  
     

      if(one==false)  
      {
         try
        {
        String par= request.getParameter("par");
        String par1=request.getParameter("remove-all");
        String n =  request.getParameter("my-nonce");
        int value=Integer.parseInt(n);
        
      if(MyCheck.validateCT(value))
      { 
       
        if(par.equalsIgnoreCase("cartview") && !par.equalsIgnoreCase(" ") )
        {    if(cartBean != null)
             {
             request.setAttribute("basket",cartBean);
             }
             if(CurUs.getCartBean()!=null)
             {
                CurUs.setCartBean(cartBean);
             }
             else
             {  cartBean.clearList();
                CurUs.setCartBean(cartBean); 
             }
             this.getServletContext().getRequestDispatcher("/basket.jsp").forward(request, response);
        }  
        
      
      
             else if (par.equalsIgnoreCase("removeall"))
        {   
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
       
            String line="Cancelled: "+CurUs.getName()+":  "+dateFormat.format(date)+":  "+cartBean.getItemListLine()+"";
            toOrderLogFile(line);
            
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
             out.println("<a href=\"userpanel.jsp\">Home</a>");
            out.println("<h1>cansled</h1>");
            out.println("<h1>"+cartBean.getItemList()+"</h1>");
            out.println("</body>");
            out.println("</html>");
            booksbean.returnBooks(cartBean.cartList());
            cartBean.clearList();
           
           
        
        }
           // this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response); 
        }
         else if (par.equalsIgnoreCase("purches"))
         {
             
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
       
            String line="Purchased: "+CurUs.getName()+":  "+dateFormat.format(date)+":  "+cartBean.getItemListLine()+"";
            toOrderLogFile(line);
             try (PrintWriter out = response.getWriter()) {
               out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"userpanel.jsp\">Home</a>");
            out.println("<h1>Pay Window</h1>");
             out.println("<h1>"+cartBean.getItemList()+"</h1>");
            out.println("</body>");
            out.println("</html>"); 
            cartBean.clearList();
         }
         }
        else 
        {
            String isbn= request.getParameter("remove-isbn");
            cartBean.removeItem(isbn, 1);
            CurUs.setCartBean(cartBean);
            request.setAttribute("basket",cartBean);
            this.getServletContext().getRequestDispatcher("/basket.jsp").forward(request, response); 
        }
      }
        }
         catch (javax.ejb.EJBTransactionRolledbackException ee) {
             
             
              String error="Nothing in your cartttttt!";
              request.setAttribute("error",error);
              this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
         
        }
           catch ( javax.ejb.EJBException   | java.lang.NullPointerException    ea) {
             
             
              String error="Nothing in your !";
              request.setAttribute("error",error);
              this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
         
        }
     
      }
        
     
      
    }

    private CartBeanLocal lookupCartBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartBeanLocal) c.lookup("java:global/newtwo/newtwo-ejb/CartBean!beans.CartBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
 public void toOrderLogFile(String line) throws FileNotFoundException, IOException
 {
       try(PrintWriter output = new PrintWriter(new FileWriter("C:\\netbeanProjects\\newtwo\\newtwo-war\\src\\java\\app\\logo.txt",true))) 
		 {
		     output.println(line);
		 } 
		 catch (Exception e) {}	
       
 
	
 }
 }
 
 
 
