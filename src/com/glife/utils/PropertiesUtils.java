package com.glife.utils;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties getPropertie(String fileName){
    	if(null == fileName){
    		System.out.println("file name null");
    		return null;
    	}
    	Properties prop = new Properties();
    	InputStreamReader reader = null;
    	try{
    		reader = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName),"utf-8");
    		prop.load(reader);
    		reader.close();
    	}catch (Exception e){
    		e.printStackTrace();
    	}finally {
    		if(reader != null){
    			reader = null;
    		}
    	}
    	return prop;
    }
   

}
