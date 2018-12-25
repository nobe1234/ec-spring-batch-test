$(function() {
	$('#pageOneLink').submit(function() {
		$('#page1Form').submit();
		$('#pageOneLink').prop('disabled', true);
		$('#pageTwoLink').prop('disabled', false);
	});

	$('#pageTwoLink').on('click', function() {
		var clickValue = $('#pageTwoLink').val();
		alert(clickValue);
		$('#page2Form').submit();
		$('#pageTwoLink').prop('disabled', true);
		$('#pageOneLink').prop('disabled', false);
		// document.pageForm.submit();

	});

});
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
