package harcode.papierjosef.bibliothek.test;

import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.filtry.Filtry;
import hardcode.papierjosef.bibliothek.filtry.PlainTextFiltry;
import hardcode.papierjosef.bibliothek.loader.documentloader.DocumentLoader;
import hardcode.papierjosef.bibliothek.loader.documentloader.LoadedDocument;
import hardcode.papierjosef.bibliothek.operation.TextHaeckslerKette;
import hardcode.papierjosef.model.document.Document;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class IntegrationsTest {

	@Test
	public void pruefeFunktionalitaet() throws IOException, LibraryException {
		// #FIXME
		LoadedDocument d = DocumentLoader.loadFile(new File(
				"/media/dominik/Data/WorkspaceNew/PapierJosef/"
						+ "JosefsBibliothek/test-document-plain.txt"));
		Filtry f = new PlainTextFiltry();
		f.setInputRawText(d.getText());
		f.execute();

		TextHaeckslerKette k = new TextHaeckslerKette();
		Document doc = k.execute(f.getFilteredOutputText(), f.getResiduals());
		System.out.println(doc.getText());
	}
}
