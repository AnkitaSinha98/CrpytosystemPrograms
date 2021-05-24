package Cryptosystem;

import java.math.*;
import java.util.Scanner;

public class ModifiedRSA {
	public static void main(String args[]) {
		try (Scanner sc = new Scanner(System.in)) {
			int p, q, r, n, z, z1, z2, d = 0, e, i;
			System.out.println("Enter the number to be Encrypted and Decrypted:");
			int msg = sc.nextInt();
			double c;
			BigInteger msgback;
			System.out.println("Enter 1st prime number p:");
			p = sc.nextInt();
			System.out.println("Enter 2nd prime number q:");
			q = sc.nextInt();
			System.out.println("Enter 3rd prime number r:");
			r = sc.nextInt();

			n = p * q * r;
			z1 = (p - 1) * (q - 1);
			z2 = (q - 1) * (r - 1);
			z = z1 * z2;
			System.out.println("The value of z : " + z);

			for (e = 2; e < z; e++) {
				if (gcd(e, z) == 1) {
					break;
				}
			}
			System.out.println("The value of e (public key exponent) : " + e);
			for (i = 0; i <= 9; i++) {
				int x = 1 + (i * z);
				if (x % e == 0) {
					d = x / e;
					break;
				}
			}
			System.out.println("The value of d (private key exponent) : " + d);
			c = (Math.pow(msg, e)) % n;
			
			System.out.println("Encrypted message is : ");
			System.out.println(c);

			BigInteger N = BigInteger.valueOf(n);

			BigInteger C = BigDecimal.valueOf(c).toBigInteger();
			msgback = (C.pow(d)).mod(N);
			
			System.out.println("Derypted message is : ");
			System.out.println(msgback);
		}

	}

	static int gcd(int e, int z) {
		if (e == 0)
			return z;
		else
			return gcd(z % e, e);
	}
}