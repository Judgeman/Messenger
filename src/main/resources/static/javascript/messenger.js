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
        var sender = $("#sender").val();
        stompClient.subscribe('/messages/receive/' + sender, function (message) {
            var message = JSON.parse(message.body);
            addNewMessage(message.sender, message.text);
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
        $("#messages").append("<tr><td colspan='2'>Verbindung nicht aufgebaut!</td></tr>");
        return;
    }
    stompClient.send("/app/messages", {}, JSON.stringify({'sender': $("#sender").val(), 'receiver': $("#receiver").val(), 'text': $("#message").val()}));
}

function addNewMessage(name, message) {
    $("#messages").append("<tr><td>" + name + ": </td><td>" + message + "</td></tr>");
}

function loadAllMessages() {
    if (stompClient == null) {
       $("#messages").append("<tr><td colspan='2'>Verbindung nicht aufgebaut!</td></tr>");
       return;
    }

    $.get( "/messages/all", function(messages) {
        $("#messages").html("");

        for (const message of messages) {
            addNewMessage(message.sender, message.text);
        }
    });
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
    $( "#loadAll" ).click(function() { loadAllMessages(); });
});