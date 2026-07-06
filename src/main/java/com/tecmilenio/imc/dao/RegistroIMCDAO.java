package com.tecmilenio.imc.dao;

import com.tecmilenio.imc.model.RegistroIMC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroIMCDAO {

    public boolean guardar(RegistroIMC registro) {
        String sql = "INSERT INTO registros_imc (usuario_id, imc, peso, categoria) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registro.getUsuarioId());
            stmt.setDouble(2, registro.getImc());
            stmt.setDouble(3, registro.getPeso());
            stmt.setString(4, registro.getCategoria());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RegistroIMC> obtenerHistorico(int usuarioId) {
        List<RegistroIMC> historico = new ArrayList<>();
        String sql = "SELECT * FROM registros_imc WHERE usuario_id = ? ORDER BY fecha DESC";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, usuarioId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RegistroIMC registro = new RegistroIMC();
                    registro.setId(rs.getInt("id"));
                    registro.setUsuarioId(rs.getInt("usuario_id"));
                    registro.setImc(rs.getDouble("imc"));
                    registro.setPeso(rs.getDouble("peso"));
                    registro.setCategoria(rs.getString("categoria"));
                    registro.setFecha(rs.getTimestamp("fecha"));
                    historico.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico;
    }
    
    public double obtenerUltimoPeso(int usuarioId) {
        String sql = "SELECT peso FROM registros_imc WHERE usuario_id = ? ORDER BY fecha DESC LIMIT 1";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, usuarioId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("peso");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
