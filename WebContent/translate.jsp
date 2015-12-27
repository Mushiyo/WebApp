<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多譯 | multiple machine translation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>多譯 | multiple machine translation</h3>
		<br>
		<form action="translate" method="post">
			<div class="row">
				<div class="form-group col-md-5">
					<label for="sourceLan">來源語言</label> <select class="form-control"
						name="sourceLan" id="sourceLan">
						<option value="zh-tw">繁體中文</option>
						<option value="en">英文</option>
						<option value="ja">日文</option>
						<option value="zh-cn">簡體中文</option>
					</select>
				</div>

				<button type="submit" class="col-md-2 btn btn-primary">翻譯</button>

				<div class="form-group col-md-5">
					<label for="targetLan">目標語言</label> <select class="form-control"
						name="targetLan" id="targetLan">
						<option value="zh-tw">繁體中文（Yandex.Translate不支援）</option>
						<option value="en">英文</option>
						<option value="ja">日文</option>
						<option value="zh-cn">簡體中文</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<textarea class="form-control" name="textToTrans" placeholder="在此輸入文字" rows="10"><%=request.getAttribute("textToTrans") == null ? "" : request.getAttribute("textToTrans")%></textarea>
				</div>
				<div class="col-md-6">
					<div class="col-md-12">
						<b>Google Translate</b>
						<div class="panel panel-default">
							<div class="panel-body"><a target="_blank" href="<%=request.getAttribute("gTranslate") == null ? "" : request.getAttribute("gTranslate")%>"><%=request.getAttribute("gTranslate") == null ? "" : request.getAttribute("gTranslate")%></a></div>
						</div>
					</div>
					<div class="col-md-12">
						<label for="ydTranslate"><a target="_blank" href="<%=request.getAttribute("ydTranslateUrl") == null ? "" : request.getAttribute("ydTranslateUrl")%>">Yandex.Translate</a></label>
						<textarea class="form-control" id="ydTranslate" readonly><%=request.getAttribute("ydTranslate") == null ? "" : request.getAttribute("ydTranslate")%></textarea>
					</div>
					<div class="col-md-12">
						<label for="msTranslator"><a target="_blank" href="<%=request.getAttribute("bTranslatorUrl") == null ? "" : request.getAttribute("bTranslatorUrl")%>">Microsoft Translator</a></label>
						<textarea class="form-control" id="msTranslator" readonly><%=request.getAttribute("msTranslator") == null ? "" : request.getAttribute("msTranslator")%></textarea>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			Powered by <a href="http://translate.yandex.com/.">Yandex.Translate</a>
		</div>
	</div>

</body>
</html>