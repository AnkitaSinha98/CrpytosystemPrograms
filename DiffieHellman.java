package Cryptosystem;

import java.util.*;

class DiffieHellman {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the value of modulo(p): ");
		int p = sc.nextInt();
		
		System.out.println("Enter the primitive root of " + p);
		int g = sc.nextInt();
	
		System.out.println("select 1st secret number for 1st person: ");
		int a = sc.nextInt();
	
		System.out.println("select 2nd secret number for 2nd person:");
		int b = sc.nextInt();
		
		int A = (int) Math.pow(g, a) % p;
		int B = (int) Math.pow(g, b) % p;
		
		int S_A = (int) Math.pow(B, a) % p;
		int S_B = (int) Math.pow(A, b) % p;
		
		if (S_A == S_B) {
			System.out.println("The shared secret key is = " + S_A);
		} else {
			System.out.println("1st person and 2nd person cannot exchange information with each other");
		}
	}
}