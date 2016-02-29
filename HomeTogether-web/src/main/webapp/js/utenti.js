/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showResult() {
    var xhr = new XMLHttpRequest();
    var utente = $("#ric_utente").val();
    xhr.open("POST", "NavBarServlet", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send('action=searchUtente&ric_utente=' + utente);
    xhr.onload = function () {
       $("#ric_utente").val(utente);
       console.log(xhr.responseText); 
    };
    
}


