package com.aglook.comapp.encrypt;

import android.util.Base64;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {
	
	public static final String DESKEY="12345678";
	
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	public static String encode(String key, String text) throws Exception {
		byte[] data = text.getBytes();
		DESKeySpec dks = new DESKeySpec(key.getBytes());

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// key的长度不能够小于8位字节
		Key secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes());
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
		byte[] bytes = cipher.doFinal(data);
		return new String(Base64.encodeToString(bytes, Base64.DEFAULT));
	}

	public static String decode(String key, String data) throws Exception {
			byte[] decodeBase64 = Base64.decode(data, Base64.DEFAULT);
			//SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			return new String(cipher.doFinal(decodeBase64));
		
	}

	public static String getKey() {
		UUID randomUUID = UUID.randomUUID();
		String uuid = randomUUID.toString();
		return uuid.substring(0, 8);
	}

	public static void main(String[] args) throws Exception {
		String encode = DESUtil.encode("asdfwef5", "abcd111111111111");
		System.out.println(encode);
		String decode = DESUtil.decode("asdfwef5", encode);
		System.out.println(decode);

	}
}