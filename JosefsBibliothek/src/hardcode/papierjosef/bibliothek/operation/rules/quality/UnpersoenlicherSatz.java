package hardcode.papierjosef.bibliothek.operation.rules.quality;

import java.util.Arrays;
import java.util.List;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class UnpersoenlicherSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {
		for (Word w : t.getWordsOnly()) {
			if (w.getText().toLowerCase().equals("man")) {
				t.addProperty(new TextElementProperty("UNPERS_SENTENCE",
						"'man' als Subjekt ziemt sich nicht."));
			}
		}
	}

	@Override
	public List<String> getProperties() {
		return Arrays.asList("UNPERS_SENTENCE");
	}

	@Override
	public Class<? extends TextElement> getLevel() {
		return Sentence.class;
	}

}
