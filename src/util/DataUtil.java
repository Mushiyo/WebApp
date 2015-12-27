package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class DataUtil {
	/*
	 * return JSON object from specific URL
	 */
	public static JSONObject getJSONObject(String url) throws JSONException, MalformedURLException, IOException {
		return new JSONObject(JSONString(url));
	}
	
	public static JSONObject getJSONObject(InputStream JSONStream){
		return new JSONObject(JSONString(JSONStream));
	}

//	public static String getAPIKey(String keyName) {
//		
//	}

	private static String JSONString(String JSONUrl) throws MalformedURLException, IOException {
		return JSONString(new URL(JSONUrl));
	}

	private static String JSONString(URL JSONUrl) throws IOException {
		return JSONString(JSONUrl.openStream());
	}

	private static String JSONString(InputStream JSONStream) {
		StringBuilder jsonContent = new StringBuilder();

		Scanner urlReader = new Scanner(JSONStream);

		while (urlReader.hasNext()) {
			jsonContent.append(urlReader.nextLine());
		}
		urlReader.close();

		return jsonContent.toString();
	}
}
