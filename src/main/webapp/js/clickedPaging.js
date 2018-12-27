$(function() {
	// var pageNumber = $('#pageNumber').val();
	// var pageNumber = $(#);
	// alert("ロードした時のページ番号：" + pageNumber); // なぜか１０が送られない

	// page読み込み時に今いるページへのリンクボタンを非活性化したい。
	// $('#pageNumber').val() = 10;
	// alert("代入できているチェック：" + $("[name='pageNumber']").val()); // なぜか１０が送られない

	// まず最初のロード時は１番目のページ番号をおふにしておく。
	// $('#pageOneLink').prop("disabled", true).css({
	// 'background-color' : 'gray',
	// 'color' : 'white'
	// });

	alert(getParam('pageNumber'));
	var pageNumber = getParam('pageNumber');
	/**
	 * Get the URL parameter value
	 * 
	 * @param name
	 *            {string} パラメータのキー文字列
	 * @return url {url} 対象のURL文字列（任意）
	 */
	function getParam(name, url) {
		if (!url)
			url = window.location.href;
		name = name.replace(/[\[\]]/g, "\\$&");
		var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
				.exec(url);
		if (!results)
			return null;
		if (!results[2])
			return '';
		return decodeURIComponent(results[2].replace(/\+/g, " "));
	}

	if (pageNumber == 0 || pageNumber == null) {
		$('#pageOneLink').prop("disabled", true).css({
			'background-color' : 'gray',
			'color' : 'white'
		});
		$('#pageTwoLink').prop("disabled", false);
	} else if (pageNumber == 10) {
		$('#pageOneLink').prop('disabled', false);
		$('#pageTwoLink').prop('disabled', true).css({
			'background-color' : 'gray',
			'color' : 'white'
		});
	}

	$('#pageOneLink').on('click', function() {
		$('#page1Form').submit();
		// pageNumber = $("#pageNumber1").val();
		// alert(pageNumber);
		// onLoad();
	});

	$('#pageTwoLink').on('click', function() {
		$('#page2Form').submit();
		// pageNumber = $("#pageNumber2").val();
		// // 0が入る
		// alert("クリックした時のページ番号：" + pageNumber);
		// onLoad();
	});
});

// $(function() {
// $('#pageOneLink').submit(function() {
// $('#page1Form').submit();
// $('#pageOneLink').prop('disabled', true);
// $('#pageTwoLink').prop('disabled', false);
// });
//
// $('#pageTwoLink').on('click', function() {
// var clickValue = $('#pageTwoLink').val();
// alert(clickValue);
// $('#page2Form').submit();
// $('#pageTwoLink').prop('disabled', true);
// $('#pageOneLink').prop('disabled', false);
// // document.pageForm.submit();
//
// });
//
// });
// うまく動かない

//
// let pageOne = document.getElementById('#pageOneLink');
// let pagetwo = document.getElementById('#pageTwoLink');
//
// let pageForm = document.getElementById('#pageForm');
//
// //pageFormがあるかをかくにんする。
// if(page1Form){
// pageForm.addEventListener('click', (evt) => {
// pageOne.disabled = true;
// pagetwo.disabled = false
// }, false);
// }

// pageForm.addEventListener('click', (evt) => {
// pageOne.disabled = false;
// pagetwo.disabled = true;
// }, false);
