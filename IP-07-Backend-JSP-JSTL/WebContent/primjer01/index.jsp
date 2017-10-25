<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="objekt1" class="primjer01.HelloWorld" scope="session" >
            <jsp:setProperty name="objekt1" property="name" value=" Hello world" />
        </jsp:useBean>
        <h1><jsp:getProperty name="objekt1" property="name" /></h1>
    </body>
</html>
