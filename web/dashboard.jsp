<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Principal - Biblioteca</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .banner {
            background-color: #0d47a1;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .contenedor {
            max-width: 800px;
            margin: 30px auto;
            background-color: white;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        h2 {
            margin-top: 0;
            color: #333;
        }

        .opciones {
            margin-top: 30px;
        }

        .boton {
            display: inline-block;
            margin: 15px;
            padding: 15px 25px;
            background-color: #1565c0;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .boton:hover {
            background-color: #0b4ea0;
        }

        .footer {
            text-align: center;
            margin-top: 60px;
            font-size: 13px;
            color: #aaa;
        }
    </style>
</head>
<body>

    <div class="banner">
        📚 Bienvenido a la Biblioteca Digital Colegio Amigos de Don Bosco
    </div>

    <div class="contenedor">
        <h2>¿Que quieres descrubrir hoy?<%= usuario.getUsuario() %> 👋</h2>
       

        <div class="opciones">
            <a href="buscar.jsp" class="boton">🔍 Buscar Material</a>
            <a href="mis-prestamos" class="boton">📦 Mis Préstamos</a>

            <% if ("ADMIN".equals(usuario.getRol().name())) { %>
                <a href="usuarios" class="boton">👤 Gestión de Usuarios</a>
                <a href="mora.jsp" class="boton">💰 Ver Moras</a>
                <a href="devoluciones" class="boton">📤 Devoluciones / Renovaciones</a>
            <% } %>

            <a href="logout.jsp" class="boton">🚪 Cerrar Sesión</a>
        </div>
    </div>

    <div class="footer">
        &copy; 2025 Biblioteca Digital | Desarrollado en Java Web con JSP y Servlets
    </div>

</body>
</html>
