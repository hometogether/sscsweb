var xhr = new XMLHttpRequest();
var loginFinished = function (authResult) {
    if (authResult) {

        if (authResult['access_token']) {
            var url = 'https://www.googleapis.com/plus/v1/people/me?access_token=' + authResult['access_token'];
            $.getJSON(url, function (data) {
                console.log("Cognome:" + data['name']['familyName']);
                console.log("Nome:" + data['name']['givenName']);
                console.log("Sesso:" + data['gender']);
                console.log("Email:" + data['emails']['0']['value']);
                console.log("data_nascita:" + data['birthday']);
                console.log("foto_profilo:" + data['image']['url']);
                console.log("id:" + data['id']);

                var id_token = authResult['id_token'];
                var id = data['id'];
                var email = data['emails']['0']['value'];
                var idgoogle = data['id'];
                xhr.open('POST', 'GoogleServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    console.log('Signed in as: ' + xhr.responseText);
                    if (xhr.responseText.trim() === "0") {
                        jQuery.noConflict();
                        $('#login-modal').modal('hide');
                        $('#NotRegModal').modal('show');
                        if (String(data['name']['givenName']) !== "undefined") {
                            $("#nome").val(data['name']['givenName']);
                        }
                        if (String(data['name']['familyName']) !== "undefined") {
                            $("#cognome").val(data['name']['familyName']);
                        }

                        if (String(data['gender']) !== "undefined") {

                            if (String(data['gender'] === "male")) {
                                console.log("entro in maschio!");
                                $("#sessom").prop("checked", true);
                            } else {
                                $("#sessof").prop("checked", true);
                            }
                        }
                        if (String(data['emails']) !== "undefined" && String(data['emails']['0']['value']) !== "undefined") {
                            $("#email").val(data['emails']['0']['value']);
                            $("#r_email").val(data['emails']['0']['value']);
                        }
                        if (String(data['placesLived']) !== "undefined" && String(data['placesLived']['0']['value']) !== "undefined") {
                            $("#localita").val(data['placesLived']['0']['value']);

                        }

                        if (String(data['birthday']) !== "undefined") {
                            $("#data_nascita").val(data['birthday']);
                        }

                        $('#foto_profilo').val(data['image']['url']);

                        $('#tipo_registrazione').val('2');
                        $('#idSocial').val(id);

                        $("#div_password").remove();
                        $("#div_r_password").remove();

                    } else if (xhr.responseText.trim() === "1"){
                        $('#socialForm').submit();
                    }

                };
                xhr.send('action=loginGoogle&token=' + id_token + '&id=' + id + '&email=' + email + '&idgoogle=' + idgoogle);
            });
        }

    } else {
        document.getElementById('oauth2-results').innerHTML =
                'Empty authResult';
    }
};
                                                