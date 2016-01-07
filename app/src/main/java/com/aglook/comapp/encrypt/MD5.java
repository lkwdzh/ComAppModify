package com.aglook.comapp.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class MD5 {
	public static final String md5Key = "xxxxxxxx";
	// 不需要签名的object中的对象key
	private static List<String> unSignKeyList = Arrays.asList("sign", "ipAddr");
	/**
	 * 创建签名字符串
	 * @param sPara
	 * @param key
	 * @param input_charset
	 * @return
	 */
	public static String buildSignByString(Map<String, String> sPara, String key, String input_charset) {
		String buildMysignString = buildMysignString(sPara);
		String sign = MD5.sign(buildMysignString, key, input_charset);
		return sign;
	}

    public static String ss(String text,String key){
        byte[] bytes = new byte[0];
        try {
            text=text+key;

            bytes = text.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(bytes);
        BigInteger bi = new BigInteger(1, md5.digest());
        String mdStr = bi.toString(16);
        System.out.print("result_text______"+text+key);
//        Log.d("result_text",text+key);
        return mdStr;

    }

	/**
	 * 创建签名字符串
	 * 
	 * @param sPara
	 * @param key
	 * @param input_charset
	 * @return
	 */
	public static String buildSignByObject(Object object, String key, String input_charset) {
		TreeMap<String, Object> objectToMap;
		try {
			objectToMap = MD5.objectToMap(object);
			// 删除不需要参与签名的属性
			for (String str : unSignKeyList) {
				objectToMap.remove(str);
			}
			String createLinkStr = createLinkStr(objectToMap);
			
			String sign = MD5.sign(createLinkStr, key, input_charset);
			return sign;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *                需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkStr(TreeMap<String, Object> map) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = (String) map.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 创建签名原始字符串
	 * 
	 * @param sPara
	 * @return
	 */
	public static String buildMysignString(Map<String, String> sPara) {
		String createLinkString = createLinkString(sPara);
		return createLinkString;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *                需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 签名字符串
	 * 
	 * @param text
	 *                需要签名的字符串
	 * @param key
	 *                密钥
	 * @param input_charset
	 *                编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		System.out.println("text"+text);
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * 签名字符串
	 * 
	 * @param text
	 *                需要签名的字符串
	 * @param sign
	 *                签名结果
	 * @param key
	 *                密钥
	 * @param input_charset
	 *                编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws java.security.SignatureException
	 * @throws java.io.UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 * 对象转map
	 * 
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static TreeMap<String, Object> objectToMap(Object object) throws IllegalArgumentException, IllegalAccessException {
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		// 父类属性
		for (Class<?> cls = object.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
			// 添加属性key到list
			Field[] fields = cls.getDeclaredFields();
//			Field[] fields = cls.getFields();
			for (Field f : fields) {
				f.setAccessible(true);
				map.put(f.getName(), f.get(object) == null ? "" : f.get(object));
			}
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println( DigestUtils.md5Hex(getContentBytes("123456", "utf-8")));
	}
}