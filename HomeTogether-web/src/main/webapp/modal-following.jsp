<%-- 
    Document   : modal-following
    Created on : 17-feb-2016, 17.25.15
    Author     : Andrea22
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="ejb.Interesse"%>
<%@page import="ejb.Interesse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    <meta charset="utf-8"/>

<html lang="it">


    
    <div class="modal fade" id="following-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header login_modal_header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="myModalLabel" style="color : white">Lista Following</h2>
                </div>
                <div class="modal-body login-modal">
                        
                    <ul id="ulFollowing" class="list-inline">                 
                    <c:forEach items="${profilo.following}" var="utente">
                        <li class="list-group-item col-md-4 col-sm-4 col-lg-4 " style="text-align: center; border: transparent; margin: 0% 0% 0% 32%;">
                            <form action="RedirectServlet" role="form" method="get">    
                                <input type="hidden" name="action" value="goUserProfile">
                                <input type="hidden" name="idprofile" value="${utente.id}">
                                <button class="borderless-btn btn-link" style="color: #000"><span class="pull-xs-right"><img src="${utente.foto_profilo}" class="avatar img-circle" style="box-shadow: 0px 0px 2px orangered; " alt="avatar" height='40px' width='40px'/></span>
                                    ${utente.nome}<span> </span>${utente.cognome}</button>
                            </form>
                        </li>
                    </c:forEach>
                    </ul>
                        																									
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer login_modal_footer">
                </div>
            </div>
        </div>
    </div>
</html>





