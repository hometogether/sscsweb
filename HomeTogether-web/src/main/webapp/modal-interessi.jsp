<%-- 
    Document   : modal-interssi
    Created on : 13-feb-2016, 10.05.35
    Author     : Antonio
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="ejb.Interesse"%>
<%@page import="ejb.Interesse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    <meta charset="utf-8"/>

<html lang="it">

    <script src="js/interessi.js"></script>
    <script type="text/javascript">


        $(document).ready(function () {
            $('#additem').hide();
            if('${profilo.id }'!='${id}'){
                $('#showAddinput').remove();
                $('#additem').remove();
                $('#space').remove();
            }
            $(document).on("click","#showAddinput",function(){
                $('#additem').show();
                $('#showAddinput').hide();
            });
            

        });
        $(document).on("click", "#remove", function () {
            $(this).closest('li').remove();
        });
        $(document).on("click", "#remove", function () {
            $(this).closest('li').remove();
        });
        
    </script>
    <div class="modal fade" id="mod-interessi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header login_modal_header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="myModalLabel" style="color : white">INTERESSI</h2>
                </div>
                <div class="modal-body login-modal">
                    <p>Elenco interessi!</p>
                    <br/>
                    <div class="clearfix"></div>
                    <div>
                        
                        <a class="btn icon-btn btn-success" id="showAddinput">
                        <span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>
                        Add
                        </a>
                        
                        <div id="additem">
                            
                                 <div class="input-group col-lg-6 col-md-6 col-sm-6">
                                    <input type="text" name='nomeinteresse' class="form-control" id="nomeinteresse" placeholder="Nuovo interesse..." style="height: 2%">
                                    <span class="input-group-btn">
                                      <button id="add" class="btn btn-default" type="button" onClick="aggiungiInteresse()">Aggiungi interesse</button>
                                    </span>
                                 </div>
                            
                        </div>
                        
                        <div id='todolist'>
                            <br id="space">
                            <ul id="ulInteressi" class="list-inline"> 
                            <c:forEach items="${profilo.interessi}" var="interesse">
                                <c:if test="${profilo.id == id}">
                                    <li class="col-md-3"id="${interesse.id}">
                                        <div class="btn-group">
                                            <form action="InterestServlet" role="form" method="get">
                                                <div class="btn-interest">
                                                    
                                                    <input type="hidden" name='action' value="goToInterest">
                                                    <input type="hidden" name='nome' value="${interesse.nome}">
                                                    <button class="btn btn-secondary borderless-btn btn-link" title="${interesse.nome}" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 90%;"> ${interesse.nome}</button>
                                                    
                                                    
                                                    <button id="remove" type="button" class="btn btn-secondary close" onClick="rimuoviInteresse(${interesse.id})">&times;</button> 
                                                    
                                                </div>
                                            </form>
                                        </div>
                                    </li>     
                                </c:if>
                                <c:if test="${profilo.id != id}">
                                    <li class="col-md-3"id="${interesse.id}">
                                        <div class="btn-group">
                                            <form action="InterestServlet" role="form" method="get">
                                                <div class="btn-interest">
                                                    
                                                    <input type="hidden" name='action' value="goToInterest">
                                                    <input type="hidden" name='nome' value="${interesse.nome}">
                                                    <button class="btn borderless-btn btn-link" title="${interesse.nome}" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 100%"> ${interesse.nome}</button>
                                                    
                                                </div>
                                            </form>
                                        </div>
                                    </li> 
                                </c:if>
                                
                            </c:forEach>
                            </ul>
                        </div>
                    </div>																												
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer login_modal_footer">
                </div>
            </div>
        </div>
    </div>
</html>
