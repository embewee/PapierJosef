package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

public class PassivSatz extends Regel<Sentence> {

	@Override
	public void execute(Sentence t) {
		for (Word w : t.getWordsOnly()) {
			if (w.getPartOfSpeech().getPartOfSpeechName().equals("VAFIN")
					&& (w.getText().startsWith("wurd")
							|| w.getText().startsWith("würd") || w.getText()
							.startsWith("wird")))
				t.addProperty(new TextElementProperty("PASSIVE_SENTENCE",
						"Passivsätze sind nach Möglichkeit in ihrer Anzahl zu minimieren."));
		}
	}
	
	public String getProperty(){
		return "PASSIVE_SENTENCE";
	}
}
