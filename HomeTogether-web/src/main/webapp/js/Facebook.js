/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.fbAsyncInit = function() {
              FB.init({
                appId      : '518776728298231',
                xfbml      : true,
                version    : 'v2.5'
              });
              
              FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                  console.log('Logged in Facebook.');
                }
                else {
                  console.log('Not logged in.');
                }
              });
              
            };

            (function(d, s, id){
               var js, fjs = d.getElementsByTagName(s)[0];
               if (d.getElementById(id)) {return;}
               js = d.createElement(s); js.id = id;
               js.src = "//connect.facebook.net/it_IT/sdk.js";
               fjs.parentNode.insertBefore(js, fjs);
             }(document, 'script', 'facebook-jssdk')); 
             
             function myFacebookLogin() {
                FB.login(function(response){
                    var url_basic= '/me?fields=first_name,last_name,email,birthday,gender,location&locale=it_IT';
                    var url_img_profile = '/me/picture?type=normal';    
                    FB.api(url_basic, function(response) {
                        console.log('Successful login for: ' + response.email);
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function() {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                             //document.getElementById("status").innerHTML = xhttp.responseText;
                             var res=xhttp.responseText;
                                var n="no";
                                if(res===n){
                                    jQuery.noConflict();
                                    $('#NotRegModal').modal('show');
                                    $('#login-modal').modal('hide');
                                    $('#div_password').remove();
                                    $('#div_r_password').remove();
                                    $('#idSocial').val(response.id);
                                    $('#nome').val(response.first_name);
                                    $('#cognome').val(response.last_name);
                                    $('#email').val(response.email);
                                    $('#r_email').val(response.email);
                                    $('#foto_profilo').val(photo);
                                    $('#tipo_registrazione').val('1');
                                    $('#localita').val(response.location.name);
                                    
                                    var data_n=String(response.birthday).split('/');

                                    $('#data_nascita').val(data_n[2]+"-"+data_n[0]+"-"+data_n[1]);
                                    var sesso = response.gender;
                                    var m = "uomo";
                                    if (sesso === m) {
                                        console.log(sesso);
                                        $("#sessom").prop("checked", true);
                                    } else {
                                        $("#sessof").prop("checked", true);
                                    }
                                }else{
                                   jQuery.noConflict();
                                    $('#login-modal').modal('hide');
                                    $('#iscriviti').remove();
                                    $('#login-link').remove();
                                    $('#socialForm').submit();
                                }
                            }
                          };
                          var idSocial= response.id;
                          var nome= response.first_name;
                          var cognome= response.last_name;
                          var email= response.email;
                          var data_nascita= response.birthday.toString();
                          var giorno= data_nascita.substring(3,5);
                          var mese= data_nascita.substring(1,2);
                          var anno= data_nascita.substring(6,10);
                          var sesso= response.gender;
                          var localita= response.location.name;
                          var photo="";

                          function sendData(foto) {
                                
                                xhttp.open("POST", "FacebookServlet", true);
                                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                xhttp.send("idSocial="+idSocial+"&nome="+ nome +"&cognome="+ cognome+"&email="+ email+"&giorno="+ giorno+"&mese="+ mese+"&anno="+ anno+"&sesso="+ sesso+"&localita="+localita+"&foto="+ encodeURIComponent(foto));
                                console.log(foto);
                                console.log(encodeURIComponent(foto));
                                photo=foto;
                            }

                            function get_data_user_login(){
                                FB.api(                                                
                                     '/me/picture?type=large',                     
                                      
                                      function(response){   
                                             sendData(response.data.url);
                                       }
                                 );             
                            };

                            get_data_user_login(); 
                            
                           
                          
                      });
                }, {scope: 'public_profile,email,user_friends,user_photos,user_birthday,user_location'}, { auth_type: 'reauthenticate' });
              }
              
              function goToReg(){
                  FB.api('/me?fields=first_name,last_name,email,birthday,gender,location&locale=it_IT',function(response){
                      $('#div_password').remove();
                                $('#div_r_password').remove();
                                $('#nome').val(response.first_name);
                                $('#cognome').val(response.last_name);
                                $('#email').val(response.email);
                                $('#r_email').val(response.email);
                                $('#localita').val(response.location.name);

                                console.log(response.birthday);
                                var data_n=String(response.birthday).split('/');

                                $('#data_nascita').val(data_n[2]+"-"+data_n[1]+"-"+data_n[0]);
                                var sesso = response.gender;
                                var m = "uomo";
                                if (sesso === m) {
                                    console.log(sesso);
                                    $("#sessom").prop("checked", true);
                                } else {
                                    $("#sessof").prop("checked", true);
                                }
                  });
                                
                }
                