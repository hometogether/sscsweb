<%-- 
    Document   : error
    Created on : 18-mar-2016, 17.57.33
    Author     : Andrea22
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <body>
        <br/><br/><br/><br/>
        <c:if test="${warning != null}"> 

        <div class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attenzione!</strong> ${warning}
        </div>
        </c:if>
        <c:if test="${danger != null}"> 

        <div class="alert alert-danger">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Errore!</strong> ${danger}
        </div> 
        </c:if>
        <c:if test="${success != null}"> 

        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Successo!</strong> ${success}
        </div>
        </c:if>
    </body>
</html>
