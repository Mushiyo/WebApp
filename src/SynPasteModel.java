import java.util.HashMap;
import java.util.Map;

public class SynPasteModel {
	private static final Map<String, String> setting;
	static {		
		setting = new HashMap<String, String>();
		setting.put("collapse", "; collapse: true");
		setting.put("html", "; html-script: true");
		setting.put("gutter", "; gutter: false");
		setting.put("autolink", "; auto-links: false");
		setting.put("toolbar", "; toolbar: false");
	}
	
	private static StringBuilder setting(String lan, String firstLine, String tabSize, String[] options, String highlightLine, String title){
StringBuilder outputCode = new StringBuilder("<pre class=\"brush: ");
		
		if (lan.equals("null")) {
			outputCode.append("text");
		} else {
			outputCode.append(lan);
		}
		
		if (!firstLine.equals("")) {
			outputCode.append("; first-line: " + firstLine);
		}
		
		if (!tabSize.equals("")) {
			outputCode.append("; tab-size: " + tabSize);
		}
		
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
		
		if(!highlightLine.equals("")){
			outputCode.append("; highlight: [" + highlightLine + "]");
		}
		
		if (!title.equals("")) {
			outputCode.append("\" title=\"" + title + "\">\n");
		} else {
			outputCode.append("\">\n");
		}
		
		return outputCode;
	}
	
	static String pasteCode(String inputCode, String lan, String firstLine, String tabSize, String[] options, String highlightLine, String title){
		StringBuilder outputCode = setting(lan, firstLine, tabSize, options, highlightLine, title);
		
		inputCode = inputCode.replaceAll("<", "&amp;lt;").replaceAll(">", "&amp;gt;");		
		outputCode.append(inputCode + '\n' + "</pre>\n");
		
		return outputCode.toString();
	}
	
	static String displayCode(String inputCode, String lan, String firstLine, String tabSize, String[] options, String highlightLine, String title){
		StringBuilder outputCode = setting(lan, firstLine, tabSize, options, highlightLine, title);
		
		inputCode = inputCode.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		outputCode.append(inputCode + '\n' + "</pre>\n");
		
		return outputCode.toString();
	}
}
