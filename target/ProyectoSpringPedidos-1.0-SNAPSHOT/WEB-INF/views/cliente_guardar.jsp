<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- librería Tag Security -->
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul style="display: flex;width: 100%;justify-content: space-between;padding-left: 0;
            border-bottom: 1px solid blue; padding-bottom: 1rem;" >
            <li>Navegación de Administrador</li>
            <li><a href="<c:url value='/cliente/' />" >Gestión Cliente</a></li>
            <li><a href="<c:url value='/producto/' />" >Gestión Producto</a></li>
            <li><a href="<c:url value='/reporte' />" >Reporte</a></li>
            <li>
                <security:authorize access="isAnonymous()">
                    <a href="<c:url value='/login' />">Loguearse</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a href="<c:url value='/logout' />" >Cerrar Sesión</a>
                </security:authorize>
            </li>
        </ul>
            
        <h1>${accion} Cliente!</h1>
        
        <f:form modelAttribute="cliente" method="post">
            <div>
                Id: <f:input path="id_cliente" tyoe="text" readonly="true" />
            </div>
            <div>
                Nombres: <f:input path="nombres" tyoe="text" />
                <f:errors path="nombres" />
            </div>
            <div>
                Apellidos: <f:input path="apellidos" tyoe="text" />
                <f:errors path="apellidos" />
            </div>
            <div>
                Correo: <f:input path="correo" tyoe="text" />
                <f:errors path="correo" />
            </div>
            <div>
                DNI: <f:input path="dni" tyoe="number" />
                <f:errors path="dni" />
            </div>
            <div>
                Sexo: 
                <f:select path="sexo">
                    <f:option value="">Seleccione</f:option>
                    <f:option value="M">Masculino </f:option>
                    <f:option value="F">Femenino</f:option>
                </f:select>
                <f:errors path="sexo" />
            </div>
                <f:input type="hidden" path="usuario.id_usuario" />
            <div>
                Usuario: <f:input path="usuario.username" tyoe="text" />
                <f:errors path="usuario.username" />
            </div>
            <div>
                Contraseña: <f:input path="usuario.contrasenia" tyoe="password" />
                <f:errors path="usuario.contrasenia" />
            </div>
            <button>${accion}</button>
            <a href="<c:url value='/cliente/' />">Cancelar</a>
        </f:form>
    </body>
</html>
