package com.bookstore.modelo.VO;

import java.util.Calendar;

public class UsuarioVO {
    // Datos cuenta
    private String nombre_de_usuario;
    private String encrypted_password;
    // Datos personales
    private String nombre;
    private String apellidos;
    private Calendar fecha_de_nacimiento;
    private String email;
    private String direccion_postal;
    private Long numero_de_telefono;
    private String numero_de_cuenta_bancaria;

    public UsuarioVO(String nombre_de_usuario, String encrypted_password, String nombre, String apellidos, Calendar fecha_de_nacimiento, String email, String direccion_postal, Long numero_de_telefono, String numero_de_cuenta_bancaria) {
        this.nombre_de_usuario = nombre_de_usuario;
        this.encrypted_password = encrypted_password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.email = email;
        this.direccion_postal = direccion_postal;
        this.numero_de_telefono = numero_de_telefono;
        this.numero_de_cuenta_bancaria = numero_de_cuenta_bancaria;
    }

    public UsuarioVO(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getNombreDeUsuario() {
        return nombre_de_usuario;
    }

    public void setNombreDeUsuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getEncryptedPassword() {
        return encrypted_password;
    }

    public void setEncryptedPassword(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Calendar getFechaDeNacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFechaDeNacimiento(Calendar fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccionPostal() {
        return direccion_postal;
    }

    public void setDireccionPostal(String direccion_postal) {
        this.direccion_postal = direccion_postal;
    }

    public Long getNumeroDeTelefono() {
        return numero_de_telefono;
    }

    public void setNumeroDeTelefono(Long numero_de_telefono) {
        this.numero_de_telefono = numero_de_telefono;
    }

    public String getNumeroDeCuentaBancaria() {
        return numero_de_cuenta_bancaria;
    }

    public void setNumeroDeCuentaBancaria(String numero_de_cuenta_bancaria) {
        this.numero_de_cuenta_bancaria = numero_de_cuenta_bancaria;
    }
}