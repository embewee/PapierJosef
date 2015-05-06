package hardcode.papierjosef.bibliothek.operation.rules.nlp;

import hardcode.papierjosef.bibliothek.operation.TextHaecksler;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Residual;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Token;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NlpAbschluss extends TextHaecksler<Document> {

	List<Residual> residuals;

	public List<Residual> getResiduals() {
		return residuals;
	}

	public void setResiduals(List<Residual> residuals) {
		this.residuals = residuals;
	}

	/**
	 * 
	 */
	@Override
	public void execute(Document d) {
		int pos = 0;
		if (residuals != null && residuals.size() > 1) {
			Iterator<Residual> rit = residuals.iterator();

			for (Paragraph p : d.getChildElements()) {
				for (Sentence s : p.getChildElements()) {
					ListIterator<Token> it = s.getChildElements()
							.listIterator();
					Token t = it.next();
					Residual r = rit.next();

					while (it.hasNext()) {
						if (rit.hasNext()) {
							if (r.getStart() <= pos) {
								it.add(r);
								r = rit.next();
							}
						}
						pos += t.getText().length() + 1;
						t = it.next();
					}
				}
			}
		}

		pos = 0;
		for (Paragraph p : d.getChildElements()) {
			for (Sentence s : p.getChildElements()) {
				for (Token t : s.getChildElements()) {
					t.setStart(pos);
					t.setEnd(t.getText().length() + pos);
					pos = (int) t.getEnd();
				}
				s.recalcStartEnd();
			}
			p.recalcStartEnd();
		}
		d.recalcStartEnd();
	}
}
