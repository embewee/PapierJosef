package hardcode.papierjosef.model.document;

import java.util.List;

/**
 * Represents a Document. A Document contains Paragraphs. 
 * 
 * The whole TextElement tree is composed as follows:
 * 
 * Document
 * |
 * +- Paragraph
 *    |
 *    +- Sentence
 *       |
 *       +- Word
 */
public class Document extends TextElement<Paragraph> {

	/**
	 * Creates a new Document.
	 * @param list List of Paragraphs
	 * @param start long: position of the first char in the list, relative to the whole document
	 * @param end long: position of the last char in the list, relative to the whole document
	 * @throws HumbugException if an action to the document model does not make sense.
	 */
	public Document(List<Paragraph> list, long start, long end)	throws HumbugException {
		super(list, start, end);
	}

	@Override
	public String getText() {
		StringBuffer buf = new StringBuffer();
		for (Paragraph paragraph: getChildElements())
			buf.append(paragraph.getText());
		return buf.toString();
	}
	
}
