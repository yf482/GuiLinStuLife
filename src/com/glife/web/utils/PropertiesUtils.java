package com.glife.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtils {

	/**
	 * 通过ClassLoader获取资源文件
	 * //1.2.类加载器读取：只能读取classes或者类路径中的任意资源。但是不适合读取特别大的资源。只能读取 1 2
	 * @param fileName
	 * @return
	 */
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
    
    
    //请不要把Tomcat等服务器装在有空格的目录中
   //1.1.类加载器读取：只能读取classes或者类路径中的任意资源。但是不适合读取特别大的资源
    public static Properties getPropertieByClass(String fileName){
    	ClassLoader cl = PropertiesUtils.class.getClassLoader();

    	URL url2 = cl.getResource(fileName);
    	Properties prop = new Properties();
    	
    	try {
			InputStream in = new FileInputStream(url2.getPath());
			prop.load(in);
			System.out.println(prop.getProperty("hello"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return prop;
    }
    
    
  //2.利用ResourceBundle读取：1 2 （文件在classes类路径内），不能读1，只能读取properties的文件
     public static void getPropertieByRb(String key){
    	 
    	 ResourceBundle rb = ResourceBundle.getBundle("b");
    	 System.out.println(rb.getString("hello"));
    	 ResourceBundle rb2 = ResourceBundle.getBundle("resources.a");
    	 System.out.println(rb2.getString("hello"));
     }
   

}
