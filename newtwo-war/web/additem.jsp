<%-- 
    
--%>

<%@page import="jjb.MyCheck"%>
<%@page import="jjb.CurUs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
    </head>
    <body>
        <%      HttpSession session1= request.getSession(false);
                MyCheck.setBt();
                if(session1.getAttribute("UN").toString()!=null 
                && session1.getAttribute("UN").toString().equalsIgnoreCase(CurUs.getName()) 
                && session1.getAttribute("UT").toString().equalsIgnoreCase("admin"))
                {     
        %>
        <jsp:include page="adheader.jsp" />
        <div class="container">
            <div class="form-control">
                <form class="search-name" action="product" method="POST">
                    <input type="hidden" name="remove"  value="add"> 
                    <input type="hidden" name="token"  value=<%= MyCheck.getBToken() %>> 
                    <input type="text" name="isbn"   placeholder="ISBN"      value=""> 
                    <input type="text" name="title"  placeholder="Title"     value=""> 
                    <input type="text" name="amount" placeholder="Amount"    value="">
                    <button class="button" type="submit" name='bal'  value="add">Add-product</button>
                </form>
            </div>
            <div class="form-control">
            <form class="search-name" action="product" method="POST">
                <input type="hidden" name="token"  value=<%= MyCheck.getBToken() %>> 
                <input type="hidden" name="remove"  value="remove"> 
                <input type="text" name="isbn" placeholder="ISBN"    value=""> 
                <button class="button" type="submit" name='rall'  value="removeall">Remove-product</button>
            </form>
            </div>
            <div class="form-control">
            <form class="search-name" action="amount" method="POST">
                <input type="hidden" name="token"  value=<%= MyCheck.getBToken() %>> 
                <input type="text" name="isbn" placeholder="ISBN"    value="">
                <input type="text" name="amount" placeholder="Amount"  value="">
                <button class="button" type="submit" name='set-amount'  value="increment">Increment</button>
            </form>
            </div>
             <div class="form-control">
            <form class="search-name" action="amount" method="POST">
                <input type="hidden" name="token"  value=<%= MyCheck.getBToken() %>> 
                <input type="text" name="isbn" placeholder="ISBN"    value=""> 
                <input type="text" name="amount" placeholder="Amount"  value="">
                <button class="button" type="submit" name='set-amount'  value="decrement">Decrement</button>
            </form>
            </div>
                </div>  
        <% } else {  %>      
            <h1>Access Denied!</h1>
            <% } %>
               
    </body>
</html>
