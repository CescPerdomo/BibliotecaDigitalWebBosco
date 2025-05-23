package modelo;

public class Usuario {
    private int id;
    private String usuario;
    private String contrasena;
    private Role rol;
    private int ejemplaresPrestados; // en Java usamos camelCase

    public Usuario() {}

    public Usuario(int id, String usuario, String contrasena, Role rol, int ejemplaresPrestados) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", rol=" + rol +
                ", ejemplaresPrestados=" + ejemplaresPrestados +
                '}';
    }
}


