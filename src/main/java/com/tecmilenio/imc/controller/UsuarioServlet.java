package com.tecmilenio.imc.controller;

import com.tecmilenio.imc.dao.UsuarioDAO;
import com.tecmilenio.imc.model.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("registrar".equals(action)) {
            registrarUsuario(request, response);
        } else if ("login".equals(action)) {
            loginUsuario(request, response);
        } else if ("logout".equals(action)) {
            logoutUsuario(request, response);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(request.getParameter("nombreCompleto"));
            usuario.setNombreUsuario(request.getParameter("nombreUsuario"));
            usuario.setContrasena(request.getParameter("contrasena"));
            usuario.setEdad(Integer.parseInt(request.getParameter("edad")));
            usuario.setSexo(request.getParameter("sexo"));
            usuario.setEstatura(Double.parseDouble(request.getParameter("estatura")));
            usuario.setTelefono(request.getParameter("telefono"));
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setDireccion(request.getParameter("direccion"));

            if (!usuario.esValido()) {
                request.setAttribute("error", "Datos inválidos. Recuerda: Edad >= 15 y Estatura entre 1.0 y 2.5m");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            if (usuarioDAO.registrar(usuario)) {
                response.sendRedirect("login.jsp?msg=registrado");
            } else {
                request.setAttribute("error", "Error al registrar. El usuario o correo ya existe.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error en el formato de los datos.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("nombreUsuario");
        String pass = request.getParameter("contrasena");

        Usuario usuario = usuarioDAO.autenticar(user, pass);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("calcularIMC.jsp");
        } else {
            request.setAttribute("error", "Credenciales incorrectas.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logoutUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            logoutUsuario(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
