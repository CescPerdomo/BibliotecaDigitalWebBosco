<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Material, modelo.Usuario" %>
<%
    Material m = (Material) request.getAttribute("material");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle de Material</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
        }

        .banner {
            background-color: #0d47a1;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 26px;
        }

        .container {
            width: 600px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .label {
            font-weight: bold;
            color: #0d47a1;
        }

        .info {
            margin: 10px 0;
        }

        .footer {
            background-color: #1565c0;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: 40px;
        }

        .btn {
            margin-top: 25px;
            background-color: #0d47a1;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #1565c0;
        }

        .alert {
            color: #b71c1c;
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="banner">
    Detalle del Material
</div>

<div class="container">
    <% if (m != null) { %>
        <div class="info"><span class="label">ID:</span> <%= m.getId() %></div>
        <div class="info"><span class="label">Título:</span> <%= m.getTitulo() %></div>
        <div class="info"><span class="label">Autor:</span> <%= m.getAutor() %></div>
        <div class="info"><span class="label">Tipo:</span> <%= m.getTipoMaterial() %></div>
        <div class="info"><span class="label">Estado:</span> <%= m.getEstado() %></div>

        <%-- Campos opcionales según tipo --%>
        <% if ("Revista".equals(m.getTipoMaterial())) { %>
            <div class="info"><span class="label">ISSN:</span> <%= m.getIssn() %></div>
            <div class="info"><span class="label">N° Páginas:</span> <%= m.getNumeroPaginas() %></div>
        <% } else if ("AV".equals(m.getTipoMaterial())) { %>
            <div class="info"><span class="label">Duración:</span> <%= m.getDuracionMinutos() %> min</div>
            <div class="info"><span class="label">Año:</span> <%= m.getAnio() %></div>
        <% } %>

        <%-- Botón prestar solo si hay sesión y material activo --%>
        <% if (usuario != null && ("ACTIVO".equalsIgnoreCase(m.getEstado()) || "DISPONIBLE".equalsIgnoreCase(m.getEstado()))) { %>
            <form action="prestar-material" method="post">
                <input type="hidden" name="idMaterial" value="<%= m.getId() %>">
                <input type="submit" class="btn" value="📚 Prestar Material">
            </form>
        <% } else if (usuario == null) { %>
            <div class="info" style="margin-top: 20px; color: #b71c1c; text-align: center;">
                Inicia sesión para prestar este material.
            </div>
        <% } %>

        <% if ("limite".equals(mensaje)) { %>
            <div class="alert">Has alcanzado tu límite de préstamos activos.</div>
        <% } else if ("error".equals(mensaje)) { %>
            <div class="alert">Error al registrar el préstamo. Intenta más tarde.</div>
        <% } %>
    <% } else { %>
        <div class="alert">El material solicitado no fue encontrado.</div>
    <% } %>
</div>

<div class="footer">
    &copy; 2025 Biblioteca Colegio Amigos de Don Bosco
</div>

</body>
</html>
