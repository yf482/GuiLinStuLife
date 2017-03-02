package com.glife.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class EncodeUtils {
	
	
	@Test
	public void test1(){
		String cn = "你好";
		try {
			String str = URLEncoder.encode(cn, "utf-8");
			
			System.out.println(str);
			String str2 = URLDecoder.decode(str, "utf-8");
			
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
