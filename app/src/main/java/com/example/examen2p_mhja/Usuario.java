package com.example.examen2p_mhja;

public class Usuario {
    int UsuarioID;
    String Nombre, Usuario, Password, Genero, Escolaridad, Habilidades;

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String password, String genero, String escolaridad, String habilidades) {
        Nombre = nombre;
        Usuario = usuario;
        Password = password;
        Genero = genero;
        Escolaridad = escolaridad;
        Habilidades = habilidades;
    }

    public Usuario(int usuarioID, String nombre, String usuario, String password, String genero, String escolaridad, String habilidades) {
        UsuarioID = usuarioID;
        Nombre = nombre;
        Usuario = usuario;
        Password = password;
        Genero = genero;
        Escolaridad = escolaridad;
        Habilidades = habilidades;
    }

    public boolean isNull(){
        if(Nombre.isEmpty()&&Usuario.isEmpty()&&Password.isEmpty()&&Genero.isEmpty()&&Escolaridad.equals("Licenciatura")&&Habilidades.isEmpty()){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "UsuarioID=" + UsuarioID +
                ", Nombre='" + Nombre + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                ", Genero='" + Genero + '\'' +
                ", Escolaridad='" + Escolaridad + '\'' +
                ", Habilidades='" + Habilidades + '\'' +
                '}';
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getEscolaridad() {
        return Escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        Escolaridad = escolaridad;
    }

    public String getHabilidades() {
        return Habilidades;
    }

    public void setHabilidades(String habilidades) {
        Habilidades = habilidades;
    }
}
