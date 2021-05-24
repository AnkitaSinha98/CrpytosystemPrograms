package Cryptosystem;

import java.math.BigInteger;
import java.util.Scanner;
import java.math.*;

public class HillCipherCryptoanalysis {
	static String alphabets[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the sie of plaintext you know ");
		int size = input.nextInt();
		System.out.println("Enter the plaintext you know: ");
		input.nextLine();
		String plaintext = input.nextLine().toLowerCase();
		System.out.println("Enter the corresponding ciphertext you know: ");
		String ciphertext = input.nextLine().toLowerCase();

		System.out.println("P: " + plaintext);
		System.out.println("C: " + ciphertext);

		System.out.println("Enter the size of key ");
		int key_n = input.nextInt();
		int key[][] = new int[key_n][key_n];
		double[][] mat = new double[key_n][key_n];
		double[][] constants = new double[key_n][1];

		for (int k = 0; k < key_n; k++) {
			int count = 0;
			for (int i = 0; i < key_n; i++) {
				for (int j = 0; j < key_n; j++) {

					mat[i][j] = plaintext.charAt((i * key_n) + j) - 96;
				}

				constants[i][0] = ciphertext.charAt(k + (count * key_n)) - 96;
				count++;
			}

			double getresult[][] = find_key(key_n, mat, constants);
			for (int k1 = 0; k1 < key_n; k1++) {
				key[k][k1] = (int) getresult[k1][0];
			}
		}
		System.out.println("KEY is: ");
		for (int i = 0; i < key_n; i++) {
			for (int j = 0; j < key_n; j++) {
				System.out.print(key[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static double[][] find_key(int n, double mat[][], double constants[][]) {
		char[] var = { 'x', 'y', 'z', 'w' };

		double inverted_mat[][] = invert(mat, n);

		double result[][] = new double[n][1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < n; k++) {
					result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
					result[i][j] = result[i][j] % 26;
				}
			}
		}

		return result;
	}

	public static double[][] invert(double a[][], int n) {
		double x[][] = new double[n][n];
		BigInteger[][] key2 = new BigInteger[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				String temp = String.valueOf((int) a[i][j]);
				key2[i][j] = BigInteger.valueOf(Integer.valueOf(temp));

			}
		}

		ModMatrix obj2 = new ModMatrix(key2);
		ModMatrix inverse2 = obj2.inverse(obj2);

		for (int i = 0; i < inverse2.getNrows(); i++) {
			for (int j = 0; j < inverse2.getNcols(); j++) {

				x[i][j] = inverse2.getData()[i][j].doubleValue();
			}

		}
		return x;

	}

	public static void gaussian(double a[][], int index[]) {
		int n = index.length;
		double c[] = new double[n];

		for (int i = 0; i < n; ++i)
			index[i] = i;

		for (int i = 0; i < n; ++i) {
			double c1 = 0;
			for (int j = 0; j < n; ++j) {
				double c0 = Math.abs(a[i][j]);
				if (c0 > c1)
					c1 = c0;
			}
			c[i] = c1;
		}

		int k = 0;
		for (int j = 0; j < n - 1; ++j) {
			double pi1 = 0;
			for (int i = j; i < n; ++i) {
				double pi0 = Math.abs(a[index[i]][j]);
				pi0 /= c[index[i]];
				if (pi0 > pi1) {
					pi1 = pi0;
					k = i;
				}
			}

			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i = j + 1; i < n; ++i) {
				double pj = a[index[i]][j] / a[index[j]][j];

				a[index[i]][j] = pj;

				for (int l = j + 1; l < n; ++l)
					a[index[i]][l] -= pj * a[index[j]][l];
			}
		}
	}
}

class ModMatrix {
	private int nrows;
	private int ncols;
	private BigInteger[][] data;
	private final BigInteger mod = new BigInteger("26");

	public ModMatrix(BigInteger[][] dat) {
		this.data = dat;
		this.nrows = dat.length;
		this.ncols = dat[0].length;
	}

	public ModMatrix(int nrow, int ncol) {
		this.nrows = nrow;
		this.ncols = ncol;
		data = new BigInteger[nrow][ncol];
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public BigInteger[][] getData() {
		return data;
	}

	public void setData(BigInteger[][] data) {
		this.data = data;
	}

	public BigInteger getValueAt(int i, int j) {
		return data[i][j];
	}

	public void setValueAt(int i, int j, BigInteger value) {
		data[i][j] = value;
	}

	public int size() {
		return ncols;
	}

	public static ModMatrix transpose(ModMatrix matrix) {
		ModMatrix transposedMatrix = new ModMatrix(matrix.getNcols(), matrix.getNrows());
		for (int i = 0; i < matrix.getNrows(); i++) {
			for (int j = 0; j < matrix.getNcols(); j++) {
				transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return transposedMatrix;
	}

	public static BigInteger determinant(ModMatrix matrix) {

		if (matrix.size() == 1) {
			return matrix.getValueAt(0, 0);
		}
		if (matrix.size() == 2) {

			return (matrix.getValueAt(0, 0).multiply(matrix.getValueAt(1, 1)))
					.subtract((matrix.getValueAt(0, 1).multiply(matrix.getValueAt(1, 0))));
		}
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < matrix.getNcols(); i++) {
			sum = sum.add(changeSign(i)
					.multiply(matrix.getValueAt(0, i).multiply(determinant(createSubMatrix(matrix, 0, i)))));
		}
		return sum;
	}

	private static BigInteger changeSign(int i) {
		if (i % 2 == 0) {
			return new BigInteger("1");
		} else {
			return new BigInteger("-1");
		}
	}

	public static ModMatrix createSubMatrix(ModMatrix matrix, int excluding_row, int excluding_col) {
		ModMatrix mat = new ModMatrix(matrix.getNrows() - 1, matrix.getNcols() - 1);
		int r = -1;
		for (int i = 0; i < matrix.getNrows(); i++) {
			if (i == excluding_row) {
				continue;
			}
			r++;
			int c = -1;
			for (int j = 0; j < matrix.getNcols(); j++) {
				if (j == excluding_col) {
					continue;
				}
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}

	public ModMatrix cofactor(ModMatrix matrix) {
		ModMatrix mat = new ModMatrix(matrix.getNrows(), matrix.getNcols());
		for (int i = 0; i < matrix.getNrows(); i++) {
			for (int j = 0; j < matrix.getNcols(); j++) {
				mat.setValueAt(i, j,
						(changeSign(i).multiply(changeSign(j)).multiply(determinant(createSubMatrix(matrix, i, j))))
								.mod(mod));
			}
		}

		return mat;
	}

	public ModMatrix inverse(ModMatrix matrix) {
		return (transpose(cofactor(matrix)).dc(determinant(matrix)));
	}

	private ModMatrix dc(BigInteger d) {
		BigInteger inv = d.modInverse(mod);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				data[i][j] = (data[i][j].multiply(inv)).mod(mod);
			}
		}
		return this;
	}

}
