<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Calculadora IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .login-container { max-width: 400px; margin-top: 10vh; }
    </style>
</head>
<body>
    <div class="container login-container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center">
                <h3>Calculadora IMC</h3>
            </div>
            <div class="card-body p-4">
                <h5 class="card-title text-center mb-4">Iniciar Sesión</h5>
                
                <% if(request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                
                <% if(request.getParameter("msg") != null && request.getParameter("msg").equals("registrado")) { %>
                    <div class="alert alert-success">¡Registro exitoso! Ahora puedes iniciar sesión.</div>
                <% } %>

                <form action="UsuarioServlet" method="POST">
                    <input type="hidden" name="action" value="login">
                    
                    <div class="mb-3">
                        <label for="nombreUsuario" class="form-label">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="contrasena" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100 mb-3">Entrar</button>
                    
                    <div class="text-center">
                        <a href="registro.jsp" class="text-decoration-none">¿No tienes cuenta? Regístrate aquí</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
