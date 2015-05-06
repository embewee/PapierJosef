package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class LangeSaetzeRegel extends Regel<Sentence> {

	public LangeSaetzeRegel() {
		super();
		arg("Satzlaenge", "20");
	}

	@Override
	public void execute(Sentence t) {
		int maxLength = p("Satzlaenge", Integer.class);
		if (t.size() > maxLength) {
			TextElementProperty tep = new TextElementProperty("LONG_SENTENCE",
					"Langer Satz");
			t.addProperty(tep);
		}
	}

	public String getProperty() {
		return "LONG_SENTENCE";
	}
}
