package hardcode.papierjosef.bibliothek.operation.rules.nlp;

import hardcode.papierjosef.bibliothek.operation.TextHaecksler;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Token;

public class NlpAbschluss extends TextHaecksler<Document> {

	/**
	 * TODO: Hier noch Residuals und Punctuation einfügen
	 */
	@Override
	public void fuehreAus(Document d) {

		/*
		 * HIER MUSS DAS GEMACHT WERDEN
		 */

		int pos = 0;
		for (Paragraph p : d.getChildElements()) {
			for (Sentence s : p.getChildElements()) {
				for (Token t : s.getChildElements()) {
					t.setStart(pos);
					t.setEnd(t.getText().length() + pos);
					pos += t.getEnd();
				}
				s.recalcStartEnd();
			}
			p.recalcStartEnd();
		}
		d.recalcStartEnd();
	}
}
