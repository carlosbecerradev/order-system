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

        <h1>Reporte de Pedidos!</h1>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>DNI</th>
                    <th>Monto Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pedidos}" var="card" >
                    <tr>
                        <td>${card.id_pedido}</td>
                        <td>${card.fecha}</td>
                        <td>${card.cliente.nombres} ${card.cliente.apellidos}</td>
                        <td>${card.cliente.dni}</td>
                        <td>S/ ${card.monto_total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div>
            <p>RECAUDADO: <span style="color: blue; font-size: 2rem;">S/ ${recaudado}</span></p>
        </div>
    </body>
</html>
