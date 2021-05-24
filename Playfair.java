package Cryptosystem;
import java.util.*;
	  
public	class Playfair {
	
	//Main Function
	public static void main(String[] args)
	  {
	    Scanner sc = new Scanner(System.in);
	    Playfair cipher = new Playfair();
	    String key = cipher.getKey();
	    
	    String plaintext = cipher.getPlainText();
	    String encryptedText = cipher.Encryption(plaintext, key);
	    System.out.println("Encrypted Text "+ encryptedText);

	    String ciphertext = cipher.getCipherText();
	    String decryptedText = cipher.Decryption(ciphertext, key);
	    System.out.println("Decrypted Text "+ decryptedText);
	    
	  }
	
	  public String getKey()
	  {
		 Scanner sc = new Scanner(System.in); 
		System.out.println("Enter the key");
		String key = sc.nextLine();
		  
	        key = key.toUpperCase();
	        int l=key.length();
		int n=0; 
		  
		for(int i=0; i<l-1; i++)
		{
		    char ch=key.charAt(i);
		    for(int j=i+1; j<l; j++)
		    {
		        char ch1=key.charAt(j);
		        if(ch==ch1)
		        {
		            n++;
		            break;
		        }
		    }
		}
		
		if(n>=1)
		{
		    System.out.println("It is not a unique key. Therefore, invalid key.");
		    return getKey();
		}

	    return key;
	  }
	  public String getPlainText()
	  {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter plaintext: ");
	    String text = sc.nextLine();
	    text = text.toUpperCase();

	    return text;
	  }
	  public String getCipherText()
	  {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter ciphertext: ");
	    String text = sc.nextLine();
	    text =  text.trim();
	    text = text.toUpperCase();

	    return text;
	  }
	  private String Encryption(String plainText, String key)
	  {
	    char[][] keyMatrix = makingMatrix(key);
	    String modified_plainText = "";
	    String randomCharacter = "Q";
	    for(int i=0; i<plainText.length(); i++)
	    {
	      if(plainText.charAt(i)>=65 && plainText.charAt(i)<=91)
	      {
	        if(i%2==1 && plainText.charAt(i)==plainText.charAt(i-1))
	        {
	          modified_plainText = modified_plainText.concat(randomCharacter);
	        }
	        else
	        {
	          modified_plainText = modified_plainText + plainText.charAt(i);
	        }
	      }
	    }
	    if(modified_plainText.length()%2==1)
	    {
	      modified_plainText = modified_plainText.concat(randomCharacter);
	    }
	    String cryptedText = "";
	    for(int i=0; i<modified_plainText.length()-1; i+=2)
	    {
	      int location1 = findCharInMatrix(modified_plainText.charAt(i), keyMatrix);
	      int location2 = findCharInMatrix(modified_plainText.charAt(i+1), keyMatrix);
	      if(location1/10==location2/10)
	      {
	        char crypt1 = nextCharInRow(location1, keyMatrix);
	        char crypt2 = nextCharInRow(location2, keyMatrix);
	        cryptedText = cryptedText+crypt1+crypt2;
	      }
	      else if(location1%10==location2%10)
	      {
	        char crypt1 = nextCharInColumn(location1, keyMatrix);
	        char crypt2 = nextCharInColumn(location2, keyMatrix);
	        cryptedText = cryptedText+crypt1+crypt2;
	      }
	      else
	      {
	        char crypt1 = charAtIntersection(location1, location2, keyMatrix);
	        char crypt2 = charAtIntersection(location2, location1, keyMatrix);
	        cryptedText = cryptedText+crypt1+crypt2;
	      }
	    }
	    return cryptedText;
	  }
	  private String Decryption(String cryptedText, String key)
	  {
	    char[][] keyMatrix = makingMatrix(key);
	    String realText = "";
	    for(int i=0; i<cryptedText.length()-1; i+=2)
	    {
	      int location1 = findCharInMatrix(cryptedText.charAt(i), keyMatrix);
	      int location2 = findCharInMatrix(cryptedText.charAt(i+1), keyMatrix);
	      if(location1/10==location2/10)
	      {
	        char decrypt1 = preCharInRow(location1, keyMatrix);
	        char decrypt2 = preCharInRow(location2, keyMatrix);
	        realText = realText + decrypt1 + decrypt2;
	      }
	      else if(location1%10==location2%10)
	      {
	        char decrypt1 = preCharInColumn(location1, keyMatrix);
	        char decrypt2 = preCharInColumn(location2, keyMatrix);
	        realText = realText + decrypt1 + decrypt2;
	      }
	      else
	      {
	        char decrypt1 = charAtIntersection(location1, location2, keyMatrix);
	        char decrypt2 = charAtIntersection(location2, location1, keyMatrix);
	        realText = realText + decrypt1 + decrypt2;
	      }
	    }
	    if(realText.charAt(realText.length()-1)=='Q')
	    {
	      realText = realText.substring(0, realText.length()-1);
	    }
	    String originalText = "";
	    for(int i=0; i<realText.length(); i++)
	    {
	      if(realText.charAt(i)!='Q')
	      {
	        originalText = originalText+realText.charAt(i);
	      }
	      else if(realText.charAt(i-1)!=realText.charAt(i+1))
	      {
	        originalText = originalText+realText.charAt(i);
	      }
	    }
	    return originalText;
	  }
	  public static char[][] makingMatrix(String key)
	  {
	    char[][] matrix = new char[5][5];
	    int length = 0;
	    for(int i=0; i<key.length(); i++)
	    {
	      char currentCharacter = key.charAt(i);
	      int firstIndex = key.indexOf(currentCharacter);
	      if(firstIndex==i)
	      {
	        matrix[length/5][length%5] = currentCharacter;
	        length++;
	      }
	      
	    }
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXY";
	    for(int i=0; i<25; i++)
	    {
	      if(key.indexOf(alphabet.charAt(i))==-1)
	      {
	        matrix[length/5][length%5] = alphabet.charAt(i);
	        length++;
	      }
	    }
	    
	    return matrix;
	  }
	  public static int findCharInMatrix(char z, char[][] matrix)
	  {
	    int number = 0;
	    for(int i=0; i<5; i++)
	    {
	      for(int j=0; j<5; j++)
	      {
	        if(matrix[i][j]==z)
	        {
	          number = i*10;
	          number = number+j;
	          break;
	        }
	      }
	    }
	    return number;
	  }
	  public static char nextCharInRow(int coordinates, char[][] matrix)
	  {
	    int i = coordinates/10;
	    int j = coordinates%10;
	    if(j==4)
	    {
	      return matrix[i][0];
	    }
	    return matrix[i][j+1];
	  }
	  public static char nextCharInColumn(int coordinates, char[][] matrix)
	  {
	    int i = coordinates/10;
	    int j = coordinates%10;
	    if(i==4)
	    {
	      return matrix[0][j];
	    }
	    return matrix[i+1][j];
	  }
	  public static char charAtIntersection(int pos1, int pos2, char[][] matrix)
	  {
	    int i1 = pos1/10;
	    int j1 = pos1%10;
	    int i2 = pos2/10;
	    int j2 = pos2%10;
	    return matrix[i1][j2];
	  }
	  public static char preCharInRow(int coordinates, char[][] matrix)
	  {
	    int i = coordinates/10;
	    int j = coordinates%10;
	    if(j==0)
	    {
	        return matrix[i][4];
	    }
	    return matrix[i][j-1];
	  }
	  public static char preCharInColumn(int coordinates, char[][] matrix)
	  {
	    int i = coordinates/10;
	    int j = coordinates%10;
	    if(i==0)
	    {
	        return matrix[4][j];
	    }
	    return matrix[i-1][j];
	  }
	}
