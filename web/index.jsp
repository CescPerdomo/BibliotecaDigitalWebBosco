<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Biblioteca Amigos Don Bosco - Bienvenida</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", sans-serif;
            background: linear-gradient(to bottom right, #e3f2fd, #ffffff);
        }

        .banner {
            background-color: #0d47a1;
            color: white;
            padding: 40px 20px;
            text-align: center;
            font-size: 32px;
            font-weight: bold;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }

        .contenedor {
            max-width: 600px;
            margin: 60px auto;
            background-color: #ffffff;
            border-radius: 12px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
        }

        .contenedor p {
            font-size: 18px;
            margin-bottom: 30px;
            color: #333333;
        }

        .boton {
            display: inline-block;
            padding: 12px 30px;
            margin: 10px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            font-size: 16px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .boton:hover {
            background-color: #0b60a2;
        }

        .footer-completo {
            background-color: #1565c0; /* NUEVO color de fondo */
            color: white;
            padding: 40px 20px;
            margin-top: 80px;
            font-size: 14px;
        }


        .footer-redes {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
            flex-wrap: wrap;
        }

        .iconos-redes a {
            display: inline-block;
            background-color: white;
            color: #444;
            width: 38px;
            height: 38px;
            line-height: 38px;
            margin: 5px;
            border-radius: 50%;
            text-align: center;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .iconos-redes a:hover {
            background-color: #1976d2;
            color: white;
        }

        .footer-contacto {
            text-align: center;
            margin-top: 20px;
            line-height: 1.6;
        }
    </style>
</head>
<body>

    <div class="banner">
        📚 Biblioteca Digital Colegio Amigos de Don Bosco
    </div>

    <div class="contenedor">
        <p>Bienvenido al sistema de consulta y gestión de materiales educativos. Puedes explorar el catálogo o iniciar sesión para realizar préstamos.</p>

        <a href="buscar.jsp" class="boton">🔍 Buscar Material</a>
        <a href="login.jsp" class="boton">🔐 Iniciar Sesión</a>
    </div>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<div class="footer-completo">
    <div class="footer-redes">
        <div>
            <p><strong>Redes sociales Amigos Don Bosco:</strong></p>
            <div class="iconos-redes">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                <a href="#"><i class="fab fa-youtube"></i></a>
            </div>
        </div>

        <div>
            <p><strong>Redes sociales Biblioteca Digital:</strong></p>
            <div class="iconos-redes">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fas fa-envelope"></i></a>
                <a href="#"><i class="fas fa-newspaper"></i></a>
                <a href="#"><i class="fas fa-podcast"></i></a>
                <a href="#"><i class="fas fa-blog"></i></a>
                <a href="#"><i class="fab fa-youtube"></i></a>
            </div>
        </div>
    </div>

    <div class="footer-contacto">
        <p><i class="fas fa-phone"></i> Soyapango: 2251-8215</p>
        <p><i class="fas fa-phone"></i> Antiguo Cuscatlán: 2527-2312</p>
        <p>© 2025 Biblioteca UDB • Creado con JSP y Tomcat</p>
        <p> by Cesar Ernesto Perdomo Guerrero <p>
    </div>
</div>

</body>
</html>
