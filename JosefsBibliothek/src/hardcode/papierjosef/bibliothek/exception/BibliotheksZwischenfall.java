package hardcode.papierjosef.bibliothek.exception;

public final class BibliotheksZwischenfall extends Exception {
	
	private static final long serialVersionUID = -720141547918802276L;

	public final static String PREFIX = "#BIB_ERR__";
	private String code;
	
	public BibliotheksZwischenfall(String code) {
		this.code = PREFIX + code;
	}
	
	public String getCode() {
		return code;
	}
	
}
