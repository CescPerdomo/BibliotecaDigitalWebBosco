<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Material, java.util.List" %>
<%@ page import="modelo.Usuario" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuario"); // puede ser null
    List<Material> materiales = (List<Material>) request.getAttribute("materiales");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Material - Biblioteca</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
        }

        .banner {
            background-color: #0d47a1;
            color: white;
            text-align: center;
            padding: 25px;
            font-size: 28px;
            font-weight: bold;
        }

        .container {
            width: 90%;
            max-width: 1000px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .search-box {
            text-align: center;
            margin-bottom: 30px;
        }

        input[type="text"] {
            width: 70%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #0d47a1;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #1565c0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #1565c0;
            color: white;
        }

        a.detalle {
            text-decoration: none;
            color: #0d47a1;
            font-weight: bold;
        }

        .footer {
            margin-top: 40px;
            text-align: center;
            padding: 12px;
            background-color: #1565c0;
            color: white;
        }

    </style>
</head>
<body>

<div class="banner">
    Buscar Material en Biblioteca
</div>

<div class="container">
    <div class="search-box">
        <form action="buscar-material" method="post">
            <input type="text" name="busqueda" placeholder="Buscar por título, autor, tipo..." required>
            <input type="submit" value="Buscar">
        </form>
    </div>

    <% if (materiales != null && !materiales.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>Autor</th>
                    <th>Estado</th>
                    <th>Detalle</th>
                </tr>
            </thead>
            <tbody>
            <% for (Material m : materiales) { %>
                <tr>
                    <td><%= m.getId() %></td>
                    <td><a href="detalle?id=<%= m.getId() %>" class="detalle"><%= m.getTitulo() %></a></td>
                    <td><%= m.getTipoMaterial() %></td>
                    <td><%= m.getAutor() %></td>
                    <td><%= m.getEstado() %></td>
                    <td><a href="detalle?id=<%= m.getId() %>" class="detalle">🔍 Ver</a></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } else if (request.getParameter("busqueda") != null) { %>
        <p style="text-align:center; color: #b71c1c;">No se encontraron materiales con esa búsqueda.</p>
    <% } %>
</div>

<div class="footer">
    &copy; 2025 Biblioteca Colegio Amigos de Don Bosco
</div>

</body>
</html>
