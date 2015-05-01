package hardcode.papierjosef.bibliothek.operation.rules.nlp;

import hardcode.papierjosef.bibliothek.operation.TextHaecksler;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Sentence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

public class SentenceDetector extends TextHaecksler<Paragraph> {

	@Override
	public void fuehreAus(Paragraph t) {
		String pfad = System.getProperty("user.dir") + File.separator
				+ "sprachen" + File.separator + getSprache().getLanguage()
				+ File.separator + "nlpmodels/" + getSprache().getLanguage();
		try {
			FileInputStream sentModelIn = new FileInputStream(pfad
					+ "-sent.bin");
			SentenceModel sentenceModel = new SentenceModel(sentModelIn);
			if (sentModelIn != null)
				sentModelIn.close();
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(
					sentenceModel);

			String s[] = sentenceDetector.sentDetect(getRawText());
			try {
				for (String sentence : s)
					t.addElement(new Sentence(null, 0, 0), 0);
				setOutput(Arrays.asList(s));
			} catch (HumbugException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
