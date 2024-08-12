package com.base;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class PaymentClass extends BaseClass{
    static Cipher ecipher;
	static Cipher dcipher;

    public static String key="9B3AA7E81FBECD36526E492165AFD39D";

	/**
	 * Input a string that will be md5 hashed to create the key.
	 * @return 
	 * 
	 * @return void, cipher initialized
	 */

	public PaymentClass(String key) {
		SecretKeySpec skey = new SecretKeySpec(getMD5(key), "AES");
		this.setupCrypto(skey);
	}

	private void setupCrypto(SecretKey key) {
		// Create an 8-byte initialization vector
		byte[] iv = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d,
				0x0e, 0x0f };

		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		try {
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			// CBC requires an initialization vector
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Input is a string to encrypt.
	 * 
	 * @return a Hex string of the byte array
	 */
	public String encrypt(String plaintext) {
		try {
			byte[] ciphertext = ecipher.doFinal(plaintext.getBytes("UTF-8"));
			return byteToHex(ciphertext);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Input encrypted String represented in HEX
	 * 
	 * @return a string decrypted in plain text
	 */
	public String decrypt(String hexCipherText) throws Exception {

		String plaintext = new String(dcipher.doFinal(PaymentClass.hexToByte(hexCipherText)), "UTF-8");
		return plaintext;

	}

	public String decrypt(byte[] ciphertext) {
		try {
			String plaintext = new String(dcipher.doFinal(ciphertext), "UTF-8");
			return plaintext;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] getMD5(String input) {
		try {
			byte[] bytesOfMessage = input.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(bytesOfMessage);
		} catch (Exception e) {
			return null;
		}
	}

	static final String HEXES = "0123456789ABCDEF";

	public static String byteToHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		String result = "";
		for (int i = 0; i < raw.length; i++) {
			result += Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static byte[] hexToByte(String hexString) {
		int len = hexString.length();
		byte[] ba = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			ba[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
					+ Character.digit(hexString.charAt(i + 1), 16));
		}
		return ba;
	}

	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		for (int i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xff) < 16)
				strbuf.append("0");
			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	public static String encryption(String data) {
		PaymentClass encrypter = new PaymentClass(key);
		return encrypter.encrypt(data);
	}

	public static String decryption(String data) throws Exception {
		PaymentClass encrypter = new PaymentClass(key);
		return encrypter.decrypt(data);
	}

    public static String paymentInput(String OrderId) throws IOException{
    Random number=new Random();
    System.out.println(number.nextInt(12));   
       String a=toReadDataFromExcel("Payments", 4, 1);
       long b=Long.parseLong(a)+1;
       String c=String.valueOf(b);
	   toCreateNewCell("Payments", 4, 1, c);
       String e="order_id="+OrderId+"&tracking_id="+c+"&bank_ref_no=bsb13694f2961&order_status=Success&failure_message=&payment_mode=NetBanking&card_name=Avenues Test for New TC&status_code=null&status_message=Transaction is Successful&currency=INR&amount=2154.00&billing_name=null&billing_address=&billing_city=&billing_state=&billing_zip=&billing_country=&billing_tel=null&billing_email=&delivery_name=&delivery_address=&delivery_city=&delivery_state=&delivery_zip=&delivery_country=&delivery_tel=&merchant_param1=&merchant_param2=&merchant_param3=&merchant_param4=&merchant_param5=&vault=N&offer_type=null&offer_code=null&discount_value=0.0&mer_amount=2154.00&eci_value=null&retry=N&response_code=&billing_notes=&trans_date=07/02/2024 15:40:45&bin_country=";
       String data=encryption(e);
       return data;
    }


}
