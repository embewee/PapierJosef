package hardcode.papierjosef.bibliothek.operation.instance;

import hardcode.papierjosef.bibliothek.operation.base.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class UnpersoenlicherSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {
		for (Word w : t.getChildElements()) {
			if (w.getText().toLowerCase().equals("man")) {
				t.addProperty(new TextElementProperty("UNPERS_SENTENCE",
						"'man' als Subjekt ziemt sich nicht."));
			}
		}
	}

}
