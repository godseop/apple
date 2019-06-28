<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
    $(function() {
        WebSocket.init();
    });

    let WebSocket = (function() {
        const SERVER_SOCKET_API = "/websockethandler";

        let stompClient;

        function connect() {
            let socket = new SockJS(SERVER_SOCKET_API);
            stompClient = Stompjs.Stomp.over(socket);
            stompClient.connect({}, function() {
                stompClient.subscribe("/chat/roomId", function(message) {
                    printMessage(JSON.parse(message.body));
                });
            });
        }

        function printMessage(message) {
            $("#txaChatOutput").append(message);
        }

        function sendMessage(message) {
            stompClient.send("/apple/chat", {}, JSON.stringify({content: message}));
        }

        function init() {
            connect();
            $("#txtChatInput").on("keydown", function(e) {
                if (e.keyCode === 13) {
                    console.log(this.val());
                    sendMessage(this.val());
                    this.val("");
                }
            });
        }

        return {
            init: init
        }
    })();
    </script>
</head>
<body>
    <!-- CONTENTS HERE -->
    <textarea id="txaChatOutput" rows="24"></textarea>
    <input type="text" id="txtChatInput"/>
</body>
</html>