package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;

public class KorrelatSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {

		// for(Word w:t.getChildElements()){
		// w.getText().toLowerCase().equals("es")
		// }
		// t.addProperty(new TextElementProperty("CORRELATIVE_SENTENCE",
		// "Es ziemt sich nicht, 'ES' als Subjekt im Satz einzusetzen."));
	}
	
	public String getProperty(){
		return "CORRELATIVE_SENTENCE";
	}
}
