/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var comuni = [];
var xhr = new XMLHttpRequest();
function autocompile() {

    xhr.open('POST', 'RegistrationServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var comune = $("#localita").val();
    console.log("prima dell'onload:" + comune);
    xhr.onload = function () {
        if (!(xhr.responseText.trim() === "-1")) {
            console.log("dopo l'onload");
            comuni = xhr.responseText.trim().split("_");

            console.log("comuni:" + comuni);
            //mettere il classico $ al posto di jQuery porta ad un conflitto con l'implementazione del modal.
            jQuery("#localita").autocomplete("option", "source", comuni);

        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=autocompileComune&comune=' + comune);
}


function checkComune() {

    xhr.open('POST', 'RegistrationServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var comune = $("#localita").val();
    xhr.onload = function () {
        console.log('torno dalla servlet. Messaggio di ritorno:' + xhr.responseText);
        if (xhr.responseText.trim() === "0") {
            console.log("nome esistente nel db! Submitto.");
            $("#registrationform").submit();

        } else {
            // GESTIRE ERRORE: INSERITO UN NOME NON ESISTENTE NEL DB!
            console.log("nome non esistente nel db!");
        }

    };
    xhr.send('action=checkComune&comune=' + comune);
}



jQuery(function () {
    jQuery("#localita").autocomplete({
        source: comuni,
    });


});