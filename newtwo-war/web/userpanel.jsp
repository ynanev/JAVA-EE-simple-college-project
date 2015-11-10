<%-- 

Yavor Nanev,           11060247
--%>


<%@page import="jjb.CurUs"%>
<%@page import="jjb.MyCheck"%>
<%@page import="beans.booksbean"%>
<%@page import="ente.Book"%>
<%@page import="java.util.List"%>
<%@page import="beans.booksbeanLocal"%>
<%@page import="ente.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    int a=MyCheck.getBToken() ; %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
    </head>
    <body>
              <form action="submit" method="post">
              <input type="submit" name="logout"  value="LogOut">
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
                        <input name='bookname' type="hidden" value="bookname">
                        <input class="button" type="submit" name="book-name-submit" value="Search">
                    </form>
                </div>
                <div class="col-4">
                    <form action="allbooks" method="POST" >
                     <input name='my-nonce' type="hidden"  value="<%=MyCheck.getBToken() %>">
                        <input  type="submit" value="See all"/>
                    </form>
                    <%if(request.getAttribute("error")!=null)
                    { %>
                    <p><%=request.getAttribute("error")%></p>
                    <% } %>
                </div>
            </nav>
        </div>
    </body>
</html>
