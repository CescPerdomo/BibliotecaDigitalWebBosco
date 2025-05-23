<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario, modelo.Role" %>
<%
    Usuario admin = (Usuario) session.getAttribute("usuario");
    if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Usuario</title>
    <style>
        body { font-family: Arial; padding: 30px; }
        h2 { text-align: center; color: #0d47a1; }
        form {
            width: 400px; margin: auto; padding: 20px;
            border: 1px solid #ccc; background: #f5f5f5;
        }
        label { display: block; margin-top: 10px; }
        input[type="text"], input[type="password"], select {
            width: 100%; padding: 8px; margin-top: 5px;
        }
        input[type="submit"] {
            background: #0d47a1; color: white; padding: 10px 20px;
            border: none; margin-top: 20px;
        }
    </style>
</head>
<body>

<h2>➕ Agregar Usuario</h2>

<form action="agregar-usuario" method="post">
    <label>Usuario:</label>
    <input type="text" name="usuario" required>

    <label>Contraseña:</label>
    <input type="password" name="contrasena" required>

    <label>Rol:</label>
    <select name="rol" required>
        <option value="ALUMNO">ALUMNO</option>
        <option value="PROFESOR">PROFESOR</option>
        <option value="ADMIN">ADMIN</option>
    </select>

    <label>Ejemplares Prestados:</label>
    <input type="text" name="ejemplares" value="0" required>

    <input type="submit" value="Crear Usuario">
</form>

</body>
</html>
