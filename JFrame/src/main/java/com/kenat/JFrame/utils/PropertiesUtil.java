package com.kenat.JFrame.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtil {
	
	public static final Properties p = new Properties();
	
	public static final String config = "config.properties";

	public static final String path = FileIUtil.class.getClassLoader().getResource("config.properties").getPath();

	public static void init() {
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(config);
		
		try {
			p.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static String get(String key) {
		
		String propertiy = p.getProperty(key);
		
		if (propertiy == null) {
			propertiy = "";
		}
		return propertiy;
	}
	
	public static void update(String key, String value) {
		p.setProperty(key, value);
		FileOutputStream oFile = null;
		
		try {
			oFile = new FileOutputStream(path);
			//将Properties中的属性列表（键和元素对）写入输出流
			p.store(oFile, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				oFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(String key) {
		p.remove(key);
		FileOutputStream oFile = null;
		
		try {
			oFile = new FileOutputStream(path);
			p.store(oFile, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				oFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void list() {
		Enumeration en = p.propertyNames(); //得到配置文件的名字
		while(en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = p.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertiesUtil.init();
		
		PropertiesUtil.get("password");
		System.out.println(PropertiesUtil.get("password"));
//		PropertiesUtil.update("password","aasdsada");
//		System.out.println(PropertiesUtil.get("password"));
//		
//		PropertiesUtil.delete("username");
//		System.out.println(PropertiesUtil.get("username"));
//		
//		PropertiesUtil.list();
	}

}
