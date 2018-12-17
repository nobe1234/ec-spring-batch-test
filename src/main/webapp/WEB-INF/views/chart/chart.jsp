<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script> -->
<title>グラフ課題</title>
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script> -->
</head>
<body>

	<h1>グラフ</h1>
	<canvas id="myLineChart" width="400" height="400"></canvas>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

	<script>
		var ctx = document.getElementById("myLineChart");

		/* IDをチェックするロジック */
		var ctx = document.getElementById("myLineChart");
		if (document.getElementById('myLineChart') === null) {
			alert('Does not exist!');
		} else {
			alert('Exist!');
		}

		var myLineChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				datasets : [ /* {
								label : '最高気温(度）',
								data : [ 35, 34, 37, 35, 34, 35, 34, 25 ],
								borderColor : "rgba(255,0,0,1)",
								backgroundColor : "rgba(0,0,0,0)"
							}, */{
					label : '○○年の売上',
					data : [ 5347, 4456, 3337, 1234, 9987, 9933, 2646, 3238,
							6598, 1117, 1648, 5547, ],
					borderColor : "rgba(0,0,255,1)",
					backgroundColor : "rgba(0,0,0,0)"
				} ],
			},
			options : {
				title : {
					display : true,
					text : '年間売上'
				},
				scales : {
					yAxes : [ {
						ticks : {
							suggestedMax : 15000,
							suggestedMin : 0,
							stepSize : 5000,
							callback : function(value, index, values) {
								return '¥' + value //TODO:円マーク　カンマ区切り
							}
						}
					} ]
				},
			}
		});
	</script>

</body>
</html>