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
        <h1>Listado de Clientes!</h1>
        <div>
            <a href="<c:url value='/cliente/guardar' />">Nuevo Registro</a>
        </div>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Correo</th>
                    <th>dni</th>
                    <th>sexo</th>
                    <th>usuario</th>
                    <th>contraseña</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${clientes}" var="card" >
                <tr>
                    <td>${card.id_cliente}</td>
                    <td>${card.nombres}</td>
                    <td>${card.apellidos}</td>
                    <td>${card.correo}</td>
                    <td>${card.dni}</td>
                    <td>
                        <c:if test="${card.sexo eq 'M'.charAt(0) }">Masculino</c:if>
                        <c:if test="${card.sexo eq 'F'.charAt(0) }">Femenino</c:if>
                    </td>
                    <td>${card.usuario.username}</td>
                    <td>${card.usuario.contrasenia}</td>
                    <td>
                        <a href="<c:url value='/cliente/editar/${card.id_cliente}' />">Editar</a> | 
                        <a href="<c:url value='/cliente/eliminar/${card.id_cliente}'/>"                           
                              onclick='return confirm("¿Estas seguro de eliminar a ${card.nombres} ${card.apellidos} ?")'>Eliminar</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
