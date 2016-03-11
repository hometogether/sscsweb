var lingue = [];
var xhr = new XMLHttpRequest();
function aggiungiLingua() {

    xhr.open('POST', 'LanguageServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var nomelingua = $("#nomelingua").val();
    console.log('nome lingua =' + nomelingua);
    xhr.onload = function () {
        console.log('ohdonpiano');
        if (!(xhr.responseText.trim() === "-1")) {
            var id = xhr.responseText.trim();
            jQuery.noConflict();
            console.log("id lingua:"+id);
            $("#nomelingua").val('');
            var lowerlingua = nomelingua.toLowerCase();
            lowerlingua = lowerlingua.substring(0, 1).toUpperCase() + lowerlingua.substring(1);
            $('#ulLingue').append('<li class="col-md-3"id="'+id+'">'+
                                        '<div class="btn-group">'+
                                                '<div class="btn-interest">'+
                                                    '<button class="btn btn-secondary borderless-btn btn-link" title="'+lowerlingua+'" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 90%;">'+lowerlingua+'</button>'+
                                                    '<button id="remove" type="button" class="btn btn-secondary close" onClick="rimuoviLingua('+id+')">&times;</button> '+
                                                '</div>'+
                                        '</div>'+
                                    '</li>');


        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=add&nomelingua=' + nomelingua);
}

function rimuoviLingua(idlingua) {

    xhr.open('POST', 'LanguageServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        if (xhr.responseText.trim() === "0") {
            jQuery.noConflict();
            console.log("entro nel trim");
            //jQuery.noConflict();
            $("#" + idlingua).remove();



        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=remove&idlingua=' + idlingua);

}

function autocompileLingue() {

    xhr.open('POST', 'LanguageServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var lingua = $("#nomelingua").val();
    xhr.onload = function () {
        console.log('torno dalla servlet. Messaggio di ritorno:' + xhr.responseText);
        if (!(xhr.responseText.trim() === "-1")) {
            lingua = xhr.responseText.trim().split("_");
            //mettere il classico $ al posto di jQuery porta ad un conflitto con l'implementazione del modal.
            jQuery("#nomelingua").autocomplete("option", "source", lingua);
        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=autocompileLingue&lingua=' + lingua);
}

jQuery(function () {
    jQuery("#nomelingua").autocomplete({
        source: lingue,
        zIndex: 5000,
        appendTo: "#mod-lingue"
        
    });

});