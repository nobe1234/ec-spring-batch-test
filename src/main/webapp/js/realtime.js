$(function() {
	$("#total-price").text("この商品の金額:" + String(parseInt($(".priceM").text().replace(/,/, ""))).replace(/(\d)(?=(\d\d\d)+$)/g, '$1,') + "円(税抜)");
	$(".size").change(function() {
		calc();
	});
	$(".toppings").change(function() {
		calc();
	});
	$("#quantity").change(function() {
		calc();
	});
	function calc() {
		var size = $(".size:checked").val();
		var price_M = parseInt($(".priceM").text().replace(/,/, ""));
		var price_L = parseInt($(".priceL").text().replace(/,/, ""));
		var total_price;
		// toppingチェックボックスの選択されている数を変数に格納
		var cnt = $(".toppings:checked").length;
		// 数量を取得
		var quantity = $("#quantity").val();
		// 各値を計算
		if ($(".size:checked").val() == 'M') {
			total_price = (price_M * quantity) + (200 * cnt);
		} else if ($(".size:checked").val() == 'L') {
			total_price = (price_L * quantity) + (300 * cnt);
		}
		total_price = String(total_price).replace(/(\d)(?=(\d\d\d)+$)/g, '$1,');
		// 合計金額の書き換え
		$("#total-price").text("この商品の金額:" + total_price + "円(税抜)");
	};
});
