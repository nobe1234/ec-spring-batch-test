<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新商品紹介ページ</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- google map api の読み込み　-->
<script
	src="http://maps.google.com/maps/api/js?key=AIzaSyCPf8hSiGkp9kp2dWNE1YRfItypWdKNjXI&language=ja"></script>
<style>
html {
	height: 100%
}

body {
	height: 100%
}

#map {
	height: 70%;
	width: 70%
}
</style>

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


	<div id="map"></div>
	<script>
		var MyLatLng = new google.maps.LatLng(35.689766, 139.704101);
		var Options = {
			zoom : 16, //地図の縮尺値
			center : MyLatLng, //地図の中心座標
			mapTypeId : 'roadmap' //地図の種類
		};
		var map = new google.maps.Map(document.getElementById('map'), Options);

		/* マーカー */
		var marker = new google.maps.Marker({
			position : MyLatLng,
			map : map,
			title : 'ラクスパートナーズ本社',
			icon : new google.maps.MarkerImage('/img/piza.jpg',//マーカー画像URL
			new google.maps.Size(80, 80),//マーカー画像のサイズ
			new google.maps.Point(40, 60),//マーカー画像表示の起点（変更しない）
			new google.maps.Point(30, 80)//マーカー位置の調整
			)
		});
	</script>





</body>
</html>