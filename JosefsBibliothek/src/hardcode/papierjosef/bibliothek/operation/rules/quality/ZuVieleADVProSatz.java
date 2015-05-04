package hardcode.papierjosef.bibliothek.operation.rules.quality;

import java.util.Arrays;
import java.util.List;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

/**
 * DIES IST NUR EINE BEISPIEL-REGEL, DIE EIGTL NICHT ALS SOLCHE TAUGT
 * 
 * @author dominik
 *
 */
public class ZuVieleADVProSatz extends Regel<Sentence> {

	@Override
	public void fuehreAus(Sentence t) {
		String ADV = "ADV";
		int nrOfAdv = 0;
		for (Word w : t.getWordsOnly()) {
			if (w.getPartOfSpeech().getPartOfSpeechName().equals(ADV))
				nrOfAdv++;
		}
		if (nrOfAdv >= 5) {
			TextElementProperty property = new TextElementProperty(
					"ZU_VIELE_ADV_PRO_SATZ",
					"Ein guter Satz enthält weniger als 5 ADV.");
			t.addProperty(property);
		}

	}

	@Override
	public List<String> getProperties() {
		return Arrays.asList("ZU_VIELE_ADV_PRO_SATZ");
	}

	@Override
	public Class<? extends TextElement> getLevel() {
		return Sentence.class;
	}
}
