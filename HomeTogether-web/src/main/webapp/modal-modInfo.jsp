<%-- 
    Document   : modal-modInfo
    Created on : 13-feb-2016, 10.06.41
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <div class="modal fade" id="mod-info-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
            <div class="modal-content">
                    <div class="modal-header login_modal_header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title" id="myModalLabel" style="color : white">MODIFICA LE INFORMAZIONI SU DI TE</h2>
                    </div>
                    <form action="ProfileServlet" role="form" method="POST">
                        <div class="modal-body">
                            <div class="input-group col-md-8 col-xs-8 col-sm-8">
                                <span class="input-group-addon">Località attuale</span><input class="form-control" type="text" id="localita" name="localita" placeholder="Dove vivi?"/>
                            </div>
                            <br>
                            <div class="input-group col-md-8 col-xs-8 col-sm-8">
                                <span class="input-group-addon">Data di nascita</span><input class="form-control" type="date" id="data_nascita" name="data_nascita" placeholder="Quando sei nato?"/>
                            </div>
                            <br>
                            <div class="input-group col-md-8 col-xs-8 col-sm-8">
                                <span class="input-group-addon">Formazione</span><input class="form-control" type="text" id="formazione" name="formazione" placeholder="Qual è la tua formazione?"/>
                        </div>
                            <br>
                            <div class="input-group col-md-8 col-xs-8 col-sm-8">
                                <span class="input-group-addon">Occupazione</span><input class="form-control" type="text" id="occupazione" name="occupazione" placeholder="Dove lavori?"/>
                            </div>
                            <br>
                            <div class="input-group col-md-8 col-xs-8 col-sm-8">
                                <span class="input-group-addon">Numero di telefono</span><input class="form-control" type="tel" id="telefono" name="telefono" placeholder="Qual è il tuo numero di telefono?"/>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="modal-footer login_modal_footer">
                            <input type="hidden" name="action" value="mod_info">
                            <button type="submit" class="btn btn-primary form-group" >Conferma</button>
                        </div>
                    </form>

            </div>
            </div>
        </div>
</html>
