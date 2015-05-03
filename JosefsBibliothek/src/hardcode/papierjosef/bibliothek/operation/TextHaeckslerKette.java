package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.operation.rules.nlp.NlpAbschluss;
import hardcode.papierjosef.bibliothek.operation.rules.nlp.Paragraphizer;
import hardcode.papierjosef.bibliothek.operation.rules.nlp.SentenceDetector;
import hardcode.papierjosef.bibliothek.operation.rules.nlp.TokenDetector;
import hardcode.papierjosef.bibliothek.sprachen.Deutsch;
import hardcode.papierjosef.bibliothek.sprachen.Language;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Residual;
import hardcode.papierjosef.model.document.Sentence;

import java.util.List;

public class TextHaeckslerKette {

	public Document execute(String plainText, List<Residual> residuals) {

		try {
			Document d = new Document(null, 0, 0);

			Paragraphizer p = new Paragraphizer();
			SentenceDetector s = new SentenceDetector();
			TokenDetector t = new TokenDetector();
			NlpAbschluss n = new NlpAbschluss();

			Language deutsch = new Deutsch();
			p.setSprache(deutsch);
			s.setSprache(deutsch);
			t.setSprache(deutsch);
			n.setSprache(deutsch);

			p.setRawText(plainText);
			p.fuehreAus(d);

			List<String> stringParagraphs = p.getOutput();
			List<Paragraph> paragraphs = d.getChildElements();

			for (int i = 0; i < stringParagraphs.size(); i++) {
				s.setRawText(stringParagraphs.get(i));
				s.fuehreAus(paragraphs.get(i));
				List<String> stringSentences = s.getOutput();
				List<Sentence> sentences = paragraphs.get(i).getChildElements();
				for (int j = 0; j < stringSentences.size(); j++) {
					t.setRawText(stringSentences.get(j));
					t.fuehreAus(sentences.get(j));
				}
			}

			n.fuehreAus(d);

			return d;
		} catch (HumbugException e) {
			e.printStackTrace();
		}
		return null;
	}
}
