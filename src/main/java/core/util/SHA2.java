package core.util;

public class SHA2 {
	
	public static String getSHA256(String inputValue, Integer memberId) {
		String newString = inputValue + String.valueOf(memberId);
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(newString);
	}

}
