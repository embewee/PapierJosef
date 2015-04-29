package hardcode.papierjosef.model.document;

/**
 * A HumbugException is thrown by the DocumentModel, if an action to the modes does not make sense. 
 */
public class HumbugException extends Exception {

	/**
	 * Creates a new HumbugException.
	 * @param msg String: A textual message which may be shown.
	 */
	public HumbugException(String msg) {
		super(msg);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4336719803083785615L;

}
