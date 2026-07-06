<%@page import="com.tecmilenio.imc.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Verificación de sesión
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcular IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">App IMC</a>
            <div class="d-flex">
                <span class="navbar-text me-3 text-white">Hola, <%= usuario.getNombreCompleto() %></span>
                <a href="historico.jsp" class="btn btn-outline-light btn-sm me-2">Ver Histórico</a>
                <a href="UsuarioServlet?action=logout" class="btn btn-danger btn-sm">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-white">
                        <h4 class="text-center mb-0">Calcular mi IMC Actual</h4>
                    </div>
                    <div class="card-body p-4">
                        
                        <div class="alert alert-info text-center">
                            Tu estatura registrada es: <strong><%= usuario.getEstatura() %> m</strong>
                        </div>

                        <% if(request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                        <% } %>

                        <form action="IMCServlet" method="POST">
                            <div class="mb-4">
                                <label class="form-label">Ingresa tu peso actual (en kilogramos):</label>
                                <input type="number" step="0.1" min="1" class="form-control form-control-lg text-center" name="peso" placeholder="Ej: 75.5" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg w-100">Calcular IMC</button>
                        </form>

                        <%-- Sección de Resultados (Solo se muestra si hay cálculo) --%>
                        <% if(request.getAttribute("resultadoIMC") != null) { %>
                            <hr class="my-4">
                            <div class="text-center">
                                <h5>Resultado:</h5>
                                <h1 class="display-4 fw-bold text-primary"><%= request.getAttribute("resultadoIMC") %></h1>
                                <h4 class="text-secondary"><%= request.getAttribute("categoriaIMC") %></h4>
                                <p class="text-muted mt-2">Peso ingresado: <%= request.getAttribute("pesoIngresado") %> kg</p>
                                <a href="historico.jsp" class="btn btn-success mt-2">Ver en mi Histórico</a>
                            </div>
                        <% } %>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
