package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class UnpersoenlichesPronomen extends Regel<Word> {

	@Override
	public void fuehreAus(Word w) {
		if (w.getWord().toLowerCase().equals("man")) {
			w.addProperty(new TextElementProperty("UNPERS_PRONOUN",
					"'man' als Indefinitpronomen ziemt sich nicht."));
		}
	}

	public String getProperty() {
		return "UNPERS_PRONOUN";
	}

}
