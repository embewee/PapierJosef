package hardcode.papierjosef.bibliothek.operation.instance;

import hardcode.papierjosef.bibliothek.operation.base.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class PassivSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {
		for (Word w : t.getChildElements()) {
			if (w.getPartOfSpeech().getPartOfSpeechName().equals("VAFIN")
					&& (w.getText().startsWith("wurd")
							|| w.getText().startsWith("würd") || w.getText()
							.startsWith("wird")))
				t.addProperty(new TextElementProperty("PASSIVE_SENTENCE",
						"Passivsätze sind nach Möglichkeit in ihrer Anzahl zu minimieren."));
		}
	}

}
