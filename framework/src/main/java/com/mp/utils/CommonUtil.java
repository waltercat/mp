package com.mp.utils;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {

	public static boolean isEmpty(final String value) {

		return StringUtils.isEmpty(value);
	}

	public static boolean isEmpty(final Object value) {

		return (null == value);
	}

	/**
	 * 
	 * @Title: genarateID 生成ID
	 * @Description: TODO(基于Twitter的Snowflake算法，生成符合时间趋势的ID.)
	 * @return long 返回长整型ID
	 */
	public static long genarateID() {

		Random r = new Random(31);

		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(r.nextInt(), r.nextInt());

		return idWorker.nextId();
	}

	public static String MD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	public static void sendJsonData(HttpServletResponse response, String data) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}
	
	public static void error(HttpServletResponse response, String errorMsg) throws Exception {
		JSONObject errorInfo = new JSONObject();
		errorInfo.put("status", false);
		errorInfo.put("code", 404);
		errorInfo.put("message", errorMsg);
		errorInfo.put("data", null);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(errorInfo.toJSONString());
		out.flush();
		out.close();
	}

}
