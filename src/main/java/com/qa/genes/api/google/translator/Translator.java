package com.qa.genes.api.google.translator;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;

import com.qa.genes.api.google.translator.properties.PropertiesReader;

public class Translator extends PropertiesReader{

	private static String unicodeStandard=prop.getProperty("UNICODE_STANDARD");
	private static String endPoint=prop.getProperty("END_POINT");
	private static String clientType=prop.getProperty("CLIENT");
	private static String agent=prop.getProperty("AGENT");
	private static String agentType=prop.getProperty("AGENT_TYPE");
	
/* public static void main(String[] args) throws Exception 
 {

  Translator http = new Translator();
  String word = http.translator("en", "ar", "Organic Tea");
  System.out.println(word);
 
 }*/
 
 /***
  * 
  * @param langFrom is the language in @param word will be supplied in method
  * @param langTo is language @param word will be translated
  * @param word is text need to be translated.
  * @return translated text in @param langTo.
  * @throws Exception
  */
 public static String translator(String langFrom, String langTo, String word) throws Exception 
 {

  String url = endPoint+
    "client="+clientType+"&"+
    "sl=" + langFrom + 
    "&tl=" + langTo + 
    "&dt=t&q=" + URLEncoder.encode(word, unicodeStandard);    
  
  URL obj = new URL(url);
  HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
  con.setRequestProperty(agent, agentType);
 
  BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
  String inputLine;
  StringBuffer response = new StringBuffer();
 
  while ((inputLine = in.readLine()) != null) {
   response.append(inputLine);
  }
  in.close();
 
  return parseTranslatedText(response.toString());
 }
 
 /****
  * 
  * @param inputJson is translated words return by @translator method
  * @return parsed @string 
  * @throws Exception
  * 
  * 
  * inputJson for word 'Bye' translated to language Arabic from English- 'وداعا'.
  * 
  */
 private static String parseTranslatedText(String inputJson) throws Exception
 {
  
  JSONArray jsonArray = new JSONArray(inputJson);
  JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
  JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
  
  return convertToUTF8(jsonArray3.get(0).toString());
		  
 }
 
 /***
  * 
  * @param translatedText to be convert in unicode UTF-8
  * @return
  */
 private static String convertToUTF8(String translatedText) {
	  String convertedText=null;
	 try {
		 convertedText=new String(translatedText.getBytes(), unicodeStandard);
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
    return convertedText;
}

}
	
	
	
