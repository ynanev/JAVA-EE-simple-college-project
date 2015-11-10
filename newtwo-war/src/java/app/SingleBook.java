/*

Yavor Nanev,           11060247
 */
package app;
import beans.bookcommentsLocal;
import beans.booksbeanLocal;
import ente.Book;
import ente.BookComments;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jjb.MyCheck;
import jjb.PstArg;
import static jjb.PstArg.isbn;

/**
 *
 * @author Rock n Roll
 */
public class SingleBook extends HttpServlet {
    @EJB
    private bookcommentsLocal bookcomments;
    @EJB
    private booksbeanLocal booksbean;
    MyCheck no=new MyCheck();
   
                
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
             
      String n =  request.getParameter("my-nonce");
        if(MyCheck.validateSBT(Integer.parseInt(n)))
      {
         String bookname = request.getParameter("book-name");
         Book book=booksbean.getInfoByName(bookname.toLowerCase());       
       
            if(book != null)
              {
                 PstArg.setIsbn(""+book.getIsbn());
                 PstArg.setBook(book);
                 request.setAttribute("b",book);
                 request.setAttribute("c",bookcomments);

                  this.getServletContext().getRequestDispatcher("/single.jsp").forward(request, response);  
                 }
            else
                {
                 String error="Book not there!";
                 request.setAttribute("error",error);
                 this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);

                }
      }
      else
        {
              String error="no result!";
              request.setAttribute("error",error);
              this.getServletContext().getRequestDispatcher("/userpanel.jsp").forward(request, response);
        }   

      }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
