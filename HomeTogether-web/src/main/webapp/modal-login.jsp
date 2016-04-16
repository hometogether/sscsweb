<%-- 
    Document   : modal-login
    Created on : 13-feb-2016, 10.05.59
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header login_modal_header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="myModalLabel" style="color : white">Accedi col tuo Account</h2>
                </div>
                <div class="modal-body login-modal">
                    <p>HomeTogether è il miglior social per l'house sharing. È  al 100% gratuito e lo sarà sempre</p>
                    <br/>
                    <div class="clearfix"></div>
                    <div id='social-icons-conatainer'>
                        <form action="LoginServlet"  role="form" method="post">
                            <input type="hidden" name="action" value="login">

                            <div class='modal-body-left'>
                                <div class="form-group">
                                    <input type="text" name="email" placeholder="Inserisci la tua email" value="" class="form-control login-field">
                                    <i class="fa fa-user login-field-icon"></i>
                                </div>

                                <div class="form-group">
                                    <input type="password" name="password" id="login-pass" placeholder="Password" value="" class="form-control login-field">
                                    <i class="fa fa-lock login-field-icon"></i>
                                </div>

                                <button type="submit" class="btn btn-success modal-login-btn">Login</a>
                                    
                            </div>
                        </form>

                        <div class='modal-body-right'>
                            <div class="modal-social-icons">
                                <a href="#" class="btn btn-default facebook" onclick="myFacebookLogin()"> <i class="fa fa-facebook modal-icons"></i> Accedi con Facebook </a>
                                <div class="g-signin btn btn-default google" data-callback="loginFinished"
                                     data-approvalprompt="force"
                                     data-clientid="914513079502-evc4u51vs3mijtfebssfqr0mjpk7cs2l.apps.googleusercontent.com"
                                     data-requestvisibleactions="http://schemas.google.com/CommentActivity"
                                     data-cookiepolicy="single_host_origin"
                                     data-scope="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email" >
                                    <i class="fa fa-google-plus modal-icons"></i> Accedi con Google
                                </div>


                            </div> 
                        </div>	
                        <div id='center-line'> O </div>
                    </div>																												
                    <div class="clearfix"></div>

                    <div class="form-group modal-register-btn">
                        
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer login_modal_footer">
                </div>
            </div>
        </div>
    </div>


    <div id="NotRegModal" class="modal fade">
        <div class="modal-dialog">
            <div class="alert alert-warning">
                <!-- dialog body -->
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <strong>OPS! </strong>Non risulti iscritto al sito, per favore iscriviti.
                </div>
                <!-- dialog buttons -->
                <div class="modal-footer"><a href="#iscriviti" data-dismiss="modal" class="btn scroll">Iscriviti</a></div>
            </div>
        </div>
    </div>
</html>
