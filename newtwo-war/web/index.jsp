<%-- 
 
Yavor Nanev,           11060247
--%>

<%@page import="jjb.CurUs"%>
<%@page import="jjb.MyCheck"%>
<%@page import="beans.booksbeanLocal"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="javax.jms.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>


<html>
    <% 
       MyCheck.setBt();
       MyCheck.create();
       MyCheck.singleTocken();
       MyCheck.setCt();
        HttpSession session1= request.getSession(false);
        
        
        
        if( null!= session1.getAttribute("UN") && CurUs.isCurrentUserLogedIn()== true )
        {  
            String s =session1.getAttribute("UN").toString();
            CurUs.setName(s);
            if(session1.getAttribute("UT").equals("user"))
             response.sendRedirect("userpanel.jsp");
            else
             response.sendRedirect("adminpanel.jsp");     
        
        }
    
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
      <form name="login" action="home" method="POST" accept-charset="utf-8">
    <ul>
        <li><label for="username">username</label>
        <input type="usename" name="username" placeholder="username" required></li>
        <li><label for="password">Password</label>
        <input type="password" name="password" placeholder="password" required></li>
        <li>
            <% %>
        <input name='my-nonce' type="hidden" value="<%=MyCheck.getSingleBToken() %>">    
        <input type="submit" value="Login"></li>
    </ul>
</form>
    </body>
</html>
