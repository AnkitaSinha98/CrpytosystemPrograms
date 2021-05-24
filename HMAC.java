package Cryptosystem;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		String data = null;
		String key = null;

		System.out.println("Enter the data : ");
		data = sc.next();

		System.out.println("Enter the key : ");
		key = sc.next();

		String hmac = HMACc(data, key);
		System.out.println("The HMAC hashed value is : "+hmac);
	}

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	public static String HMACc(String data, String key)
			throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
		Mac mac = Mac.getInstance("HmacSHA512");
		mac.init(secretKeySpec);
		return toHexString(mac.doFinal(data.getBytes()));
	}
}