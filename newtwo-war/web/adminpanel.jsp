<%-- 

Yavor Nanev,           11060247
--%>

<%@page import="jjb.CurUs"%>
<%@page import="jjb.MyCheck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
     <% String userPermition="admin";
        String s="you dont have permition";
        HttpSession session1= request.getSession(false);

        if( null!= session1.getAttribute("UN"))
        {
            
            CurUs.setUsertyppe(session1.getAttribute("UT").toString());
            CurUs.setName(session1.getAttribute("UN").toString());
           //  response.sendRedirect("userpanel.jsp");
                  
        
        }
              
        if(CurUs.getUsertyppe().equalsIgnoreCase(userPermition))
        {
            s="Admin DashBoard";
        }
       if(session1.getAttribute("UN").toString()!=null 
                && session1.getAttribute("UN").toString().equalsIgnoreCase(CurUs.getName()) 
                && session1.getAttribute("UT").toString().equalsIgnoreCase("admin"))
                {              
             
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="adheader.jsp" />
        <h1><%=s%></h1>
         <% } else {  %>      
            <h1>Access Denied!</h1>
            <% } %>
    </body>
</html>
