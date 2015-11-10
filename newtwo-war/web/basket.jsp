<%-- 
 
Yavor Nanev,           11060247
--%>

<%@page import="jjb.PstArg"%>
<%@page import="jjb.CurUs"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="beans.bookcommentsLocal"%>

<%@page import="jjb.MyCheck"%>
<%@page import="java.util.List"%>
<%@page import="ente.Book"%>
<%@page import="java.util.HashMap"%>
<%@page import="beans.CartBeanLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%   MyCheck ram=new MyCheck(); ram.create();
  
     %>
      <%! 
 
    bookcommentsLocal bookcomments= null;
    public void jspInit () {
    try {
    InitialContext ctx = new InitialContext();
    bookcomments = (bookcommentsLocal)ctx.lookup("java:global/newtwo/newtwo-ejb/bookcomments!beans.bookcommentsLocal");
    } catch (Exception e) {
    e.printStackTrace ();
    }
    }    
      %>
       <%!   CartBeanLocal  cartBean= null;
     public void jspInitt () {
     try {
     InitialContext ctx = new InitialContext();
     cartBean = (CartBeanLocal)ctx.lookup("java:global/newtwo/newtwo-ejb/CartBean!beans.CartBeanLocal ");
    } catch (Exception e) {
    e.printStackTrace ();
    }
    }%>

    
    
    

    <head>
       
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
    </head>
    <body>
        <div class="container"> 
             <a href="userpanel.jsp">Home</a>
             <nav>
                <div class="col-4">
                    <form class="search-id" action="isbn" method="POST">
                        <input type="text" id="isbn" name="isbn" placeholder="Search by ISBN">
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getBToken()%>">
                        <input class="button" type="submit" name="isbn-submit" value="Search">
                    </form>
                </div>
                <div class="col-4">
                    <form class="search-name" action="book" method="POST">
                        <input type="text" id="isbn" name="book-name" placeholder="Search by name">
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getBToken()%>">
                        <input class="button" type="submit" name="book-name-submit" value="Search">
                    </form>
                </div>
                <div class="col-4">
                    <form action="allbooks" method="POST" >
                        <input name='my-nonce' type="hidden" value="<%=MyCheck.getBToken()%>">
                        <input  type="submit" value="See all"/>
                    </form>
                        <%if(request.getAttribute("error")!=null)
                    { %>
                    <p><%=request.getAttribute("error")%></p>
                    <% } %>
                </div>
            </nav>
        </div>
                    <% CartBeanLocal cartBeann= CurUs.getCartBean();
           List<Book> b= cartBeann.cartList();
          %>
          <div class="container"> 
            <div class="row">
                <form action="cart" method="POST">
        <% %>
        
          <%if(b.size()!=0)
          {
           for(int i=0;i<b.size();i++)
            { %>
            <div class="col-4 home-box">
                
                    <h1>
                        <%=PstArg.escapeString(b.get(i).getBookname())%>
                    </h1>
                    <h1>
                        <%=b.get(i).getAmount() %>
                    </h1>
                     <h1>
                        <%=b.get(i).getIsbn() %>
                     </h1>
                    <button class="button" type="submit" name='remove-isbn'  value="<%=b.get(i).getIsbn()%>">Remove</button>
                    <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken() %>">
                    <input type="hidden" name="par" value="remove">
                </a>
            </div>
          <% } %>
         
          
          </form>
          <form action="cart" method="POST">
          <button class="button" type="submit" name='rall'  value="removeall">Cancel</button>
          <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken()%>">
          <input type="hidden" name="par" value="removeall">
          </form>
          <form action="cart" method="POST">
          <button class="button" type="submit" name='sall'  value="purches">Buy all</button>
          <input name='my-nonce' type="hidden" value="<%=MyCheck.getCToken()%>">
          <input type="hidden" name="par" value="purches">
          </form>
          <div>
          </div>
        <% } else { %>
          <p>Your cart is empty!</p>
          <%}%>
    </body>
</html>
