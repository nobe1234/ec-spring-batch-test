$(function(){
	$("#cashOnDelivery").change(function(){
		$("#creditCardNumber").css("display","none");
	});
	$("#creditCard").change(function(){
		$("#creditCardNumber").css("display","block");
	});
	
	$(".creditCertification").on("click",function(){
		if($('#creditNumber').val()=="" ||!$("#creditNumber").val().match(/^\d+$/)){
			alert("カード番号を入力してください");
		} else {
			alert("クレジットカードを認証しました！");
		}
	});
	
	$("#btnSubmit").on("click",function(){
		$("#nowProcessing").css("display","block");
	})
	
});