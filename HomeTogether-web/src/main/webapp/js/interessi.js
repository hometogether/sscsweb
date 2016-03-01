var xhr = new XMLHttpRequest();
function aggiungiInteresse() {

    xhr.open('POST', 'InterestServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var nomeinteresse = $("#nomeinteresse").val();
    console.log('nome interesse =' + nomeinteresse);
    xhr.onload = function () {
        console.log('ohdonpiano');
        if (!(xhr.responseText.trim() === "-1")) {
            var id = xhr.responseText.trim();
            jQuery.noConflict();
            console.log("entro nel trim");
            //jQuery.noConflict();
            $("#nomeinteresse").val('');
            var lowerinteressi = nomeinteresse.toLowerCase();
            $('#ulInteressi').append('<li class="col-md-3" id="'+id+'"><div class="btn-group">'+
                                            
                                                '<div class="btn-interest">'+
                                                    '<form action="InterestServlet" role="form" method="post">'+
                                                    '<input type="hidden" name="action" value="goToInterest">'+
                                                    '<input type="hidden" name="nome" value="'+lowerinteressi+'">'+
                                                    '<button class="btn btn-secondary borderless-btn btn-link" title="'+lowerinteressi+'" style="text-align: center;color: black;overflow: hidden;text-overflow: ellipsis;max-width: 90%;">' +lowerinteressi+'</button>'+
                                                    '</form>'+
                                                    '<form action="InterestServlet" role="form" method="post">'+
                                                    '<button id="remove" type="button" class="btn btn-secondary close" )">&times;</button>'+
                                                    '</form>'+
                                                '</div>'+
                                            
                                        '</div></li>');


        } else {
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=add&nomeinteresse=' + nomeinteresse);
}

function rimuoviInteresse(idinteresse) {

    xhr.open('POST', 'InterestServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        console.log('ohdonpiano');
        if (xhr.responseText.trim() === "0") {
            jQuery.noConflict();
            console.log("entro nel trim");
            //jQuery.noConflict();
            $("#" + idinteresse).remove();



        } /*else {
         // $('#googleForm').submit();
         }*/

    };
    xhr.send('action=remove&idinteresse=' + idinteresse);

}