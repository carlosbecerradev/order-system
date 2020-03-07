<!-- libreria JSTL Core -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Hello World!</h3>
                        
        <form name="" method="post" action="/project/login">
            
            <!-- token de seguridad -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            
            <!-- si existe el cierre de sesión -->
            <c:if test="${param.logout != null}">
                <p style="color:green">
                    Usted ha cerrado sesión!
                </p>
            </c:if>
            
            <!-- si existe error de logeo -->
            <c:if test="${param.error != null}">
                <p style="color:red">
                    Error, username y/o password incorrecto!
                </p>
            </c:if>
            
            Username: <input type="text" name="username"/> <br>
            Password: <input type="password" name="contrasenia"/> <br><br>
            
            <button type="submit">Iniciar sesión</button>
            <a href="<c:url value='/' />">Cancelar</a>
        </form>
    </body>
</html>
