package com.tecmilenio.imc.dao;

import com.tecmilenio.imc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombreCompleto, nombreUsuario, contraseña, edad, sexo, estatura, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getContrasena()); // En un entorno real esto debería estar encriptado (ej. BCrypt)
            stmt.setInt(4, usuario.getEdad());
            stmt.setString(5, usuario.getSexo());
            stmt.setDouble(6, usuario.getEstatura());
            stmt.setString(7, usuario.getTelefono());
            stmt.setString(8, usuario.getCorreo());
            stmt.setString(9, usuario.getDireccion());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario autenticar(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
        Usuario usuario = null;
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombreCompleto(rs.getString("nombreCompleto"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setEdad(rs.getInt("edad"));
                    usuario.setSexo(rs.getString("sexo"));
                    usuario.setEstatura(rs.getDouble("estatura"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
