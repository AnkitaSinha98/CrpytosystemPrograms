package Cryptosystem;

import java.util.Scanner;

public class CAT3 {

	public static int messLength = 0;

	public static void main(String[] args) {

		System.out.println("Enter the Plain text");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		System.out.println("Plain Text: " + word);

		String binary = convertToBinary(word);
		messLength = binary.length();
		calculateMod(word, binary);

	}

	public static String convertToBinary(String word) {

		byte[] bytes = word.getBytes();
		StringBuilder binary = new StringBuilder();

		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}

		return binary.toString();

	}

	public static void calculateMod(String word, String binary) {

		int binaryMessageLength = word.length() * 8 - 8;
		String endBitLength = calculateMessageLength(binaryMessageLength + 8);
		int subMod = endBitLength.length();
		int temp = (binaryMessageLength) % 512;

		if (432 - temp < 0) {
			int x = 512 - temp;
			temp = x + 440 + temp + 64;
		} else {
			temp = 432 - temp;
		}

		int binaryZeros = temp;
		String onePadded = "10000000"; 
		binary = binary.replaceAll("\\s+", "");
		createMessageLength(binary, onePadded, binaryZeros, endBitLength);

	}

	public static String calculateMessageLength(int bitLength) {

		String tempBitsLength = Integer.toBinaryString(bitLength);
		StringBuilder sb = new StringBuilder(tempBitsLength);
		int temp = 64 - tempBitsLength.length();

		while (temp > 0) {
			sb.insert(0, 0);
			temp--;
		}

		return sb.toString();

	}

	public static String createMessageLength(String message, String paddedOne, int zeros, String endLength) {

		StringBuilder messageBinary = new StringBuilder(message);
		messageBinary.insert(messageBinary.toString().length(), paddedOne);

		while (zeros > 0) {
			messageBinary.insert(messageBinary.toString().length(), 0);
			zeros--;
		}

		messageBinary.insert(messageBinary.toString().length(), endLength);
		String m = printMessage(messageBinary.toString());
		m = m.replaceAll("\\s+", "");
		int[] mArray = new int[m.toString().length() / 32];

		for (int i = 0; i < m.toString().length(); i += 32) {
			mArray[i / 32] = Integer.valueOf(m.substring(i + 1, i + 32), 2);
			if (m.charAt(i) == '1') {
				mArray[i / 32] |= 0X80000000;
			}

		}

		hash(mArray);
		return messageBinary.toString();

	}

	public static String printMessage(String message) {

		StringBuilder sb = new StringBuilder(message);
		int num = message.length();

		while (num > 0) {
			if (num % 32 == 0) {
				sb.insert(num, " ");
			}
			num--;
		}

		return sb.toString();

	}

	private static int leftrotate(int x, int shift) {

		return ((x << shift) | (x >>> (32 - shift)));

	}

	private static int h1 = 0x67452301;
	private static int h2 = 0xEFCDAB89;
	private static int h3 = 0x98BADCFE;
	private static int h4 = 0x10325476;
	private static int h5 = 0xC3D2E1F0;
	private static final int[] TABLE_T = new int[80]; // md5 for K1,k2,k3,k4 values
	static {
		for (int i = 0; i < 80; i++)
			TABLE_T[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
	}

	private static String hash(int[] z) {
		int integer_count = z.length;
		int[] intArray = new int[80];
		int j = 0;

		for (int i = 0; i < integer_count; i += 16) {
			for (j = 0; j <= 15; j++)
				intArray[j] = z[j + i];
			for (j = 16; j <= 79; j++) {
				intArray[j] = leftrotate(intArray[j - 3] ^ intArray[j - 8] ^ intArray[j - 14] ^ intArray[j - 16], 1);
			}
			int A = h1;
			int B = h2;
			int C = h3;
			int D = h4;
			int E = h5;
			int t = 0;
			for (int x = 0; x <= 19; x++) {
				t = leftrotate(A, 5) + ((A & B) | (C & D)) + E + intArray[x] + TABLE_T[i]; // f1(A,B,C,D) = (A AND B) OR (C AND D)
				E = D;
				D = C;
				C = leftrotate(B, 30);
				B = A;
				A = t;
			}
			for (int b = 20; b <= 39; b++) {
				t = leftrotate(A, 5) + ((A & B) | (B & C) | (C & D)) + E + intArray[b] + TABLE_T[i]; //f2(A,B,C,D) = (A AND B) OR (B AND C)  OR (C AND D)
				E = D;
				D = C;
				C = leftrotate(B, 30);
				B = A;
				A = t;
			}
			for (int c = 40; c <= 59; c++) {
				t = leftrotate(A, 5) + (((~A) & B) | ((~B) & C) ^ D) + E + intArray[c] + TABLE_T[i]; // f3(A,B,C,D) = (~A AND B) OR (~B AND C) XOR D
				E = D;
				D = C;
				C = leftrotate(B, 30);
				B = A;
				A = t;
			}
			for (int d = 60; d <= 79; d++) {
				t = leftrotate(A, 5) + (A ^ B ^ C ^ D) + E + intArray[d] + TABLE_T[i]; // A XOR B XOR C XOR D
				E = D;
				D = C;
				C = leftrotate(B, 30);
				B = A;
				A = t;
			}
			h1 += A;
			h2 += B;
			h3 += C;
			h4 += D;
			h5 += E;
		}
		String h1Length = Integer.toHexString(h1);
		String h2Length = Integer.toHexString(h2);
		String h3Length = Integer.toHexString(h3);
		String h4Length = Integer.toHexString(h4);
		String h5Length = Integer.toHexString(h5);

		if (h1Length.length() < 8) {
			StringBuilder h1L = new StringBuilder(h1Length);
			h1L.insert(0, 0);
			h1Length = h1L.toString();
		} else if (h2Length.length() < 8) {
			StringBuilder h2L = new StringBuilder(h2Length);
			h2L.insert(0, 0);
			h2Length = h2L.toString();
		} else if (h3Length.length() < 8) {
			StringBuilder h3L = new StringBuilder(h3Length);
			h3L.insert(0, 0);
			h3Length = h3L.toString();
		} else if (h4Length.length() < 8) {
			StringBuilder h4L = new StringBuilder(h4Length);
			h4L.insert(0, 0);
			h4Length = h4L.toString();
		} else if (h5Length.length() < 8) {
			StringBuilder h5L = new StringBuilder(h5Length);
			h5L.insert(0, 0);
			h5Length = h5L.toString();
		}

		String hash = h1Length + h2Length + h3Length + h4Length + h5Length;
		System.out.println("Result: " + hash);
		return null;
	}
}
