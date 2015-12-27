package translate;

import java.io.IOException;
import java.util.Scanner;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.DataUtil;

/**
 * Servlet implementation class translate
 */
@WebServlet("/translate")
public class translate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ENCODING = "UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public translate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(ENCODING);
		response.setContentType("text/html; charset=UTF-8");

		request.getRequestDispatcher("translate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(ENCODING);
		response.setContentType("text/html; charset=UTF-8");

		String sourceLan = request.getParameter("sourceLan");
		String targetLan = request.getParameter("targetLan");
		String textToTrans = URLEncoder.encode(request.getParameter("textToTrans"), ENCODING);
		request.setAttribute("textToTrans", URLDecoder.decode(textToTrans, ENCODING));

		// get key.json which contains API keys
		JSONObject keyObj = DataUtil.getJSONObject(getServletContext().getResourceAsStream("/WEB-INF/key.json"));
		
		// Google Translate
		// There's no free version of Google Translate API, so only generate url
		String gTranslateUrlQuery = String.format("https://translate.google.com/?sl=%s&tl=%s#%s/%s/%s", sourceLan, targetLan, sourceLan, targetLan, textToTrans);
		request.setAttribute("gTranslate", URLDecoder.decode(gTranslateUrlQuery, ENCODING));

		// Yandex.Translate
		String ydSourceLan = sourceLan.startsWith("zh") ? "zh" : sourceLan;
		String ydTargetLan = targetLan.startsWith("zh") ? "zh" : targetLan;
		String ydTranslateKey = keyObj.getString("Yandex Translate");
		String ydTransQuery = String.format(
				"https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s-%s", ydTranslateKey,
				textToTrans, ydSourceLan, ydTargetLan);
		String ydTranslateText = DataUtil.getJSONObject(ydTransQuery).getJSONArray("text").getString(0);
		String ydTransUrlQuery = String.format("https://translate.yandex.com/?text=%s&lang=%s-%s", textToTrans, ydSourceLan, ydTargetLan);
		request.setAttribute("ydTranslate", ydTranslateText);
		request.setAttribute("ydTranslateUrl", ydTransUrlQuery);
		
		// Microsoft translator
		String bTranslatorUrlQuery = String.format("http://www.bing.com/translator?text=%s&from=%s&to=%s", textToTrans, sourceLan, targetLan);
		request.setAttribute("msTranslator", textToTrans);
		request.setAttribute("bTranslatorUrl", bTranslatorUrlQuery);
		
		// return the translation result
		request.getRequestDispatcher("translate.jsp").forward(request, response);
	}

}
