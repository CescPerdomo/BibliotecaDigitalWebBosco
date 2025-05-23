
#  Biblioteca Digital Web - Colegio Amigos de Don Bosco

Este es un sistema web completo de gestión de biblioteca, desarrollado con tecnologías Java EE. Permite a los usuarios consultar materiales bibliográficos, realizar préstamos según su rol, simular moras, y a los administradores gestionar usuarios, devoluciones y morosidades.

> Proyecto de Catedra final POO

---

## 🌐 Tecnologías Utilizadas

| Componente               | Tecnología / Herramienta         |
|--------------------------|----------------------------------|
| Lenguaje principal       | Java 8                           |
| Vista                    | JSP + HTML + CSS                |
| Controlador              | Servlets Java                    |
| Modelo                   | JavaBeans (POJO)                 |
| Servidor de Aplicaciones | Apache Tomcat 9.0.105            |
| Base de Datos            | MySQL 8.x                        |
| Conexión BD              | JNDI + Pool de conexiones        |
| IDE                      | NetBeans 8.2                     |
| Logging                  | Log4j                            |

---

## 📁 Estructura del Proyecto

biblioteca-web/
├── build/ # Archivos compilados por NetBeans
├── dist/
│ └── biblioteca-web.war # Archivo WAR generado para Tomcat
├── nbproject/ # Configuración interna de NetBeans
│ ├── project.properties
│ └── private/
├── src/
│ ├── dao/ # Clases DAO (acceso a base de datos)
│ │ ├── AuthDAO.java
│ │ ├── MaterialDAO.java
│ │ ├── MoraDAO.java
│ │ ├── MoraResumenDAO.java
│ │ ├── PrestamoDAO.java
│ │ └── UsuarioDAO.java
│ ├── modelo/ # Clases del modelo
│ │ ├── Material.java
│ │ ├── Mora.java
│ │ ├── Prestamo.java
│ │ ├── Role.java
│ │ └── Usuario.java
│ ├── servlet/ # Servlets del sistema
│ │ ├── LoginServlet.java
│ │ ├── BuscarMaterialServlet.java
│ │ ├── VerDetalleServlet.java
│ │ ├── PrestarMaterialServlet.java
│ │ ├── MisPrestamosServlet.java
│ │ ├── DevolucionesServlet.java
│ │ ├── DevolverPrestamoServlet.java
│ │ ├── RenovarPrestamoServlet.java
│ │ ├── MoraServlet.java
│ │ ├── SimularMoraServlet.java
│ │ ├── UsuariosServlet.java
│ │ ├── EditarUsuarioServlet.java
│ │ ├── EliminarUsuarioServlet.java
│ │ └── ResetearContrasenaServlet.java
│ └── util/
│ └── ConexionBD.java # Conexión vía JNDI (pool Tomcat)
│
├── web/
│ ├── index.jsp # Página de bienvenida
│ ├── login.jsp # Vista de inicio de sesión
│ ├── buscar.jsp # Búsqueda pública de materiales
│ ├── detalle.jsp # Detalle de material + préstamo
│ ├── misPrestamos.jsp # Vista de préstamos personales
│ ├── devoluciones.jsp # Vista de devoluciones / renovaciones
│ ├── mora.jsp # Vista de moras y simulaciones (ADMIN)
│ ├── usuarios.jsp # Vista de gestión de usuarios (ADMIN)
│ ├── editarUsuario.jsp # Edición individual de usuario
│ └── logout.jsp # Cierre de sesión
│
├── web/WEB-INF/
│ ├── web.xml # Configuración de servlets
│ └── context.xml # Pool de conexión a MySQL (JNDI)

---

##  Roles del Sistema

| Rol        | Permisos                                                                 |
|------------|--------------------------------------------------------------------------|
| ALUMNO     | Buscar, prestar hasta 5 materiales, devolver                             |
| PROFESOR   | Buscar, prestar hasta 10 materiales, devolver                            |
| ADMIN      | Acceso total: usuarios, mora, renovaciones, simulaciones, dashboard      |

---

##  Flujo General del Sistema

1. **Búsqueda pública** sin iniciar sesión
2. **Login** con validación de credenciales
3. **Préstamos** limitados por rol
4. **Devoluciones** y renovación con validación de mora
5. **Simulación de mora** visual sin afectar la base
6. **Gestión de usuarios** completa (CRUD) para el ADMIN

---

##  Configuración del Pool de Conexiones (JNDI)

Ubicado en: `web/WEB-INF/context.xml`


<Context>
    <Resource name="jdbc/biblioteca" auth="Container"
              type="javax.sql.DataSource"
              maxTotal="20" maxIdle="10" maxWaitMillis="10000"
              username="root" password="1234"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/biblioteca?useSSL=false"/>
</Context>

# En Pool de ConexionBD se obtiene la conexión así:


Context initContext = new InitialContext();
Context envContext = (Context) initContext.lookup("java:/comp/env");
DataSource ds = (DataSource) envContext.lookup("jdbc/biblioteca");
return ds.getConnection();

# Cómo Ejecutar el Proyecto
Clonar el repositorio:


git clone https://github.com/CescPerdomo/BibliotecaDigitalWebBosco.git

Abrir el proyecto en NetBeans 8.2

Configurar Tomcat 9.0.105 y crear la base de datos biblioteca

Ejecutar el proyecto o generar el .war desde el menú Clean and Build

Desplegar el archivo biblioteca-web.war en:


C:\Apache\Tomcat9\webapps\
Iniciar Tomcat con startup.bat o mediante script personalizado

Acceder vía navegador:


http://localhost:8081/biblioteca-web/

 # Funcionalidades Avanzadas
 Validación de login por rol

 Préstamos controlados (5 ALUMNO / 10 PROFESOR)

 Control de mora automática

 Simulación de mora visual (ADMIN)

 Gestión total de usuarios (ADMIN)

 Renovación de préstamos si no hay mora

 Autor
CESAR ERNESTO PERDOMO GUERRERO
Carnet: PG241690
## Appendix

Any additional information goes here

