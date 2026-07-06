package com.tecmilenio.imc.controller;

import com.tecmilenio.imc.dao.RegistroIMCDAO;
import com.tecmilenio.imc.model.CategoriaIMC;
import com.tecmilenio.imc.model.IMCCalculador;
import com.tecmilenio.imc.model.RegistroIMC;
import com.tecmilenio.imc.model.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IMCServlet", urlPatterns = {"/IMCServlet"})
public class IMCServlet extends HttpServlet {

    private RegistroIMCDAO registroDAO = new RegistroIMCDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        try {
            double peso = Double.parseDouble(request.getParameter("peso"));
            
            if (peso <= 0) {
                request.setAttribute("error", "El peso debe ser mayor a 0.");
                request.getRequestDispatcher("calcularIMC.jsp").forward(request, response);
                return;
            }

            // Calcular IMC usando la clase de lógica de negocio
            double imcCalculado = IMCCalculador.calcular(peso, usuario.getEstatura());
            CategoriaIMC categoria = CategoriaIMC.obtenerCategoria(imcCalculado);

            // Guardar registro
            RegistroIMC registro = new RegistroIMC(usuario.getId(), imcCalculado, peso, categoria.getDescripcion());
            registroDAO.guardar(registro);

            // Pasar datos a la vista
            request.setAttribute("resultadoIMC", imcCalculado);
            request.setAttribute("categoriaIMC", categoria.getDescripcion());
            request.setAttribute("pesoIngresado", peso);
            
            request.getRequestDispatcher("calcularIMC.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Por favor ingresa un peso válido.");
            request.getRequestDispatcher("calcularIMC.jsp").forward(request, response);
        }
    }
}
