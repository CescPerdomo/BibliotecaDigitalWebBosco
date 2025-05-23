<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate(); // Cierra la sesión actual
    response.sendRedirect("login.jsp"); // Redirige al login
%>
