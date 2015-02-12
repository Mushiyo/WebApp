<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SyntaxHighlighter Easy Tool</title>
	<link rel="stylesheet" href="CSS/site.css">
	<link rel="stylesheet" href="CSS/SynPaste.css">
	<%
		boolean hasOutputCode = request.getAttribute("outputCode") != null;
		if(hasOutputCode){
	%>
		<link href='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/styles/shCore.css' rel='stylesheet' type='text/css' />
		<link href='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/styles/shThemeDefault.css' rel='stylesheet' type='text/css' />
		<script src='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/shCore.js'></script>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/shAutoloader.js'></script>
	<%		
		}
	%>
	
</head>
<body>
	<h3>SyntaxHighlighter Easy Tool</h3>
	v0.875<br>
    A tool built for using
	<a href="http://alexgorbatchev.com/SyntaxHighlighter/">SyntaxHighlighter</a>
	conveniently :)
	<br>
	<br>
	<table>
		<tr>
			<td>請輸入程式碼：</td>
			<td>Output:</td>
		</tr>
		<tr>
			<td>
			<form action="SynPaste" method="post">
				<select name="lan" id="lan">
					<option value="text" selected disabled>please select language (default: plaintext)</option>
					<option value="applescript">applescript</option>
					<option value="as3">actionscript3</option>
					<option value="bash">bash, shell script</option>
					<option value="c">C, C++</option>
					<option value="cf">ColdFusion</option>
					<option value="c#">C#</option>
					<option value="css">CSS</option>
					<option value="delphi">Delphi</option>
					<option value="diff">diff</option>
					<option value="erl">Erlang</option>
					<option value="groovy">Groovy</option>
					<option value="html">HTML, XML, XHTML</option>
					<option value="java">Java</option>
					<option value="jfx">JavaFX</option>
					<option value="js">JavaScript</option>
					<option value="pascal">Pascal</option>
					<option value="perl">Perl</option>
					<option value="php">PHP</option>
					<option value="text">Plain Text</option>
					<option value="ps">PowerShell</option>
					<option value="py">Python</option>
					<option value="ruby">Ruby</option>
					<option value="sass">Sass</option>
					<option value="scala">scala</option>
					<option value="sql">SQL</option>
					<option value="vb">Visual Basic</option>
				</select> <br>
				<%
					if(request.getParameter("code") != null){
				%>
					<textarea name="code" rows="10"><%= request.getParameter("code").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %></textarea>
				<%
					}else{		
				%>
				<textarea name="code" rows="10"></textarea>
				<%
					}		
				%>
				<br>
				其它選項（非必要）：<br>
				code title:<input type="text" name="title"><br>
				first line number: <input type="number" name="lineNum"><br>
				tab size: <input type="number" name="tabSize"> <br>
				highlight line(s) (ex: 1, 3, 5, means highlight line 1, 3 and 5): <input type="text" name="highlightLine"> <br>
				<input type="checkbox" name="option" value="collapse">collapse
				<input type="checkbox" name="option" value="html">html-script
				<input type="checkbox" name="option" value="gutter">no gutter
				<input type="checkbox" name="option" value="autolink">no autolink
				<input type="checkbox" name="option" value="toolbar">no toolbar<br>
				<b>(NOTE: collapse and no toolbar can not be checked at the same time)</b><br>
				<input type="submit" name="submit" value="送出" align="middle">
			</form></td>
			<td>
			<%
				if(hasOutputCode){
			%>
				<textarea rows=20 readonly><%= request.getAttribute("outputCode") %></textarea>
			<%
				}
			%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<br>result：
			<%
				if(hasOutputCode){
					out.print(request.getAttribute("displayCode"));
			%>
			<script>
					//<![CDATA["
					function path() {
						var args = arguments, result = [];
						for (var i = 0; i < args.length; i++)
							result.push(args[i].replace('@', 'http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/'));
						return result;
					};
					SyntaxHighlighter.autoloader.apply(null, path(
							'applescript            @shBrushAppleScript.js',
							'actionscript3 as3      @shBrushAS3.js',
							'bash shell             @shBrushBash.js',
							'coldfusion cf          @shBrushColdFusion.js',
							'cpp c                  @shBrushCpp.js',
							'c# c-sharp csharp      @shBrushCSharp.js',
							'css                    @shBrushCss.js',
							'delphi pascal          @shBrushDelphi.js',
							'diff patch pas         @shBrushDiff.js',
							'erl erlang             @shBrushErlang.js',
							'groovy                 @shBrushGroovy.js',
							'java                   @shBrushJava.js',
							'jfx javafx             @shBrushJavaFX.js',
							'js jscript javascript  @shBrushJScript.js',
							'perl pl                @shBrushPerl.js',
							'php                    @shBrushPhp.js',
							'ps powershell         @shBrushPowerShell.js',
							'text plain             @shBrushPlain.js',
							'py python              @shBrushPython.js',
							'ruby rails ror rb      @shBrushRuby.js',
							'sass scss              @shBrushSass.js',
							'scala                  @shBrushScala.js',
							'sql                    @shBrushSql.js',
							'vb vbnet               @shBrushVb.js',
							'xml xhtml xslt html    @shBrushXml.js'));//]]>
				</script>
				<script>
					SyntaxHighlighter.all();
				</script>
			<%
				}
			%>
			</td>
		</tr>
	</table>
	
	<footer class="sticky">
		<table class="ftTable">
			<tr>
				<td class="ftTdL"><strong><em>SyntaxHighlighter Easy Tool</em></strong> by Mushiyo<br> last update:
					2014.10.1<br>
				</td>
				<td class="ftTdR">
					<a href="./">Home</a>
					| <a href="https://github.com/Mushiyo/WebApp/tree/master/src/synPaste">Source Code</a>
					| <a target="_blank" href="http://mushiyoscomputerworld.blogspot.tw/p/blog-page_1.html">Contact Author</a>
				 </td>
			</tr>
		</table>
	</footer>
</body>
</html>