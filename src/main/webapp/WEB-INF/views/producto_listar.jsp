<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- librería Tag Security -->
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>td,th{padding: .5rem;border: 1px solid blue;}</style>
    </head>
    <body>
        <ul  style="display: flex;width: 100%;justify-content: space-between;padding-left: 0;
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
        <h1>Listado de Productos!</h1>
        <div>
            <a href="<c:url value='/producto/guardar' />">Nuevo Registro</a>
        </div>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Imagen</th>
                    <th>Categoría</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productos}" var="card" >
                <tr>
                    <td>${card.id_producto}</td>
                    <td>${card.nombre}</td>
                    <td>
                        <img src="<c:url value='/resources/uploads/${card.nombre_imagen}' />" width="150px" />                        
                    </td>
                    <td>${card.categoria}</td>
                    <td>${card.precio}</td>
                    <td>${card.stock}</td>
                    <td>
                        <c:if test="${card.estado}">Disponible</c:if>
                        <c:if test="${!card.estado}">No Disponible</c:if>
                    </td>
                    <td>
                        <a href="<c:url value='/producto/editar/${card.id_producto}' />">Editar</a> | 
                        <a href="<c:url value='/producto/eliminar/${card.id_producto}'/>"                           
                              onclick='return confirm("¿Estas seguro de eliminar ${card.nombre}?")'>Eliminar</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
