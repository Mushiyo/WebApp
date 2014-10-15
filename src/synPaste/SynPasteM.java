package synPaste;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SynPasteM
 */
@WebServlet("/SynPasteM")
public class SynPasteM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Map<String, String> setting;
	static {		
		setting = new HashMap<String, String>();
		setting.put("collapse", "; collapse: true");
		setting.put("html", "; html-script: true");
		setting.put("gutter", "; gutter: false");
		setting.put("autolink", "; auto-links: false");
		setting.put("toolbar", "; toolbar: false");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SynPasteM() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static void printInputForm(PrintWriter out) {
		out.println("<h3>SyntaxHighlighter Easy Tool</h3>v0.75<br>");
		out.println("A tool built for using <a href=\"http://alexgorbatchev.com/SyntaxHighlighter/\">SyntaxHighlighter</a> conveniently :) <br>");
		out.println("請輸入程式碼");
		out.println("<form action=\"SynPasteM\" method=\"post\">"
				+ "<select name=\"lan\" id=\"lan\">"
				+ "<option value=\"text\" selected disabled>請選擇語言</option>"
				+ "<option value=\"java\">Java</option>"
				+ "<option value=\"groovy\">Groovy</option>"
				+ "<option value=\"c\">C, C++</option>"
				+ "<option value=\"py\">Python</option>"
				+ "<option value=\"bash\">bash, shell script</option>"
				+ "<option value=\"html\">HTML, XML, XHTML</option>"
				+ "<option value=\"css\">CSS</option>"
				+ "<option value=\"js\">Javascript</option>"
				+ "<option value=\"sql\">SQL</option>"
				+ "<option value=\"php\">PHP</option>"
				+ "<option value=\"text\">Plain Text</option>"
				+ "</select>"
				+ "<br>"
				+ "<textarea name=\"code\" rows=\"10\" cols=\"100\"></textarea>"
				+ "<br>"
				+ "其它選項（非必要）：<br>"
				+ "code title:<input type=\"text\" name=\"title\"> "
				+ "first line number:<input type=\"number\" name=\"lineNum\"> "
				+ "tab size:<input type=\"number\" name=\"tabSize\">"
				+ "<br>"
				+ "highlight line(s) (ex: 1, 3, 5, means highlight line 1, 3 and 5):<input type=\"text\" name=\"highlightLine\">"
				+ "<br>"
				+ "<input type=\"checkbox\" name=\"option\" value=\"collapse\">collapse"
				+ "<input type=\"checkbox\" name=\"option\" value=\"html\">html-script"
				+ "<input type=\"checkbox\" name=\"option\" value=\"gutter\">no gutter"
				+ "<input type=\"checkbox\" name=\"option\" value=\"autolink\">no autolink"
				+ "<input type=\"checkbox\" name=\"option\" value=\"toolbar\">no toolbar"
				+ "<br>"
				+ "<b>(NOTE: collapse and no toolbar can not be checked at the same time)</b>"
				+ "<br>"
				+ "<input type=\"submit\" name=\"submit\" value=\"送出\" align=\"middle\">"
				+ "</form>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>SyntaxHighlighter Easy Tool</title>");
		out.println("</head>");
		out.println("<body>");
		printInputForm(out);
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String lan = "" + request.getParameter("lan");
		StringBuilder outputCode = new StringBuilder("<pre class=\"brush: ");
		if (lan.equals("null")) {
			outputCode.append("text");
		} else {
			outputCode.append(lan);
		}

		String firstLine = request.getParameter("lineNum");
		if (!firstLine.equals("")) {
			outputCode.append("; first-line: " + firstLine);
		}

		String tabSize = request.getParameter("tabSize");
		if (!tabSize.equals("")) {
			outputCode.append("; tab-size: " + tabSize);
		}

		String[] options = request.getParameterValues("option");
		boolean isCollapse = false;
		//boolean isHtmlScript = false;
		boolean noToolbar = false;
		if (options != null) {
			for (int i = 0; i < options.length; ++i) {
				outputCode.append(setting.get(options[i]));
				if (options[i].equals("collapse")) {
					isCollapse = true;
				} else if (options[i].equals("html")) {
					outputCode.insert(0, "<script type=\"text/javascript\" src=\"http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/shBrushXml.js\"></script>\n");
				}
				else if(options[i].equals("toolbar")){
					noToolbar = true;
				}
			}
		}
		
		String highlightLine = request.getParameter("highlightLine");
		if(!highlightLine.equals("")){
			outputCode.append("; highlight: [" + highlightLine + "]");
		}

		String title = request.getParameter("title");
		if (!title.equals("")) {
			outputCode.append("\" title=\"" + title + "\">\n");
		} else {
			outputCode.append("\">\n");
		}

		String inputCode = request.getParameter("code").replaceAll("<", "&amp;lt;")
				.replaceAll(">", "&amp;gt;");
		outputCode.append(inputCode + '\n' + "</pre>\n");

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>SyntaxHighlighter Easy Tool</title>");
		out.println("<link href='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/styles/shCore.css'"
				+ " rel='stylesheet' type='text/css' />");
		out.println("<link href='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/styles/shThemeDefault.css'"
				+ " rel='stylesheet' type='text/css' />");
		out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/shCore.js'></script>"
				+ "<script src='http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/shAutoloader.js'></script>");
				
		out.println("</head>");
		out.println("<body>");
		printInputForm(out);

		out.println("<br>輸出：");
		out.println("<br>");
		out.println("<textarea name=\"code\" rows=\"10\" cols=\"100\">"
				+ outputCode + "</textarea>");

		out.println("<br><br>");
		out.println("Result Preview<br>");		
		
		if(isCollapse && noToolbar){
			out.println("<p style=\"color: red\">Error: You can not check \"collapse\" and \"no toolbar\" at the same time!</p><br>");
		}
		
		out.println(outputCode);
		printSyntaxHighliterAutoLoader(out);

		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	private static void printSyntaxHighliterAutoLoader(PrintWriter out) {
		out.println("<script>"
				+ "	//<![CDATA["
				+ "\n"
				+ "	function path() {"
				+ "		var args = arguments, result = [];"
				+ "		for (var i = 0; i < args.length; i++)"
				+ "			result.push(args[i].replace('@', 'http://cdnjs.cloudflare.com/ajax/libs/SyntaxHighlighter/3.0.83/scripts/'));"
				+ "		return result"
				+ "	};"
				+ "\n"
				+ "	SyntaxHighlighter.autoloader.apply(null, path("
				+ "			'applescript            @shBrushAppleScript.js',"
				+ "			'actionscript3 as3      @shBrushAS3.js',"
				+ "			'bash shell             @shBrushBash.js',"
				+ "			'coldfusion cf          @shBrushColdFusion.js',"
				+ "			'cpp c                  @shBrushCpp.js',"
				+ "			'c# c-sharp csharp      @shBrushCSharp.js',"
				+ "			'css                    @shBrushCss.js',"
				+ "			'delphi pascal          @shBrushDelphi.js',"
				+ "			'diff patch pas         @shBrushDiff.js',"
				+ "			'erl erlang             @shBrushErlang.js',"
				+ "			'groovy                 @shBrushGroovy.js',"
				+ "			'java                   @shBrushJava.js',"
				+ "			'jfx javafx             @shBrushJavaFX.js',"
				+ "			'js jscript javascript  @shBrushJScript.js',"
				+ "			'perl pl                @shBrushPerl.js',"
				+ "			'php                    @shBrushPhp.js',"
				+ "			'text plain             @shBrushPlain.js',"
				+ "			'py python              @shBrushPython.js',"
				+ "			'ruby rails ror rb      @shBrushRuby.js',"
				+ "			'sass scss              @shBrushSass.js',"
				+ "			'scala                  @shBrushScala.js',"
				+ "			'sql                    @shBrushSql.js',"
				+ "			'vb vbnet               @shBrushVb.js',"
				+ "			'xml xhtml xslt html    @shBrushXml.js'));//]]>"
				+ "</script>"
				+ "\n"
				+ "<script>"
				+ "	SyntaxHighlighter.all()"
				+ "</script>");
	}
}
