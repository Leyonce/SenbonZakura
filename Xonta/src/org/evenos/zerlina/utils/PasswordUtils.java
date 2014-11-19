package org.evenos.zerlina.utils;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

/**
 * Helper class which is used for en-/decryption and other password related stuff
 * 
 * @author Jan Thielemann
 * 
 */
public class PasswordUtils {

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	private static int pswdIterations = 2048;

	private static int keySize = 256;

	/**
	 * Encrypts a given text with a given password. The return value is an array of Strings containing the encrypted text (0), the salt (1)
	 * and the initialization vector (2)
	 * 
	 * @param plainText
	 * @param password
	 * @return String[]
	 * @throws Exception
	 */
	public static String[] encrypt(String plainText, String password) throws Exception {

		// Get the salt
		String salt = generateSalt();
		byte[] saltBytes = salt.getBytes("UTF-8"); // Convert bytes to string

		// Derive the key
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, pswdIterations, keySize);

		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		// Encrypt the message
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);

		AlgorithmParameters params = cipher.getParameters();
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

		// Store the encrypted key, the salt and the initialization vector in an array
		String[] retVal = new String[3];
		retVal[0] = Base64.encodeToString(encryptedTextBytes, Base64.NO_WRAP); // Convert bytes to string
		retVal[1] = salt;
		retVal[2] = Base64.encodeToString(ivBytes, Base64.NO_WRAP); // Convert bytes to string
		return retVal;
	}

	/**
	 * Decrypts a given encrypted text with a given password, salt and initialization vector
	 * 
	 * @param encryptedText
	 * @param password
	 * @param salt
	 * @param initializationVector
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText, String password, String salt, String initializationVector) throws Exception {

		// Convert Strings back to byte arrays
		byte[] saltBytes = salt.getBytes("UTF-8");
		byte[] encryptedTextBytes = Base64.decode(encryptedText, Base64.NO_WRAP);
		byte[] ivBytes = Base64.decode(initializationVector, Base64.NO_WRAP);

		// Derive the key
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, pswdIterations, keySize);

		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		// Decrypt the message
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));

		byte[] decryptedTextBytes = null;
		try {
			decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return new String(decryptedTextBytes);
	}

	private static String generateSalt() {
		// Generate a random salt
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String s = new String(bytes);
		return s;
	}

	/**
	 * Get the strength of a password as an integer between 0 and 5. Each of the following adds 1 to the strength -Password has more than 5
	 * letters -Password has more than 8 letters -Password contains upper case letters -Password contains lower case letters -Password
	 * contains numbers
	 * 
	 * @param password
	 * @return
	 */
	public static int getStrength(String password) {
		if (password == null)
			return 0;

		int passwordStrength = 0;
		if (password.length() > 5)
			passwordStrength++;

		if (password.toLowerCase() != password)
			passwordStrength++;

		if (password.toUpperCase() != password)
			passwordStrength++;

		if (password.length() > 8)
			passwordStrength++;

		if (getNumberDigits(password) > 0)
			passwordStrength++;

		return passwordStrength;
	}

	public static int getNumberDigits(String password) {
		if (password == null || password.length() == 0)
			return 0;

		int numDigits = 0;
		int length = password.length();
		for (int i = 0; i < length; i++)
			if (Character.isDigit(password.charAt(i)))
				numDigits++;

		return numDigits;
	}

}
