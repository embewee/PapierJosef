package hardcode.papierjosef.bibliothek.operation.rules.quality;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.annotation.TextElementProperty;

import javax.swing.JOptionPane;

public class TestUserInputRule extends Regel<Sentence>{

	@Override
	public String getProperty() {
		return "LONG_SENTENCE";
	}

	@Override
	public void fuehreAus(Sentence t) {
		String maxString = JOptionPane.showInputDialog(null, "Maximale Anzahl Wörter in einem guten Satz:");
		int maxLength = Integer.parseInt(maxString);
		if (t.size() > maxLength) {
			TextElementProperty tep = new TextElementProperty("LONG_SENTENCE", "Langer Satz");
			t.addProperty(tep);
		}
	}

}
