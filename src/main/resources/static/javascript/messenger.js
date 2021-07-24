var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/register');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/messages/receive', function (message) {
            var message = JSON.parse(message.body);
            addNewMessage(message.name, message.text);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    if (stompClient == null) {
        $("#messages").append("<tr><td>Verbindung nicht aufgebaut!</td></tr>");
        return;
    }
    stompClient.send("/app/messages", {}, JSON.stringify({'name': $("#name").val(), 'text': $("#message").val()}));
}

function addNewMessage(name, message) {
    $("#messages").append("<tr><td>" + name + " schrieb: " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});