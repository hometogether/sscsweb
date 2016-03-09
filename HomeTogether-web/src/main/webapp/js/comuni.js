/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var regioni = [];
var xhr = new XMLHttpRequest();
function autocompileRe() {

    xhr.open('POST', 'RegistrationServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var regione = $("#regione").val();
    xhr.onload = function () {
        console.log('torno dalla servlet. Messaggio di ritorno:' + xhr.responseText);
        if (!(xhr.responseText.trim() === "-1")) {
            regioni = xhr.responseText.trim().split("_");
            //mettere il classico $ al posto di jQuery porta ad un conflitto con l'implementazione del modal.
            jQuery("#regione").autocomplete("option", "source", regioni);
        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=autocompileRegione&regione=' + regione);
}

var provincie = [];
var xhr = new XMLHttpRequest();
function autocompilePro() {

    xhr.open('POST', 'RegistrationServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var provincia = $("#provincia").val();
    xhr.onload = function () {
        console.log('torno dalla servlet. Messaggio di ritorno:' + xhr.responseText);
        if (!(xhr.responseText.trim() === "-1")) {
            provincie = xhr.responseText.trim().split("_");
            //mettere il classico $ al posto di jQuery porta ad un conflitto con l'implementazione del modal.
            jQuery("#provincia").autocomplete("option", "source", provincie);
        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=autocompileProvincia&provincia=' + provincia);
}




var comuni = [];
var xhr = new XMLHttpRequest();
function autocompile() {

    xhr.open('POST', 'RegistrationServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var comune = $("#localita").val();
    xhr.onload = function () {
        console.log('torno dalla servlet. Messaggio di ritorno:' + xhr.responseText);
        if (!(xhr.responseText.trim() === "-1")) {
            comuni = xhr.responseText.trim().split("_");
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
            console.log ("nome esistente nel db! Submitto.");
            $("#registrationform").submit();

        } else {
            // GESTIRE ERRORE: INSERITO UN NOME NON ESISTENTE NEL DB!
            console.log ("nome non esistente nel db!");
        }

    };
    xhr.send('action=checkComune&comune=' + comune);
}



jQuery(function () {
    jQuery("#localita").autocomplete({
        source: comuni,
        
    });
    jQuery("#provincia").autocomplete({
        source: provincie,
        
    });
    jQuery("#regione").autocomplete({
        source: regioni,
        
    });
    

});