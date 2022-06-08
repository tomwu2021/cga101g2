package core.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AuthCode {
	public static String genAuthCode() {

		String strGenAuthCode = "";
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Set<Integer> set = new HashSet<Integer>();
		int number01 = (int) (Math.random() * (25 - 0 + 1) + 0); // 0 ~ 25 一定有英文小寫
		set.add(number01);
		int number02 = (int) (Math.random() * (51 - 26 + 1)) + 26; // 26 ~ 51 一定有英文大寫
		set.add(number02);
		int number03 = (int) (Math.random() * (61 - 52 + 1)) + 52; // 52 ~ 61 一定有數字
		set.add(number03);
		while (set.size() != 8) {
			int number = (int) (Math.random() * 61);
			set.add(number);
		}
		Iterator<Integer> objs = set.iterator();
		while (objs.hasNext()) {
			char ch = str.charAt((int) objs.next());
			strGenAuthCode += ch;
		}
		return strGenAuthCode;
	}
}
