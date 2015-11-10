/*

Yavor Nanev,           11060247
 */
package app;

import beans.CartBeanLocal;
import beans.beanLocal;
import beans.booksbeanLocal;
import ente.Book;
import ente.Users;
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

/**
 *
 * @author Rock n Roll
 */
public class serv extends HttpServlet {
    CartBeanLocal cartBean = lookupCartBeanLocal();
    @EJB
    private booksbeanLocal booksbean;
    @EJB
    private beanLocal bean;
    
   
   
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      // String b=userBean.userIn("bla");
        int num=0;
         try {
               String s=request.getParameter("my-nonce");
               num=Integer.parseInt(s);
               
             }
        catch (Exception  e)
             {
                    CurUs.setCurrentUserLogedIn(false);
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
             }
        
         try  {
             if(num==MyCheck.getSingleBToken())
             {
            Users u;
            String chat="";
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String ss= cartBean.getUsername();
            if(!ss.equalsIgnoreCase(username))
            {
               CurUs.setCartBean(null);
            }
            else
            {
               CurUs.setCartBean(cartBean);
            }
             String n =  request.getParameter("my-nonce");
             String sv=username;
             String typ=bean.user(username).getType();
      if(bean.isIn(username,password))
      {
            /* TODO output your page here. You may use following sample code. */
             boolean s=bean.isIn(username,password);
             if(s)
             {  cartBean.setUsername(username);
                
                HttpSession session = request.getSession(true);
                session.setAttribute("UN", sv);
                session.setAttribute("UT", typ);
                CurUs.setName(username);
                CurUs.setUsertyppe(bean.user(username).getType());
               
                if(CurUs.getUsertyppe().equalsIgnoreCase("user"))
                { 
                    CurUs.setCurrentUserLogedIn(true);
                  
                   this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
                }
                else
                {   
                    CurUs.setCurrentUserLogedIn(true);
                    this.getServletContext().getRequestDispatcher("/adminpanel.jsp").forward(request, response);
                }
             }
             else
             {
                    CurUs.setCurrentUserLogedIn(false);
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
             } 
          
        }
    }
         }
     catch (ServletException | IOException | javax.ejb.EJBException e)
             {
                    CurUs.setCurrentUserLogedIn(false);
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
             }
    
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     
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
}
