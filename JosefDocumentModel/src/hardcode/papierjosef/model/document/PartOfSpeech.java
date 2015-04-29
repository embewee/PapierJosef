package hardcode.papierjosef.model.document;

/**
 * Represents the part of speech of a Word. A PartOfSpeech object contains a concrete and unique String, which
 * describes this part of speech. It may contain an optional description for this part of speech.
 */
public class PartOfSpeech {
	
	private String partOfSpeech;
	private String description;
	
	/**
	 * Creates a new part of speech object
	 * @param partOfSpeech String: the part of speech's name
	 */
	public PartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
		description = "";
	}
	
	/**
	 * Creates a new part of speech object
	 * @param partOfSpeech String: the part of speech's name
	 * @param description String: textual description of the part of speech
	 */
	public PartOfSpeech(String partOfSpeech, String description) {
		this.partOfSpeech = partOfSpeech;
		this.description = description;
	}
	
	/**
	 * Returns the part of speech's name
	 * @return String: The part of speech's name
	 */
	public String getPartOfSpeechName() {
		return partOfSpeech;
	}
	
	/**
	 * Returns the part of speech's description
	 * @return String: The part of speech's description or an empty String, if no description is present.
	 */
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return partOfSpeech + ": " + description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PartOfSpeech) {
			return equals((PartOfSpeech) obj);
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true, if this part of speech's getText() equals the given object's getText().
	 * @param pos PartOfSpeech.
	 * @return boolean
	 */
	public boolean equals(PartOfSpeech pos) {
		return pos.getPartOfSpeechName().equals(partOfSpeech);
	}
	
}
