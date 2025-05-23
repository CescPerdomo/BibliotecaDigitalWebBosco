<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión - Biblioteca</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
        }

        .banner {
            background-color: #0d47a1;
            color: white;
            text-align: center;
            padding: 20px 0;
            font-size: 24px;
            font-weight: bold;
        }

        .login-box {
            width: 400px;
            margin: 60px auto;
            padding: 30px;
            background: white;
            border: 1px solid #ccc;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            border-radius: 6px;
        }

        h2 {
            text-align: center;
            color: #0d47a1;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            margin-top: 20px;
            padding: 12px;
            background-color: #0d47a1;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #1565c0;
        }

        .info {
            text-align: center;
            margin-top: 15px;
            color: #1565c0;
            font-size: 14px;
        }

        .footer {
            background-color: #1565c0;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

    </style>
</head>
<body>

<div class="banner">
    Sistema de Biblioteca Escolar
</div>

<div class="login-box">
    <h2>Iniciar Sesión</h2>
    <form action="login" method="post">
        <label>Usuario:</label>
        <input type="text" name="usuario" required>

        <label>Contraseña:</label>
        <input type="password" name="contrasena" required>

        <input type="submit" value="Ingresar">
    </form>

    <div class="info">
        Solo personal autorizado puede acceder al sistema
    </div>
</div>

<div class="footer">
    &copy; 2025 Biblioteca Colegio Amigos de Don Bosco
</div>

</body>
</html>
