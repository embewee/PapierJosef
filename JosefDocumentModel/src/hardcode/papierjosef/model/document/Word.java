package hardcode.papierjosef.model.document;

import java.util.List;

/**
 * Represents a Word. A Word contains a concrete word as String in the child elements list. It also contains
 * the words part of speech attribute and an appendix to the word, which may be a space or another String.
 * Words are contained by Sentences.  
 */
public class Word extends TextElement<String> {
	private PartOfSpeech pos;
	private String appendix;

	/**
	 * Creates a new Word.
	 * @param list List containing <b>exactly one</b> String, the concrete word's spelling.
	 * @param start long: position of the first char in the list, relative to the whole document
	 * @param end long: position of the last char in the list, relative to the whole document
	 * @throws HumbugException if the list contains more than one elements.
	 */
	public Word(List<String> list, long start, long end, PartOfSpeech pos)
			throws HumbugException {
		super(list, start, end);

		if (list.size() != 1) {
			throw new HumbugException("Invalid number of list elements.");
		}

		this.pos = pos;
	}
	
	/**
	 * Returns the appendix for this word.<p>
	 * An appendix is a String which follows the concrete word. It may be space or another String, which is not a word.
	 * In other words: an Appendix describes the gap between two word. 
	 * @return String: the appendix
	 */
	public String getAppendix() {
		return appendix;
	}

	/**
	 * Sets the appendix for this word.<p>
	 * An appendix is a String which follows the concrete word. It may be space or another String, which is not a word.
	 * In other words: an Appendix describes the gap between two word. 
	 * @param appendix String: the appendix
	 */
	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}

	/**
	 * Returns the words spelling.
	 * @return String: The word's spelling.
	 */
	public String getWord() {
		return getChildElements().get(0);
	}

	/**
	 * Returns this word's part of speech.
	 * @return PartOfSpeech
	 */
	public PartOfSpeech getPartOfSpeech() {
		return pos;
	}

	@Override
	public String getText() {
		return getChildElements().get(0) + appendix;
	}
}
