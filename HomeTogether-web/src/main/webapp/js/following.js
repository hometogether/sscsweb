var xhr = new XMLHttpRequest();
function follow(id) {

    xhr.open('POST', 'ProfileServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function () {
        console.log('ohdonpiano');
        if (xhr.responseText.trim() === "0") {
            console.log("sto seguendo l'utente!");
            
            $("#followbuton"+id).html('Stop Follow');
            $("#followbuton"+id).attr("onclick","eliminafollow("+id+")");
            
        } else {
            console.log("errore!");
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=follow&id=' + id);
}

function eliminafollow(id) {

    xhr.open('POST', 'ProfileServlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function () {
        console.log('ohdonpiano/2');
        if (xhr.responseText.trim() === "0") {
            console.log("ho eliminato l'utente!");
            
            $("#followbuton"+id).html('Follow');
            $("#followbuton"+id).attr("onclick","follow("+id+")");
            
        } else {
            console.log("errore!");
            // GESTIRE ERRORE
        }

    };
    xhr.send('action=eliminafollow&id=' + id);
}