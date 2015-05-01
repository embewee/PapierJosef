package hardcode.papierjosef.bibliothek.loader.documentloader;

import hardcode.papierjosef.bibliothek.exception.LibraryException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DocumentLoader {

	private DocumentLoader() {}

	public static LoadedDocument loadFile(File file) throws IOException, LibraryException {
		String extension = file.getName().substring(file.getName().lastIndexOf(".")).toLowerCase();
		
		switch(extension) {
			case "txt":
				String content = loadPlainTextFile(file);
				LoadedDocument ld = new LoadedDocument(content, extension);
				return ld;
			
			default:
				throw new LibraryException("File type '" + extension + "' not supported.");
		}
	}

	private static String loadPlainTextFile(File file) throws IOException {
		// TODO: no such file exception
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		return br.toString();
	}
}
