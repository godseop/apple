<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script type="text/javascript">
    $(function() {

        var video = videojs("vdoTest", {
            controls: true,
            liveui: true,
            preload: "auto",
            width: 640,
            height: 480,
            poster: "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
        });

        // 로딩스피너 제거
        video.removeChild("LoadingSpinner");

        video.src({
            // src: "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8",
            // src: "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
            src: "https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8",
            // src: "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8",
            // src: "http://www.streambox.fr/playlists/test_001/stream.m3u8",
            type: "application/x-mpegURL",
        });

        video.play();

    });
    </script>
</head>
<body>
    <video id="vdoTest" class="video-js"></video>
</body>
</html>