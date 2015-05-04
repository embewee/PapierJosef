package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;

import java.util.Arrays;
import java.util.List;

public class KorrelatSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {

		// for(Word w:t.getChildElements()){
		// w.getText().toLowerCase().equals("es")
		// }
		// t.addProperty(new TextElementProperty("CORRELATIVE_SENTENCE",
		// "Es ziemt sich nicht, 'ES' als Subjekt im Satz einzusetzen."));
	}

	@Override
	public List<String> getProperties() {
		return Arrays.asList("CORRELATIVE_SENTENCE");
	}

	@Override
	public Class<? extends TextElement> getLevel() {
		return Sentence.class;
	}

}
