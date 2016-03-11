<%-- 
    Document   : modal-interssi
    Created on : 13-feb-2016, 10.05.35
    Author     : Antonio
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="ejb.Lingua"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    <meta charset="utf-8"/>

<html lang="it">

    <script src="js/lingue.js"></script>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
    <script type="text/javascript">


        $(document).ready(function () {
            $('#additemlang').hide();
            if('${profilo.id }'!='${id}'){
                $('#showAddinputlang').remove();
                $('#additemlang').remove();
                $('#spacelang').remove();
            }
            $(document).on("click","#showAddinputlang",function(){
                $('#additemlang').show();
                $('#showAddinputlang').hide();
            });
            

        });
        $(document).on("click", "#remove", function () {
            $(this).closest('li').remove();
        });
        $(document).on("click", "#remove", function () {
            $(this).closest('li').remove();
        });
        
    </script>
    <div class="modal fade" id="mod-lingue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header login_modal_header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="myModalLabel" style="color : white">LINGUE</h2>
                </div>
                <div class="modal-body login-modal">
                    <p>Elenco lingue parlate</p>
                    <br/>
                    <div class="clearfix"></div>
                    <div>
                        
                        <a class="btn icon-btn btn-success" id="showAddinputlang">
                        <span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>
                        Add
                        </a>
                        
                        <div id="additemlang">
                            
                                 <div class="input-group col-lg-6 col-md-6 col-sm-6">
                                    <input type="text" name='nomelingua' class="form-control" id="nomelingua" onkeyup="autocompileLingue()" placeholder="Inserisci Lingua">
                                    <span class="input-group-btn">
                                      <button id="add" class="btn btn-default" type="button" onClick="aggiungiLingua()">Aggiungi Lingua</button>
                                    </span>
                                 </div>
                            
                        </div>
                        
                        <div id='todolist'>
                            <br id="spacelang">
                            <ul id="ulLingue" class="list-inline"> 
                            <c:forEach items="${profilo.lingue}" var="lingua">
                                <c:if test="${profilo.id == id}">
                                    <li class="col-md-3"id="${lingua.id}">
                                        <div class="btn-group">
                                                <div class="btn-interest">
                                                    <button class="btn btn-secondary borderless-btn btn-link" title="${lingua.nome}" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 90%;"> ${lingua.nome}</button>
                                                    <button id="remove" type="button" class="btn btn-secondary close" onClick="rimuoviLingua(${lingua.id})">&times;</button> 
                                                </div>
                                        </div>
                                    </li>     
                                </c:if>
                                <c:if test="${profilo.id != id}">
                                    <li class="col-md-3"id="${lingua.id}">
                                        <div class="btn-group">
                                                <div class="btn-interest">
                                                    <button class="btn btn-secondary borderless-btn btn-link" title="${lingua.nome}" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 90%;"> ${lingua.nome}</button>
                                                </div>
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
