package hardcode.papierjosef.bibliothek.operation.rules.nlp;

import java.util.Arrays;

import hardcode.papierjosef.bibliothek.operation.TextHaecksler;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.Paragraph;

public class Paragraphizer extends TextHaecksler<Document> {

	@Override
	public void fuehreAus(Document t) {
		String rawText = getRawText();
		String p[] = rawText.split("\\n\\n");
		try {
			for (String paragraph : p)
				t.addElement(new Paragraph(null, 0, 0), 0);
			setOutput(Arrays.asList(p));
		} catch (HumbugException e) {
			e.printStackTrace();
		}
		
	}

}
