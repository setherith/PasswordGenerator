package core;

import java.util.Random;

public class Password {
	
	private static final int MAX_BITS = 127;
	
	public static String Generate(int length, boolean upper, boolean number, boolean special) {
		// TODO: consider vowels only option?
		
				// all printable = 33 -> 126
				boolean bits[] = new boolean[MAX_BITS];
				
				// lower case letter = 97 -> 122		
				for (int b = 97; b < 123; b++) {
					bits[b] = true;
				}
				
				if (upper) {
					// upper case letter = 65 -> 90
					for (int b = 65; b < 91; b++) {
						bits[b] = true;
					}
				}
				
				if (number) {
					// numbers = 48 -> 57
					for (int b = 48; b < 58; b++) {
						bits[b] = true;
					}
				}
				
				if (special) {
					// specials = 33 -> 47, 58 -> 64, 91 -> 96, 123 -> 126
					for (int b = 33; b < 48; b++) {
						bits[b] = true;
					}
					for (int b = 58; b < 65; b++) {
						bits[b] = true;
					}
					for (int b = 91; b < 97; b++) {
						bits[b] = true;
					}
					for (int b = 123; b < 127; b++) {
						bits[b] = true;
					}
				}
				
				String result = "";
				Random r = new Random();
				for (int x = 0; x < length; x++) {
					boolean bit = false;
					int g = 0;
					while (!bit) {
						g = r.nextInt(MAX_BITS);
						bit = bits[g];
					}
					result += (char) g;
				}
				return result;
	}
	
}
