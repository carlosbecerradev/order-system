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
                
        <security:authorize access="isAuthenticated()">
            <security:authentication var="username" 
                                 property="principal.username"/>
            <h1>Bienvenido ${username}!</h1>
            <!-- Si tiene el rol ADMIN-->
            <security:authorize access="hasRole('ADMIN')">
                <a href="<c:url value='/cliente/' />" >Redireccionar</a>        
            </security:authorize>
            
        </security:authorize>
        <!-- Si no hay nadie logueado -->
        <c:if test="${username == null}"><h1>Bienvenido Cliente!!!</h1></c:if>
        <h2>Sistema de pedidos</h2>
        <h3>Recuerde! Debe iniciar sesión para realizar sus pedidos.</h3>
    </body>
</html>
