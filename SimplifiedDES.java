package Cryptosystem;

import java.util.Scanner;

public class SimplifiedDES {

	public static void main(String args[]) {
		int key[] = new int[10];

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the key :");
		for (int i = 0; i < key.length; i++) {
			key[i] = sc.nextInt();
		}
		System.out.println("After P10 Permutation, The result is as follows :");

		key[0] = key[2];
		key[1] = key[5];
		key[2] = key[1];
		key[3] = key[6];
		key[4] = key[3];
		key[5] = key[9];
		key[6] = key[0];
		key[7] = key[8];
		key[8] = key[7];
		key[9] = key[5];

		for (int i = 0; i < key.length; i++) {
			System.out.print(" " + key[i]);
		}

		System.out.print("\nBit divided into 2 parts : \n");
		int l[] = new int[5];
		int r[] = new int[5];
		for (int i = 0; i < l.length; i++) {
			l[i] = key[i];
		}
		
		System.out.print("\nLeft bit is : ");
		for (int i = 0; i < r.length; i++) {
			System.out.print(" " + l[i]);
		}

		int length = 4;
		for (int j = 0; j < r.length; j++) {
			r[j] = key[++length];
		}
		
		System.out.print("\nRight bit is : ");
		for (int j = 0; j < r.length; j++) {
			System.out.print(" " + r[j]);
		}

		System.out.print("\nShifting both the bits-1 towards left : ");

		for (int i = 0; i < 1; i++) {
			int k, first;
			first = l[0];
			for (k = 0; k < r.length - 1; k++) {
				l[k] = l[k + 1];
			}
			l[k] = first;
		}
		
		System.out.print("\nShifted left bits towards left : ");

		for (int i = 0; i < r.length; i++) {
			System.out.print(" " + l[i]);
		}
		
		for (int i = 0; i < 1; i++) {
			int m, second;
			second = r[0];
			for (m = 0; m < r.length - 1; m++) {
				r[m] = r[m + 1];
			}
			r[m] = second;
		}
		
		System.out.print("\nShifted right bits towards left : ");


		for (int i = 0; i < r.length; i++) {
			System.out.print(" " + r[i]);
		}
		int key1[] = new int[10];
		
		for (int i = 0; i < r.length; i++) {
			key1[i] = l[i];
			}
		
		int length1 = 4;
		for (int j = 0; j < r.length; j++) {
			key1[++length1]=r[j];
		}
		
		System.out.print("\nBefore P8 Permutation, The result is as follows :");

		for (int i = 0; i < key1.length; i++) {
			System.out.print(" "+key1[i]);
		}
		
		System.out.print("\nAfter P8 Permutation, The result is as follows :");
		key1[0] = key1[5];
		key1[1] = key1[2];
		key1[2] = key1[6];
		key1[3] = key1[3];
		key1[4] = key1[7];
		key1[5] = key1[4];
		key1[6] = key1[9];
		key1[7] = key1[8];
	
		for (int i = 0; i < key1.length-2; i++) {
			System.out.print(" "+key1[i]);
		}
		
		System.out.print("\nKey K1:");
		for (int i = 0; i < key1.length-2; i++) {
			System.out.print(" "+key1[i]);
		}

	}
}
