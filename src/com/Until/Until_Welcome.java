package com.Until;

public class Until_Welcome {
	private final static String[] WELCOM = new String[] { "���ٸ�������������ż�͸��ӽ�����",
			"΢Цӵ��ÿһ�죬�������տ�����ů��Ů��", "��С���κ��ˣ�Խ�����۵��ˡ���������Щ�����벻������",
			"û��ɡ�ĺ��ӱ���Ŭ�����ܣ�", "����ʲô��û�У�Ψһ�ı�Ǯ�����ഺ�������������ڲ�ͬ���ܶ����Ҹı����ˣ�",
			"�㲻�¸ң�û�������ǿ��","","" };

	public static String GetString()// ���������õ��ִ�
	{
		int i = (int) (Math.random() * 5);// ����������ɵķ�������0-n(nΪ����ĳ���-1)֮��������
		System.out.println(i);
		String str = WELCOM[i];
		return str;

	}
}
