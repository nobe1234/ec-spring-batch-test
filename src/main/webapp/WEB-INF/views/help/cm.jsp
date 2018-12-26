<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新商品紹介ページ</title>
<!-- <link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/background.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<link rel="stylesheet" type="text/css" href="/path/to/modal-video.css">
<!-- <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="/path/to/modal-video.js"></script> -->
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/showItem/index"> <!-- 企業ロゴ -->
						<img alt="main log" src="../img/header_logo.png" height="35">
					</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="${pageContext.request.contextPath}/showItem/index"
							class="navbar-link">トップページへ</a>&nbsp;&nbsp;
					</p>
				</div>
			</div>
		</nav>

		<!-- 		<button class="js-modal-btn-vimeo" data-video-id="202177974">Vimeoを開く</button> -->
		<!-- 		<iframe id="ytplayer" type="text/html" width="640" height="360"　name=""
			src="http://www.youtube.com/embed/M7lc1UVf-VE?autoplay=1&origin=http://example.com"
			frameborder="0" /> -->

		<div id="player"></div>
		<div id="player"></div>
		<div id="player"></div>
		<script>
			// 2. This code loads the IFrame Player API code asynchronously.
			var tag = document.createElement('script');

			tag.src = "https://www.youtube.com/iframe_api";
			var firstScriptTag = document.getElementsByTagName('script')[0];
			firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

			// 3. This function creates an <iframe> (and YouTube player)
			//    after the API code downloads.
			var player;
			function onYouTubeIframeAPIReady() {
				player = new YT.Player('player', {
					height : '360',
					width : '640',
					videoId : 'sr--GVIoluU',
					events : {
						'onReady' : onPlayerReady,
					/*  'onStateChange' : onPlayerStateChange*/
					}

				});
			}

			// 4. The API will call this function when the video player is ready.
			function onPlayerReady(event) {
				event.target.playVideo();
			}

			// 5. The API calls this function when the player's state changes.
			//    The function indicates that when playing a video (state=1),
			//    the player should play for six seconds and then stop.
			//ビデオは通常再生をする
			/* var done = false;
			function onPlayerStateChange(event) {
				if (event.data == YT.PlayerState.PLAYING && !done) {
					setTimeout(stopVideo, 6000000);
					done = true;
				}
			}
			function stopVideo() {
				player.stopVideo();
			} */
		</script>





	</div>


</body>
</html>