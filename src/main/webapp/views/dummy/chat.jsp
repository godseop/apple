<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script type="text/javascript">
    const SERVER_SOCKET_API = "/apple/websockethandler";


    var stompClient = new StompJs.Client({
        brokerURL: "ws://localhost:8080/ws",
        connectHeaders: buildHeaders(),
        debug: function(str) {
            console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });


    $(function() {
        $("#txtChatInput").on("keydown", function (e) {
            if (e.keyCode === 13) {
                console.log($(this).val());
                sendMessage($(this).val());
                $(this).val("");
            }
        });

        stompClient.webSocketFactory = function () {
            return new SockJS(SERVER_SOCKET_API);
        };


        stompClient.onConnect = function (frame) {
            // Do something, all subscribes must be done is this callback
            // This is needed because this will be executed after a (re)connect
            stompClient.subscribe("/chat/roomId", function(message) {
                console.log(message);
                printMessage(JSON.parse(message.body));
            });
        };

        stompClient.onStompError = function (frame) {
            // Will be invoked in case of error encountered at Broker
            // Bad login/passcode typically will cause an error
            // Complaint brokers will set `message` header with a brief message. Body may contain details.
            // Compliant brokers will terminate the connection after any error
            console.log('Broker reported error: ' + frame.headers['message']);
            console.log('Additional details: ' + frame.body);
        };

        stompClient.activate();

    });

    function printMessage(message) {
        console.log(message);
        $("#divChatOutput").append(
            $("#chat-template").render(message)
        );
    }

    function sendMessage(message) {
        stompClient.publish({
            destination: "/chat/roomId",
            body: JSON.stringify(({content: message})),
        });
    }

    </script>

    <script id="chat-template" type="text/x-handlebars-template">
        <span>{{list.content}}</span><br/>
    </script>
</head>
<body>
    <div id="divChatOutput">
        <span>웹소켓 채팅샘플입니다</span><br/>
        <span>어서오세요</span><br/>
    </div>
    <input type="text" id="txtChatInput"/>
</body>
</html>