package harcode.papierjosef.bibliothek.test;

import static org.junit.Assert.assertTrue;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.filtry.Filtry;
import hardcode.papierjosef.bibliothek.filtry.PlainTextFiltry;
import hardcode.papierjosef.bibliothek.loader.documentloader.DocumentLoader;
import hardcode.papierjosef.bibliothek.loader.documentloader.LoadedDocument;
import hardcode.papierjosef.bibliothek.operation.TextHaeckslerKette;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.Token;
import hardcode.papierjosef.model.document.Word;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {

	Document document;

	@Before
	public void init() throws IOException, LibraryException {
		LoadedDocument d = DocumentLoader.loadFile(new File(
				"test-document-plain.txt"));
		Filtry f = new PlainTextFiltry();
		f.setInputRawText(d.getText());
		f.execute();

		TextHaeckslerKette k = new TextHaeckslerKette();
		document = k.execute(f.getFilteredOutputText(), f.getResiduals());
	}

	@Test
	public void filter() {
		List<Token> toks = document.getChildElements().get(0)
				.getChildElements().get(0).getChildElements();
		List<Word> words = document.getChildElements().get(0)
				.getChildElements().get(0).getWordsOnly();
		// Weil im ersten Satz ein Satzzeichen (nämlich ein Punkt) vorkommt
		assertTrue("Tokens: " + toks.size() + ", Words: " + words.size(),
				toks.size() > words.size());
	}

}
