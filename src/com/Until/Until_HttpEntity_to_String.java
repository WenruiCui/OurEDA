package com.Until;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

/**
 * 
 * @author cwr �ѽ��յ���HttpResponce���������ת��ΪString
 */
public class Until_HttpEntity_to_String {
	static public String Change(HttpResponse httpResponce) {
		String result = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpResponce.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			// ��ȡ��ϵͳ���зָ���
			String NL = System.getProperty("line.separator");
			// ��http��Ӧ�����������ݰ��ж�ȡ��StringBuffer��
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
