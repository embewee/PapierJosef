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
}
