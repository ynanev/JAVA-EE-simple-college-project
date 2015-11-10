<%-- 

Yavor Nanev,           11060247
--%>

<%@page import="jjb.PstArg"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="beans.CartBeanLocal"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="beans.bookcommentsLocal"%>
<%@page import="javax.inject.Inject"%>
<%@page import="jjb.MyCheck"%>
<%@page import="beans.booksbean"%>
<%@page import="ente.Book"%>
<%@page import="java.util.List"%>
<%@page import="beans.booksbeanLocal"%>
<%@page import="ente.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html lang="en">
    <% MyCheck.create(); %>
 <%!
     booksbeanLocal bookbean= null;
    public void jspInit () {
    try {
    InitialContext ctx = new InitialContext();
    bookbean = (booksbeanLocal)ctx.lookup("java:global/newtwo/newtwo-ejb/booksbean!beans.booksbeanLocal");
    } catch (Exception e) {
    e.printStackTrace ();
    }
    }
    
 %>
 <%  %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
    </head>
    <body>
        <div class="container">
             <a href="userpanel.jsp">Home</a>
              <form action="submit" method="post">
              <input type="submit" name="logout"  value="LogOut">
              </form>
              <form class="search-name" action="cart" method="POST">
             <input type="hidden" name="par" value="cartview">
             <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken() %>">
             <input class="button" type="submit" name="viewcar" value="ViewCart">
              </form>
            <nav>
                <div class="col-4">
                    <form class="search-id" action="isbn" method="POST">
                        <input type="text" id="isbn" name="isbn" placeholder="Search by ISBN">
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getSingleBToken()%>">
                        <input class="button" type="submit" name="isbn-submit" value="Search">
                    </form>
                </div>
                <div class="col-4">
                    <form class="search-name" action="book" method="POST">
                        <input type="text" id="isbn" name="book-name" placeholder="Search by name">
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getSingleBToken()%>">
                        <input class="button" type="submit" name="book-name-submit" value="Search">
                    </form>
                </div>
            </nav>
        </div>
        <div class="container"> 
            <div class="row">
        <% 
           List<Book> books= bookbean.books();%>
          <% for(int i=0;i<books.size();i++)
            { %>
            <div class="col-4 home-box">
                <a href="isbn?isbn=<%=books.get(i).getIsbn()%>">

                    <h1>
                        <%= PstArg.escapeString(books.get(i).getBookname())%>
                    </h1>
                </a>
            </div>
          <% } %>
          </div>
        </div>
    </body>
</html>
