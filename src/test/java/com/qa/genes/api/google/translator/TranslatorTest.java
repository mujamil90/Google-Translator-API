package com.qa.genes.api.google.translator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.genes.api.google.translator.properties.PropertiesReader;

public class TranslatorTest extends PropertiesReader{
	
	public  String english;
	public  String spanish;
	public  String german;
	public  String englishText;
	public  String spanishText;
	public  String germanText;
	
	
	@BeforeClass
	public void loadAllPropFiles() {
		
		PropertiesReader.loadAllFiles(Constant.ENVIRONMENT_TESTDATA);
		english=PropertiesReader.prop.getProperty("ENGLISH");
		spanish=PropertiesReader.prop.getProperty("SPANISH");
		german=PropertiesReader.prop.getProperty("GERMAN");
		englishText=PropertiesReader.prop.getProperty("ENGLISH_TEXT");
		spanishText=PropertiesReader.prop.getProperty("SPANISH_TEXT");
		germanText=PropertiesReader.prop.getProperty("GERMAN_TEXT");
		
	}
	
	@Test
	public void englishToSpanishTest() throws Exception {
		String translatedText=Translator.translator(english, spanish, englishText);
		Assert.assertEquals(translatedText, spanishText);
	}

	@Test
	public void englishToGermanTest() throws Exception {
		String translatedText=Translator.translator(english, german, englishText);
		Assert.assertEquals(translatedText, germanText);
	}



	@Test
	public void germanToEnglishTest() throws Exception {
		String translatedText=Translator.translator(german, english, germanText);
		Assert.assertEquals(translatedText, englishText);
	}

}
