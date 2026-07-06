<%@page import="com.tecmilenio.imc.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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
    <title>Histórico IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">App IMC</a>
            <div class="d-flex">
                <a href="calcularIMC.jsp" class="btn btn-outline-light btn-sm me-2">Nuevo Cálculo</a>
                <a href="UsuarioServlet?action=logout" class="btn btn-danger btn-sm">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 class="mb-0">Mi Historial de Mediciones</h4>
                <span class="badge bg-info">Consumiendo REST API</span>
            </div>
            <div class="card-body">
                
                <!-- Tabla donde se inyectarán los datos vía JavaScript -->
                <div class="table-responsive">
                    <table class="table table-hover table-striped" id="tablaHistorico">
                        <thead class="table-dark">
                            <tr>
                                <th>Fecha</th>
                                <th>Peso (kg)</th>
                                <th>IMC</th>
                                <th>Categoría</th>
                            </tr>
                        </thead>
                        <tbody id="cuerpoTabla">
                            <tr>
                                <td colspan="4" class="text-center">Cargando datos desde el servicio REST...</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                
                <div id="mensajeError" class="alert alert-danger d-none mt-3"></div>

            </div>
        </div>
    </div>

    <!-- Script AJAX para consumir el Servicio REST (Requisito de la rúbrica) -->
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const usuarioId = <%= usuario.getId() %>;
            // URL del servicio REST que creamos
            const apiUrl = "api/historico/usuario/" + usuarioId + "/json";
            
            fetch(apiUrl)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('No se encontraron registros o hubo un error en el servidor.');
                    }
                    return response.json();
                })
                .then(data => {
                    const tbody = document.getElementById("cuerpoTabla");
                    tbody.innerHTML = ""; // Limpiar tabla
                    
                    if (data.length === 0) {
                        tbody.innerHTML = "<tr><td colspan='4' class='text-center'>No tienes registros de IMC aún.</td></tr>";
                        return;
                    }
                    
                    // Llenar la tabla con los datos del JSON
                    data.forEach(registro => {
                        // Formatear fecha
                        const fecha = new Date(registro.fecha).toLocaleString();
                        
                        // Asignar color según categoría
                        let colorBadge = "bg-secondary";
                        if(registro.categoria === "Peso normal") colorBadge = "bg-success";
                        else if(registro.categoria === "Sobrepeso") colorBadge = "bg-warning text-dark";
                        else if(registro.categoria === "Obesidad") colorBadge = "bg-danger";
                        else if(registro.categoria === "Bajo peso") colorBadge = "bg-info text-dark";

                        const tr = document.createElement("tr");
                        tr.innerHTML = `
                            <td>${fecha}</td>
                            <td><strong>${registro.peso}</strong></td>
                            <td>${registro.imc}</td>
                            <td><span class="badge ${colorBadge}">${registro.categoria}</span></td>
                        `;
                        tbody.appendChild(tr);
                    });
                })
                .catch(error => {
                    document.getElementById("cuerpoTabla").innerHTML = "";
                    const divError = document.getElementById("mensajeError");
                    divError.classList.remove("d-none");
                    divError.innerText = error.message;
                });
        });
    </script>
</body>
</html>
