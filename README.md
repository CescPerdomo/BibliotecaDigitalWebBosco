
#  Biblioteca Digital Web - Colegio Amigos de Don Bosco

Este es un sistema web completo de gestión de biblioteca, desarrollado con tecnologías Java EE. Permite a los usuarios consultar materiales bibliográficos, realizar préstamos según su rol, simular moras, y a los administradores gestionar usuarios, devoluciones y morosidades.

> Proyecto de Catedra final POO

---

##  Tecnologías Utilizadas

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

##  Estructura del Proyecto



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

