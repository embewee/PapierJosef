package hardcode.papierjosef.bibliothek.operation.rules.nlp;

import hardcode.papierjosef.bibliothek.operation.TextHaecksler;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.PartOfSpeech;
import hardcode.papierjosef.model.document.Punctuation;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class TokenDetector extends TextHaecksler<Sentence> {

	/**
	 * Residual und Punctuation werden in NlpAbschluss eingefügt
	 */
	@Override
	public void fuehreAus(Sentence s) {
		String pfad = System.getProperty("user.dir") + File.separator
				+ "sprachen" + File.separator + getSprache().getLanguage()
				+ File.separator + "nlpmodels/" + getSprache().getLanguage();
		try {
			InputStream tokenModelIn = new FileInputStream(pfad + "-token.bin");
			InputStream posModelIn = new FileInputStream(pfad
					+ "-pos-maxent.bin");

			TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
			if (tokenModelIn != null)
				tokenModelIn.close();
			POSModel posModel = new POSModel(posModelIn);
			if (posModelIn != null)
				posModelIn.close();
			TokenizerME tokenizer = new TokenizerME(tokenModel);
			POSTaggerME tagger = new POSTaggerME(posModel);

			String t[] = tokenizer.tokenize(getRawText());
			String p[] = tagger.tag(t);

			// Residual wird in NlpAbschluss eingefügt
			for (int i = 0; i < t.length; i++) {
				if (p[i].equals("XY") || p[i].equals("UNDEFINED")
						|| p[i].startsWith("$"))
					s.addElement(new Punctuation(Arrays.asList(t[i]), 0, 0), 0);
				else
					s.addElement(new Word(Arrays.asList(t[i]), 0, 0,
							new PartOfSpeech(p[i], "")), 0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (HumbugException e) {
			e.printStackTrace();
		}
		// TODO Tokens nach oben propagieren
	}
}
