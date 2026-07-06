<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Calculadora IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .register-container { max-width: 600px; margin-top: 5vh; margin-bottom: 5vh; }
    </style>
</head>
<body>
    <div class="container register-container">
        <div class="card shadow">
            <div class="card-header bg-success text-white text-center">
                <h3>Crear Cuenta</h3>
            </div>
            <div class="card-body p-4">
                
                <% if(request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>

                <form action="UsuarioServlet" method="POST">
                    <input type="hidden" name="action" value="registrar">
                    
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Nombre Completo</label>
                            <input type="text" class="form-control" name="nombreCompleto" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Nombre de Usuario</label>
                            <input type="text" class="form-control" name="nombreUsuario" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="password" class="form-control" name="contrasena" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Correo Electrónico</label>
                            <input type="email" class="form-control" name="correo" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label class="form-label">Edad (Mín. 15)</label>
                            <input type="number" class="form-control" name="edad" min="15" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="form-label">Sexo</label>
                            <select class="form-select" name="sexo" required>
                                <option value="M">Masculino</option>
                                <option value="F">Femenino</option>
                            </select>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="form-label">Estatura (m)</label>
                            <input type="number" step="0.01" min="1.00" max="2.50" class="form-control" name="estatura" placeholder="Ej: 1.75" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label class="form-label">Teléfono</label>
                            <input type="text" class="form-control" name="telefono" required>
                        </div>
                        <div class="col-md-8 mb-3">
                            <label class="form-label">Dirección</label>
                            <input type="text" class="form-control" name="direccion" required>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-success w-100 mb-3">Registrarse</button>
                    
                    <div class="text-center">
                        <a href="login.jsp" class="text-decoration-none">¿Ya tienes cuenta? Inicia sesión aquí</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
