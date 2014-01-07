package com.Until;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

/**
 * 
 * @author cwr 把接收到的HttpResponce里面的内容转换为String
 */
public class Until_HttpEntity_to_String {
	static public String Change(HttpResponse httpResponce) {
		String result = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpResponce.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			// 获取本系统的行分割线
			String NL = System.getProperty("line.separator");
			// 把http响应的输入流数据按行读取到StringBuffer中
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + NL);
			}
			result = stringBuffer.toString();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("the result is " + result);
		return result;
	}

}
