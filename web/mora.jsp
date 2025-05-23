<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Mora, java.util.List" %>
<%@ page import="modelo.Usuario" %>

<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null || !"ADMIN".equals(u.getRol().name())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    List<Mora> morasAlumnos = (List<Mora>) request.getAttribute("morasAlumnos");
    List<Mora> morasProfesores = (List<Mora>) request.getAttribute("morasProfesores");
    List<Mora> morasSimuladas = (List<Mora>) session.getAttribute("morasSimuladas");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Moras</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        h2, .section-title { text-align: center; color: #0d47a1; }
        table {
            width: 95%; margin: 20px auto; border-collapse: collapse;
        }
        th, td {
            border: 1px solid #bbb; padding: 8px; text-align: center;
        }
        th { background-color: #0d47a1; color: white; }
        form {
            width: 60%; margin: 20px auto; padding: 15px;
            border: 1px solid #ccc; background: #f5f5f5;
        }
        form input[type="text"], form input[type="number"] {
            padding: 5px; width: 80%;
        }
        form input[type="submit"] {
            padding: 8px 20px; background: #0d47a1; color: white; border: none;
        }
    </style>
</head>
<body>

<h2>💰 Gestión de Moras (Administrador)</h2>

<!-- ✅ Formulario de Simulación -->
<div class="section-title">Simular Mora</div>

<form action="simular-mora" method="post" style="max-width: 500px; margin: 0 auto;">
    <div style="margin-bottom: 15px;">
        <label for="usuario">Usuario:</label>
        <input type="text" name="usuario" id="usuario"
               placeholder="ej: juanperez"
               style="width: 100%; padding: 8px;" required>
    </div>

    <div style="margin-bottom: 15px;">
        <label for="dias">Días de mora:</label>
        <input type="text" name="dias" id="dias"
               placeholder="ej: 5"
               style="width: 100%; padding: 8px;" required>
    </div>

    <div style="margin-bottom: 20px;">
        <label for="ejemplares">Ejemplares prestados:</label>
        <input type="text" name="ejemplares" id="ejemplares"
               placeholder="ej: 2"
               style="width: 100%; padding: 8px;">
    </div>

    <div style="text-align: center;">
        <input type="submit" value="Simular Mora"
               style="background-color: #0d47a1; color: white; border: none;
                      padding: 10px 20px; font-size: 15px; cursor: pointer;">
    </div>
</form>

<!-- ✅ Tabla de Moras Simuladas -->
<% if (morasSimuladas != null && !morasSimuladas.isEmpty()) { %>
    <div class="section-title">Moras Simuladas</div>
    <table>
        <thead>
            <tr>
                <th>Usuario</th>
                <th>Días Mora</th>
                <th>Ejemplares Prestados</th>
                <th>Monto Mora</th>
                <th>Fecha de Cálculo</th>
            </tr>
        </thead>
        <tbody>
        <% for (Mora m : morasSimuladas) { %>
            <tr>
                <td><%= m.getUsuario() %></td>
                <td><%= m.getDiasMora() %></td>
                <td><%= m.getEjemplaresPrestados() %></td>
                <td>$<%= String.format("%.2f", m.getMontoMora()) %></td>
                <td><%= m.getFechaCalculo() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
<% } %>

<!-- ✅ Tabla de Moras - Alumnos -->
<div class="section-title">Moras - Alumnos</div>
<table>
    <thead>
        <tr>
            <th>Usuario</th>
            <th>ID Material</th>
            <th>Días Mora</th>
            <th>Ejemplares Prestados</th>
            <th>Monto Mora</th>
            <th>Fecha de Cálculo</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (morasAlumnos != null && !morasAlumnos.isEmpty()) {
                for (Mora mora : morasAlumnos) {
        %>
            <tr>
                <td><%= mora.getUsuario() %></td>
                <td><%= mora.getIdMaterial() %></td>
                <td><%= mora.getDiasMora() %></td>
                <td><%= mora.getEjemplaresPrestados() %></td>
                <td>$<%= String.format("%.2f", mora.getMontoMora()) %></td>
                <td><%= mora.getFechaCalculo() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="6">Sin moras registradas para alumnos.</td></tr>
        <%
            }
        %>
    </tbody>
</table>

<!-- ✅ Tabla de Moras - Profesores -->
<div class="section-title">Moras - Profesores</div>
<table>
    <thead>
        <tr>
            <th>Usuario</th>
            <th>ID Material</th>
            <th>Días Mora</th>
            <th>Ejemplares Prestados</th>
            <th>Monto Mora</th>
            <th>Fecha de Cálculo</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (morasProfesores != null && !morasProfesores.isEmpty()) {
                for (Mora mora : morasProfesores) {
        %>
            <tr>
                <td><%= mora.getUsuario() %></td>
                <td><%= mora.getIdMaterial() %></td>
                <td><%= mora.getDiasMora() %></td>
                <td><%= mora.getEjemplaresPrestados() %></td>
                <td>$<%= String.format("%.2f", mora.getMontoMora()) %></td>
                <td><%= mora.getFechaCalculo() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="6">Sin moras registradas para profesores.</td></tr>
        <%
            }

        %> 
        
        <% if (morasSimuladas != null && !morasSimuladas.isEmpty()) { %>
            <div style="text-align:center; margin-bottom: 20px;">
                <form action="limpiar-simulacion" method="get">
                <input type="submit" value="🧹 Limpiar Simulaciones"
                   style="padding: 10px 20px; background-color: #b71c1c; color: white; border: none;">
                </form>
            </div>
    
        <% } %>

        
    </tbody>
</table>

</body>
</html>


