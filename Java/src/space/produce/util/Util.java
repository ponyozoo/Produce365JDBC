package space.produce.util;

import java.util.Random;

public class Util {

	public int generateRandomNumber() {
		Random random = new Random();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			sb.append(random.nextInt(10));
		} 
		
		return Integer.parseInt(sb.toString());
	}

}
