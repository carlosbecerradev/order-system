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
        <nav style="display: flex;width: 100%;justify-content: space-between;align-items: center;
             border-bottom: 1px solid blue;">
            <div>
                <a href="<c:url value='/' />">Home</a>
            </div>
            <div>
                <ul  style="display: flex;width: 100%;justify-content: space-between;">
                    <li> <a href="<c:url value='/catalogo' />">Catalogo</a> </li>
                    <li> <a href="<c:url value='/carrito' />">Carrito</a> </li>
                    <li> <a href="<c:url value='/mis_pedidos' />">Mis pedidos</a> </li> 
                </ul>
            </div>
            <div>
                <security:authorize access="isAnonymous()">
                    <a href="<c:url value='/login' />">Loguearse</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a href="<c:url value='/logout' />" >Cerrar Sesión</a>
                </security:authorize>
            </div>
        </nav>
        <h1>Carrito de Pedidos!</h1>
        <div>
            <a href="<c:url value='/catalogo' />">Seguir Comprando</a>
        </div>
        <div>
            <p style="font-size: 1.5rem;">Monto total: <b style="font-size: 2rem;">S/ ${monto_final}</b></p>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Producto</th>
                    <th>Precio (S/)</th>
                    <th>Cantidad</th>
                    <th>Importe (S/)</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${carrito}" var="card" >
                    <tr>
                        <td>${card.producto.nombre}</td>
                        <td>${card.producto.precio}</td>
                        <td>${card.cantidad}</td>
                        <td>${card.importe}</td>
                        <td>
                            <form post="get" action="/project/carrito_quitar" >
                                <input type="hidden" name="id_producto" value="${card.producto.id_producto}" />
                                <button>Quitar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <form method="get" action="/project/registrar_pedido" >
            <button >Registrar Pedido</button>
        </form>
    </body>
</html>
