<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>無限愛樂電台過去節目曲目查詢</title>
<link rel="stylesheet" href="CSS/site.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({dateFormat: 'yy/mm/dd', minDate: new Date(2000, 3 - 1, 26), maxDate: '0'});
	});
	
	<%
		boolean requested = request.getAttribute("prtUrl") != null;
		if (requested) {
	%>
	
	window.open("<%=request.getAttribute("prtUrl")%>");
	
	// set default value
	$(function() {
		$("#datepicker").datepicker("setDate", "<%=request.getParameter("date")%>");
	});
	$(function() {
	    $("#time").val("<%=request.getParameter("time")%>");
	});
	<%
		}
	%>
</script>

</head>
<body>
	<h3>無限愛樂電台過去節目表查詢</h3>
	<form action="e-classical" method="post">
		日期：<input type="text" name="date" id="datepicker" required>
		時段：<select name="time" id="time">
			<option value="00">00:00</option>
			<option value="01">01:00</option>
			<option value="02">02:00</option>
			<option value="03">03:00</option>
			<option value="04">04:00</option>
			<option value="05">05:00</option>
			<option value="06">06:00</option>
			<option value="07">07:00</option>
			<option value="08">08:00</option>
			<option value="09">09:00</option>
			<option value="10">10:00</option>
			<option value="11">11:00</option>
			<option value="12">12:00</option>
			<option value="13">13:00</option>
			<option value="14">14:00</option>
			<option value="15">15:00</option>
			<option value="16">16:00</option>
			<option value="17">17:00</option>
			<option value="18">18:00</option>
			<option value="19">19:00</option>
			<option value="20">20:00</option>
			<option value="21">21:00</option>
			<option value="22">22:00</option>
			<option value="23">23:00</option>
		</select>
		<input type="submit" name="submit" value="查詢">
	</form>
	<font size="2"><font color="red">*</font>查詢結果可能會被瀏覽器攔截，請設定允許彈出式視窗。</font>

	<br><br>
	<hr>
	<b>說明：</b><br>
	 愛樂電台官網上的即時曲目查詢只能查到過去七天的節目內容。<br>
	 然而，實際上是連去年的內容都可以查到（去年以前至89年3月26日的則是可以看到節目表 ），只是愛樂電台的介面只給你能查七天。<br>
	 本網頁所做的，就只是把介面做出來而已。
	 
	 <footer class="sticky">
		<table class="ftTable">
			<tr>
				<td class="ftTdL"><strong><em>無限愛樂電台過去節目表查詢</em></strong> by Mushiyo<br> last update:
					2014.12.3<br>
				</td>
				<td class="ftTdR">
					<a href="./">Home</a>
					| <a href="https://github.com/Mushiyo/WebApp/tree/master/src/e_classical">Source Code</a>
					| <a target="_blank" href="http://mushiyoscomputerworld.blogspot.tw/p/blog-page_1.html">Contact Author</a>
				 </td>
			</tr>
		</table>
	</footer>
</body>
</html>