package com.android.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	Properties properties = null;

	public void loadProperties(String filename) throws IOException {
		properties = new Properties();
		String projectDirPath = System.getProperty("user.dir");
		String propFilePath = projectDirPath + "/src/test/resources/" + filename;
		InputStream ins = new FileInputStream(propFilePath);
		properties.load(ins);

	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}
}
