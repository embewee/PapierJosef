package hardcode.papierjosef.model.document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Sentence. A Sentence contains Words. Sentences are contained by
 * Paragraphs.
 */
public class Sentence extends TextElement<Token> {

	/**
	 * Creates a new Sentence.
	 * 
	 * @param list
	 *            List of Words
	 * @param start
	 *            long: position of the first char in the list, relative to the
	 *            whole document
	 * @param end
	 *            long: position of the last char in the list, relative to the
	 *            whole document
	 * @throws HumbugException
	 *             if an action to the document model does not make sense.
	 */
	public Sentence(List<Token> list, long start, long end)
			throws HumbugException {
		super(list, start, end);
	}

	@Override
	public String getText() {
		StringBuffer buf = new StringBuffer();
		for (Token token : getChildElements()) {
			buf.append(token.getText());
		}
		return buf.toString();
	}

	public List<Word> getWordsOnly() {
		return getChildElements().stream().filter(u -> u instanceof Word)
				.map(c -> (Word) c).collect(Collectors.toList());
	}
}
