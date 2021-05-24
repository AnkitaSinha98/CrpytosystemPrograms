package Cryptosystem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA3 {

	public static void main(String args[]) throws NoSuchAlgorithmException {
		String clearText = null;
		String hashvalue = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the clear text : ");
		clearText = sc.next();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA3-256");
			byte[] mesDig = md.digest(clearText.getBytes());
			BigInteger no = new BigInteger(1, mesDig);
			hashvalue = no.toString(16);
			while (hashvalue.length() < 32) {
				hashvalue = "0" + hashvalue;
			}
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		System.out.println("The hashvalue is: " + hashvalue);
	}
}