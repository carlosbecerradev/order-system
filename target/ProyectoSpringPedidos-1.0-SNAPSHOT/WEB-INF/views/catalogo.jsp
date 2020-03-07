<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- librería Tag Security -->
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Catalogo de Productos!</h1>
        <div style="display:flex;" >
                    <hr>
            <c:forEach items="${productos}" var="card" >
                <c:if test="${card.estado}">
                    <div>
                        <form method="get" action="/project/carrito_agregar"  >
                            <div>
                                <img src="<c:url value='/resources/uploads/${card.nombre_imagen}' />" width="150px" />
                            </div>
                            <div>
                                <h3>${card.nombre}</h3>
                                <p>${card.categoria}</p>
                                <p>S/ ${card.precio}</p>
                            </div>
                            <div>
                                <input type="hidden" value="${card.id_producto}" name="id_producto" />
                                <input type="number" name="cantidad" value="0" min="0" />
                                <button>Añadir a carrito</button>
                            </div>
                        </form>
                    </div>
                    <hr>
                </c:if>
            </c:forEach>
        </div>
    </body>
</html>
