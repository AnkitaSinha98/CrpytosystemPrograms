package Cryptosystem;

import java.io.*;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cipher {

	static String hmac(String message, String Key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String pad = "36";
		pad = new String(new char[64]).replace("\0", pad);
		BigInteger ipad = new BigInteger(pad, 16);

		pad = "5c";
		pad = new String(new char[64]).replace("\0", pad);
		BigInteger opad = new BigInteger(pad, 16);

		byte[] bytes = Key.getBytes();
		Key = "";

		for (Byte b : bytes) {

			int val = b;
			for (int i = 0; i < 8; i++) {
				Key += (val & 128) == 0 ? "0" : "1";
				val <<= 1;
			}
		}

		int len = Key.length();
		BigInteger key = new BigInteger(Key, 2);
		String S = key.xor(ipad).toString(2);

		len = S.length();

		for (int i = 0; i < 512 - len; i++) {
			S = "0" + S;
		}

		bytes = message.getBytes();
		message = "";
		for (Byte b : bytes) {

			int val = b;
			for (int i = 0; i < 8; i++) {
				message += (val & 128) == 0 ? "0" : "1";
				val <<= 1;
			}
		}

		message = S + message;

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(message.getBytes("utf8"));

		S = key.xor(opad).toString(2);

		len = S.length();

		for (int i = 0; i < 512 - len; i++) {
			S = "0" + S;
		}

		message = S + new BigInteger(1, digest.digest()).toString(2);

		MessageDigest digest2 = MessageDigest.getInstance("SHA-256");
		digest2.reset();
		digest2.update(message.getBytes("utf8"));

		return new BigInteger(1, digest2.digest()).toString(16);
	}

	public static void main(String[] args)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the plain text: ");
		String message = sc.next();

		System.out.println("Enter the Key: ");
		String Key = sc.next();

		System.out.println("Hash code : " + hmac(message, Key));
	}

}
