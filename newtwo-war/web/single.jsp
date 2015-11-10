<%-- 
 
Yavor Nanev,           11060247
--%>

<%@page import="jjb.PstArg"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="jjb.MyCheck"%>
<%@page import="ente.BookComments"%>
<%@page import="beans.bookcommentsLocal"%>
<%@page import="beans.bookcomments"%>
<%@page import="beans.booksbean"%>
<%@page import="ente.Book"%>
<%@page import="java.util.List"%>
<%@page import="beans.booksbeanLocal"%>
<%@page import="ente.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%!
     bookcommentsLocal bookcomments= null;
    public void jspInit () {
    try {
    InitialContext ctx = new InitialContext();
    bookcomments = (bookcommentsLocal)ctx.lookup("java:global/newtwo/newtwo-ejb/bookcomments!beans.bookcommentsLocal");
    } catch (Exception e) {
    e.printStackTrace ();
    }
    }%>
        <% Book book=PstArg.getBook(); %>
        <%
           List<BookComments> commentsList= bookcomments.getBookComments(book.getIsbn());
             
        %>
        <title><%=book.getBookname() %></title>
        <link rel="stylesheet" href="style.css" type="text/css" />
    </head>
    <body>
        <div class="container"> 
            <a href="userpanel.jsp">Home</a>
             <form class="search-name" action="cart" method="POST">
             <input type="hidden" name="par" value="cartview">
             <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken() %>">  
             <input class="button" type="submit" name="viewcard" value="ViewCart">
             </form>
            <nav>
                <div class="col-4">
                    <form class="search-id" action="isbn" method="POST">
                        <input type="text" id="isbn" name="isbn" placeholder="Search by ISBN">
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getSingleBToken() %>">  
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
                <div class="col-4">
                    <form action="allbooks" method="POST" >
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getBToken()%>">  
                        <input  type="submit" value="See all"/>
                    </form>
                </div>
            </nav>
        </div>
        <div class="container">
        
            <div class="col-4 home-box">
                <img src="images/java.jpg">
                <div class="book-description">
                <h1>
                 <%=PstArg.escapeString(book.getBookname()) %> 
                </h1>
                <span>
                ISBN: <%= book.getIsbn() %> 
                </span>
               </div>
                <form class="search-name" action="cart" method="POST">
                         <input type="hidden" name="isbnn" value=<%=book.getIsbn()%>>   
                         <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken() %>">  
                        <input class="button" type="submit" name="addToCart" value="Add-To-Card">
                    </form>
            </div>
                <div class="col-8">
                    <ul class="comments">
                <% for(int i=0;i<commentsList.size();i++)
                { %>
                    <li>
                        <span class="avatar">User avatar :)</span>
                        <p class="author-name text-align-left">Author name: <%=commentsList.get(i).getUsername() %></p>
                        <p class="text-align-left">Comment: <%= PstArg.escapeString(commentsList.get(i).getComment()) %></p>
                    </li>
                
                <% } %>
                    </ul>
                      <form  action="submit" method="POST">
                    <span>Add comment</span>
                    <textarea name="textarea" style="width:250px;height:150px;"></textarea>
                    <input name='my-nonce' type="hidden" value="<%=MyCheck.getBToken()%>">
                    <input name='comment' type="hidden" value="addcoment"> 
                    <input type="hidden" name="isbn" value=<%=book.getIsbn()%>>
                    <input class="button" type="submit" name="comment-submit"  value="Submit">
                      </form>
                </div>
        </div>
    </body>
</html>

