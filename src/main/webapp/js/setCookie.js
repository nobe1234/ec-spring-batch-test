//$(function() {
//	// テキストエリアの内容をCookieに保存する
//	$("#itemDetail").ready(function() {
//		// テキストエリアの内容を読み込む
//// var name = $(".itemName").val();
////		document.cookie
////		document.cookie = "name=value";
////		var img = $('#imagePath').attr("img");　// valueの取得
//		var imagePath = $('img #imagePath').attr('src');　// valueの取得
//		alert('保存する画像');
//		alert(imagePath);
//		// var img = $(".img-responsive img-rounded").val();
//		// Cookieに保存する
//		$.cookie("myData", escape(imagePath), {
//			expires : 7
//		});
//		// 結果を表示する
//		$("#result").text("Cookieに保存しました");
//	});
//	// Cookieに保存した内容を読み込んで表示する
//	$("#load").ready(function() {
//		// テキストエリアの内容を読み込む
//		var text = $.cookie("myData");
//		// 結果を表示する
//		$("#result").text(unescape(text));
//	});
//});