$(function() {

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
	});

	$('#pageTwoLink').on('click', function() {
		$('#page2Form').submit();
	});
});
