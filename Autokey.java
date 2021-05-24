package Cryptosystem;

import java.util.*;
import static java.lang.System.in;
import static java.lang.System.out;

public class Autokey {
	public static Scanner in = new Scanner(System.in);

	//encrypt
	private static String encryption(String plainText, String keyPhrase) {
		String cipherText = "";
		plainText = plainText.toUpperCase();
		keyPhrase = keyPhrase.toUpperCase();

		for (int i = 0; i < plainText.length(); i++) {
			int x = (((plainText.charAt(i) - 'A') + (keyPhrase.charAt(i) - 'A')) % 26);
			cipherText += (char) (x + 'A');
		}

		return cipherText;

	}

	//decrypt
	private static String decryption(String cipherText, String keyPhrase) {
		String plainText = "";
		cipherText = cipherText.toUpperCase();
		keyPhrase = keyPhrase.toUpperCase();

		for (int i = 0; i < cipherText.length(); i++) {

			int x = (((cipherText.charAt(i) - 'A') - (keyPhrase.charAt(i) - 'A')) % 26);
			x = (x < 0) ? (26 - Math.abs(x)) : x;
			plainText += (char) (x + 'A');
			keyPhrase += (char) (x + 'A');
		}

		return plainText;
	}

	//main function switch used
	public static void main(String[] args) {
		out.println("Press 1 to encrypt a message and Press 2 to decrypt a message.");
		int command = in.nextInt();
		in.nextLine();
		
		switch (command) {
		case 1:
			out.println("Please enter your message");
			String msgToEencrypt = in.nextLine().replaceAll(" ", "");

			out.println("Please enter your key");
			String keyToEencrypt = in.nextLine().replaceAll(" ", "");

			keyToEencrypt += msgToEencrypt;
			out.println("Encrpyted Messages: ");
			out.println(encryption(msgToEencrypt, keyToEencrypt));
			break;

		case 2:
			out.println("Please enter your message");
			String msgToDecrypt = in.nextLine();
			out.println("Please enter your key");
			String keyToDecrypt = in.nextLine();
			out.println("Decrpyted Messages: ");
			out.println(decryption(msgToDecrypt, keyToDecrypt));
			break;
		}

	}

}
