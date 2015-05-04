package hardcode.papierjosef.bibliothek.loader.documentloader;

public class LoadedDocument {
	private String content;
	private String fileExtension;

	protected LoadedDocument(String content, String fileExtension) {
		this.content = content;
		this.fileExtension = fileExtension;
	}

	public String getText() {
		return content;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public AcceptedFileType getFileType() {
		switch (fileExtension) {
		case ".txt":
			return AcceptedFileType.TXT;
		case ".tex":
			return AcceptedFileType.TEX;
		case ".doc":
		case ".docx":
			return AcceptedFileType.WORD;
		}
		return null;
	}
}
