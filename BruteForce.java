package Algorithm;

import java.util.Scanner;

public class BruteForce {
	public static void main(String args[]) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println(" Enter cipher text message : ");
			String c = sc.nextLine();
			for (int k = 0; k <= 25; k++) {
				System.out.println("Key : " + k);
				String d = "";
				for (int i = 0; i < c.length(); i++) {
					char a = c.charAt(i);
					if (a >= 'a' && a <= 'z') {
						a = (char) (a - k);
						if (a < 'a') {
							a = (char) (a - 'a' + 'z' + 1);
						}
						d = d + a;
					} else if (a >= 'A' && a <= 'Z') {
						a = (char) (a - k);
						if (a < 'A') {
							a = (char) (a - 'A' + 'Z' + 1);
						}
						d = d + a;
					} else {
						d = d + a;
					}
				}
				System.out.println(" decrypt message : " + d);
			}
		}
	}

}
