package com.Until;

public class Until_Welcome {
	private final static String[] WELCOM = new String[] { "天再高又怎样，踮起脚尖就更接近阳光",
			"微笑拥抱每一天，做像向日葵般温暖的女子", "别小看任何人，越不起眼的人。往往会做些让人想不到的事",
			"没有伞的孩子必须努力奔跑！", "我们什么都没有，唯一的本钱就是青春。梦想让我与众不同，奋斗让我改变命运！",
			"你不勇敢，没人替你坚强！","","" };

	public static String GetString()// 返回随机获得的字串
	{
		int i = (int) (Math.random() * 5);// 采用随机生成的方法生成0-n(n为数组的长度-1)之间的随机数
		System.out.println(i);
		String str = WELCOM[i];
		return str;

	}
}
