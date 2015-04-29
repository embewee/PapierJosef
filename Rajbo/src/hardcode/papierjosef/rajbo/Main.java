package hardcode.papierjosef.rajbo;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File applicationDirectory = new File(System.getProperty("user.dir"));
		new Application(applicationDirectory);
	}
}
