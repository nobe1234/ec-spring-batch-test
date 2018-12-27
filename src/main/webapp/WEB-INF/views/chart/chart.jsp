<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- (1) -->
<!-- omitted -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script> -->
<title>グラフ課題</title>
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script> -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
	
</script>

<!-- <sec:csrfMetaTags /> -->
</head>
<body>



	<h1>グラフ</h1>
	<select name="year" id="year" onchange="getGraph(this)">
		<option value="2016">2016</option>
		<option value="2017">2017</option>
		<option value="2018" selected="selected">2018</option>
	</select>

	<canvas id="myLineChart" width="400" height="400"></canvas>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	<script>
		//読み込まれて最初に呼ばれる処理
		$(function() {
			makeGraph(2018);
		});
		
		function getGraph(select) {
			$("#sales").text("");
			// 選択された年を取得する
			var idx = select.selectedIndex;
			year = select.options[idx].value;
			makeGraph(year);
		};

		function makeGraph(year) {
			$.ajax({
				type : "post",
				url : "http://localhost:8080/restResponseChart/post",
				dataType : "json",
				data : {
					year : year
				},
				success : function(data) {
					success(data);
				},
				error : function() {
					alert("失敗");
				}
			});
		};

		function success(data) {
			var monthlySaleList = {
				Jan : data[0],
				Feb : data[1],
				Mar : data[2],
				Apr : data[3],
				May : data[4],
				Jun : data[5],
				Jul : data[6],
				Aug : data[7],
				Sep : data[8],
				Oct : data[9],
				Nov : data[10],
				Dec : data[11]
			};

			var ctx = document.getElementById("myLineChart");

			if (myLineChart) { //sales??
				myLineChart.destroy();
			}

			var myLineChart = new Chart(ctx, {
				type : 'line',
				data : {
					labels : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
							'9月', '10月', '11月', '12月' ],
					datasets : [ {
						/* 実際の売上の値を入れていく */
						label : '９９９９',
						data : [ monthlySaleList.Jan, monthlySaleList.Feb,
								monthlySaleList.Mar, monthlySaleList.Apr,
								monthlySaleList.May, monthlySaleList.Jun,
								monthlySaleList.Jul, monthlySaleList.Aug,
								monthlySaleList.Sep, monthlySaleList.Oct,
								monthlySaleList.Nov, monthlySaleList.Dec ], //ここにマップから取り出したList
						borderColor : "rgba(255,0,0,1)",
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
					}
				}
			});
		};

	</script>
</body>
</html>