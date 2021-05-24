package Cryptosystem;
import java.util.*;

public class CaesarCipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	

    //Encrpyt	
    public static String encrypt(String plainText, int shiftKey)
    {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(plainText.charAt(i));
            int keyVal = (shiftKey + charPosition) % 26;
            char replaceVal = ALPHABET.charAt(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }
	
    //Decrypt	
    public static String decrypt(String cipherText, int shiftKey)
    {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(cipherText.charAt(i));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0)
            {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            plainText += replaceVal;
        }
        return plainText;
    }

    //Main Function	
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String for Encryption: ");
        String message = new String();
        message = sc.next();
        System.out.println("Enter the key: ");
        int key = sc.nextInt();
        System.out.println("Encryption: "+ encrypt(message, key));
        System.out.println("Decryption: "+ decrypt(encrypt(message, key), key));
        sc.close();
    }
}
