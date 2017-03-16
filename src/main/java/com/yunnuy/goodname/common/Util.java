package com.yunnuy.goodname.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int UNICODE_MIN = 19968;
	
	private static final int UNICODE_MAX = 40869;
	
	private static final String CR = System.getProperty("line.separator");
	
	private void execute(String fileName) throws IOException {
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		
		fw.write("char, Unicode-10, Unicode-16, GBK-10, GBK-16" + CR);

		for (int i = UNICODE_MIN; i <= UNICODE_MAX; i++) {
			int gbkCode = getGBKCode(i);
			String line = String.format("%s, %s, %s, %s, %s", (char)i, i, toHex(i), gbkCode, toHex(gbkCode));
			fw.write(line + CR);
		}
		
		fw.flush();
		fw.close();
		
		logger.info("FileWriter getEncoding: " + fw.getEncoding());
		logger.info(f.getAbsolutePath());
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
		new Util().execute("chinese_char_code_numbers.txt");
	}
}
