<%-- 
    Document   : modal-immagineProfilo
    Created on : 13-feb-2016, 10.06.19
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        

    <div class="modal fade" id="avatar-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
            <div class="modal-body" style="text-align: center">
                <img src="${profilo.foto_profilo}" alt="avatar" style="width: 70%"/>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer login_modal_footer">
                </div>
        </div>
        </div>
    </div>
</html>
