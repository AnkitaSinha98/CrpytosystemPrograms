package Cryptosystem;

import java.io.*;

public class SubstitutionCipher {
	public static final String str = "abcdefghijklmnopqrstuvwxyz";
	
	public static String encrypt(String plaint, int key) {
		plaint = plaint.toLowerCase();
		String ciphert = "";
		for (int i = 0; i < plaint.length(); i++) {
			int charpos = str.indexOf(plaint.charAt(i));
			int keyval = (charpos + key) % 26;
			char replaceval = str.charAt(keyval);
			ciphert = ciphert + replaceval;
		}
		return ciphert;
	}
	
	public static String decrypt(String ciphert, int key) {
		ciphert = ciphert.toLowerCase();
		String plaint = "";
		for (int i = 0; i < ciphert.length(); i++) {
			int charpos = str.indexOf(ciphert.charAt(i));
			int keyval = (charpos - key) % 26;
			if (keyval < 0) {
				keyval = str.length() + keyval;
			}
			char replaceval = str.charAt(keyval);
			plaint = plaint + replaceval;
		}
		return plaint;
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a plain text: ");
		String msg = br.readLine();
		System.out.println("Enter a key: ");
		String key = br.readLine();
		int k = Integer.parseInt(key);
		System.out.println("Encrypted Text: ");
		System.out.println(encrypt(msg, k));
		System.out.println("Decrypted text: ");
		System.out.println(decrypt(encrypt(msg, k), k));
	}
}
