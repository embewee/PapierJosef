package hardcode.papierjosef.bibliothek;

import java.io.File;
import java.io.IOException;

public interface LibraryFacade {
	
	public void readDocument(File file) throws IOException;
	public String evaluate();
	public void analyze();

}
