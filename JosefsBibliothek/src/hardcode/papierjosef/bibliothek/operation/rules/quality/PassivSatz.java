package hardcode.papierjosef.bibliothek.operation.rules.quality;

import java.util.Arrays;
import java.util.List;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class PassivSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {
		for (Word w : t.getWordsOnly()) {
			if (w.getPartOfSpeech().getPartOfSpeechName().equals("VAFIN")
					&& (w.getText().startsWith("wurd")
							|| w.getText().startsWith("würd") || w.getText()
							.startsWith("wird")))
				t.addProperty(new TextElementProperty("PASSIVE_SENTENCE",
						"Passivsätze sind nach Möglichkeit in ihrer Anzahl zu minimieren."));
		}
	}

	@Override
	public List<String> getProperties() {
		return Arrays.asList("PASSIVE_SENTENCE");
	}

	@Override
	public Class<? extends TextElement> getLevel() {
		return Sentence.class;
	}

}
