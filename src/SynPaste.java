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
 * Servlet implementation class SynPaste
 */
@WebServlet("/SynPaste")
public class SynPaste extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SynPaste() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		request.getRequestDispatcher("SynPasteView.jsp").forward(request, response);
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
		String firstLine = request.getParameter("lineNum");
		String tabSize = request.getParameter("tabSize");
		String[] options = request.getParameterValues("option");
		String highlightLine = request.getParameter("highlightLine");
		String title = request.getParameter("title");
		String inputCode = request.getParameter("code");	
		String outputCode = SynPasteModel.pasteCode(inputCode, lan, firstLine, tabSize, options, highlightLine, title);
		String displayCode = SynPasteModel.displayCode(inputCode, lan, firstLine, tabSize, options, highlightLine, title);
		
		request.setAttribute("outputCode", outputCode);
		request.setAttribute("displayCode", displayCode);
		request.getRequestDispatcher("SynPasteView.jsp").forward(request, response);
	}
}
