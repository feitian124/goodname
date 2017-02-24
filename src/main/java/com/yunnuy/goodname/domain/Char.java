package com.yunnuy.goodname.domain;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * chinese char
 */
public class Char {
	
	private static final int UNICODE_MIN = 19968;
	
	private static final int UNICODE_MAX = 40869;
	
	private static final String CR = System.getProperty("line.separator");
	
	public void execute(String fileName) throws IOException {
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		System.out.println("FileWriter getEncoding: " + fw.getEncoding());
		
		fw.write("char, Unicode-10, Unicode-16, GBK-10, GBK-16" + CR);

		for (int i = UNICODE_MIN; i <= UNICODE_MAX; i++) {
			int gbkCode = getGBKCode(i);
			String line = String.format("%s, %s, %s, %s, %s", (char)i, i, toHex(i), gbkCode, toHex(gbkCode));
			fw.write(line + CR);
		}
		
		fw.flush();
		fw.close();
		
		System.out.println(f.getAbsolutePath());
	}

	private int getGBKCode(int unicode) throws UnsupportedEncodingException {
		char c = (char) unicode;
		byte[] bytes = (c + "").getBytes("GBK");
		return ((bytes[0] & 255) << 8) + (bytes[1] & 255);
	}
	
	private String toHex(int i) {
		return Integer.toHexString(i);
	}

	public static void main(String[] args) throws Exception {
		new Char().execute("chinese_char_code_numbers.txt");
	}
}