<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario, modelo.Role" %>
<%
    Usuario admin = (Usuario) session.getAttribute("usuario");
    if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
        response.sendRedirect("login.jsp");
        return;
    }

    Usuario usuarioEditar = (Usuario) request.getAttribute("usuarioEditar");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; }

        .banner {
            background-color: #0d47a1;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }

        .form-container {
            width: 450px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }

        h2 {
            color: #0d47a1;
            text-align: center;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }

        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #0d47a1;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            font-size: 16px;
            border-radius: 4px;
            margin-top: 25px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #1565c0;
        }

        .footer {
            background-color: #1565c0;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: 60px;
        }
    </style>
</head>
<body>

<div class="banner">
    Editar Usuario
</div>

<div class="form-container">
    <% if (usuarioEditar != null) { %>
    <form action="editar-usuario" method="post">
        <input type="hidden" name="id" value="<%= usuarioEditar.getId() %>">

        <label>Nombre de Usuario:</label>
        <input type="text" value="<%= usuarioEditar.getUsuario() %>" disabled>

        <label>Contraseña:</label>
        <input type="password" name="contrasena" value="<%= usuarioEditar.getContrasena() %>" required>

        <label>Rol:</label>
        <select name="rol" required>
            <% for (Role r : Role.values()) { %>
                <option value="<%= r.name() %>" <%= r.equals(usuarioEditar.getRol()) ? "selected" : "" %>><%= r.name() %></option>
            <% } %>
        </select>

        <label>Ejemplares Prestados:</label>
        <input type="text" name="ejemplares" value="<%= usuarioEditar.getEjemplaresPrestados() %>" required>

        <input type="submit" value="Actualizar Usuario">
    </form>
    <% } else { %>
        <p style="color:#b71c1c; text-align:center;">No se pudo cargar el usuario a editar.</p>
    <% } %>
</div>

<div class="footer">
    &copy; 2025 Biblioteca Colegio Amigos de Don Bosco
</div>

</body>
</html>


