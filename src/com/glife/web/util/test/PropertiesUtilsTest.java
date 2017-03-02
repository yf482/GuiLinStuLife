package com.glife.web.util.test;

import java.util.Properties;

import org.junit.Test;

import com.glife.web.utils.PropertiesUtils;

public class PropertiesUtilsTest {

	
	@Test
	public void test3(){
		Properties prop = PropertiesUtils.getPropertie("b.properties");
		System.out.println(prop.getProperty("hello"));
		Properties prop2 = PropertiesUtils.getPropertie("resources/a.properties");
		System.out.println(prop2.getProperty("hello"));
	}
	@Test
	public void test1(){
		PropertiesUtils.getPropertieByRb("hello");
	}
	
	
	@Test
	public void test2(){
//		PropertiesUtils.getPropertieByClass("b.properties");
		PropertiesUtils.getPropertieByClass("resources/a.properties");
	}
}
