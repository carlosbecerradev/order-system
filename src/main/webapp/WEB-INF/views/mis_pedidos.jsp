<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- librería Tag Security -->
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            b{margin-bottom: .2rem; display: block;}
            td, th {padding: .2rem .5rem; border: 1px solid black;}
        </style>
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
        <h1>Mis pedidos!</h1>
        <div>
            <c:forEach items="${mis_pedidos}" var="card" >
                <div  style="display: flex; justify-content: space-between; border: 1px solid blue;
                      padding: .5rem; margin: .5rem; align-items: center;text-align: center;">
                    <div>
                        <b>PEDIDO EL</b>
                        <b> ${card.fecha}</b>
                    </div>
                    <div>                        
                        <b>Detalle del pedido:</b>

                        <table>
                            <thead>
                                <tr>
                                    <th>Producto:</th>
                                    <th>Precio:</th>
                                    <th>Cantidad:</th>
                                    <th>Importe:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${mis_detalles_pedidos}" var="dt" >

                                    <c:if test="${dt.pedido.id_pedido == card.id_pedido}">

                                        <tr>
                                            <td>${dt.producto.nombre}</td>
                                            <td>S/ ${dt.producto.precio}</td>
                                            <td> ${dt.cantidad}</td>
                                            <td>S/ ${dt.importe}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div>
                        <b>Monto Total: </b>
                        <b style="color: blue; font-size: 2rem;">S/ ${card.monto_total}</b>
                        <div>
                            <a href="<c:url value='/cancelar_pedido/${card.id_pedido}' />"
                               style="color: red;padding-top: .5rem"
                               onclick='return confirm("¿Estas seguro de eliminar este pedido?")'>
                                Cancelar
                            </a>    
                        </div>
                        
                    </div>
                </div>
            </c:forEach>
        </div>

    </body>
</html>
