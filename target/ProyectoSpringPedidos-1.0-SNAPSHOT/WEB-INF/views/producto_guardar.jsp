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
        <h1>${accion} Producto!</h1>
        <f:form modelAttribute="producto" method="post">
            <div>
                Id: <f:input path="id_producto" tyoe="text" readonly="true" />
            </div>
            <div>
                Nombre: <f:input path="nombre" tyoe="text" />
                <f:errors path="nombre" />
            </div>
            <div>
                Nombre Imagen: <f:input path="nombre_imagen" tyoe="text" />
                <f:errors path="nombre_imagen" />
            </div>
            <div>
                Categoría:
                <f:select path="categoria">
                    <f:option value="">Seleccione</f:option>
                    <f:option value="Whisky">Whisky </f:option>
                    <f:option value="Ron">Ron</f:option>
                    <f:option value="Vodka">Vodka</f:option>
                    <f:option value="Vino">Vino</f:option>
                    <f:option value="Cerveza">Cerveza</f:option>
                    <!-- Agregar más categorias -->
                </f:select>
                <f:errors path="categoria" />
            </div>
            <div>
                Precio: <f:input path="precio" tyoe="number" />
                <f:errors path="precio" />
            </div>
            <div>
                Stock <f:input path="stock" tyoe="number" />
                <f:errors path="stock" />
            </div>
            <div>
                Estado:                 
                <f:select path="estado">
                    <f:option value="1">Disponible</f:option>
                    <f:option value="0">No Disponible</f:option>
                </f:select>
                <f:errors path="estado" />
            </div>
            <button>${accion}</button>
            <a href="<c:url value='/producto/' />">Cancelar</a>
        </f:form>
    </body>
</html>
