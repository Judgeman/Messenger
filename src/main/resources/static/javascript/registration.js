function createNewUser() {

    var registrationData = JSON.stringify({'emailAddress': $("#newUserEmail").val(),
                                           'name': $("#newUserName").val(),
                                           'password': $("#newUserPassword").val()});

    $.ajax({
        type: "POST",
        url: "/registration",
        data: registrationData,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            200: function () {
                console.log('New user created');
                $("#newUserServerResponse").html("Neuer Benutzer wurde angelegt");
            },
            409: function () {
                console.log('Email address is already in use');
                $("#newUserServerResponse").html("Email-Adresse wird bereits verwendet");
            }
        }
    });
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $( "#createNewUser" ).click(function() { createNewUser(); });
});