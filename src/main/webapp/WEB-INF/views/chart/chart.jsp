<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script> -->
<title>グラフ課題</title>
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script> -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(function () {
    $("#selectChart").change(function() {
        $.ajax({
            type: "POST",
            url: "select",
            data: "select1=" + $("#select1").val(),
            dataType: "text",
            success: function(msg) {
                $("#select2").html(msg);
            }
        });
    });
});
	
</head>
<body>



	<h1>グラフ</h1>
	<!-- 年数の取り出し -->
	<%-- 	<form:form action="" modelAttribute="chartRequestForm">
		<form:select items="${yearMap}">
		</form:select>
	</form:form> --%>
	<select id="selectChart">
		<option value="2016">2016</option>
		<option value="2017">2017</option>
		<option value="2018">2018</option>
	</select>

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

		/* 		var saleList = ${saleList};
		 var monthlySale = null;
		
		 saleList.forEach(function(sale) {
		 console.log(sale.MonthlySales);
		 monthlySale = ${sale.MonthlySales};
		 }); */

/* 		var saleList = '${saleList}';
		var monthlySale = null;
		Array.from(saleList).forEach(function(sale) {
			MonthlySales = parseInt(sale.getAttribute("${MonthlySales}"));
		}); */

		var myLineChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				datasets : [
						{
							/* 実際の売上の値を入れていく */
							label : '実際の売上',
							data : ${monthlySaleList}, //ここを合わせる　一度年単位だけでつくってみる
							borderColor : "rgba(255,0,0,1)",
							backgroundColor : "rgba(0,0,0,0)"
						},
						{
							label : '○○年の売上予想',
							data : ${yearAndMonthlySaleMap},
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