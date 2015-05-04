package harcode.papierjosef.bibliothek.test;

import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.filtry.Filtry;
import hardcode.papierjosef.bibliothek.filtry.PlainTextFiltry;
import hardcode.papierjosef.bibliothek.loader.documentloader.DocumentLoader;
import hardcode.papierjosef.bibliothek.loader.documentloader.LoadedDocument;
import hardcode.papierjosef.bibliothek.operation.OperationProcessor;
import hardcode.papierjosef.bibliothek.operation.TextHaeckslerKette;
import hardcode.papierjosef.bibliothek.operation.rules.quality.LangeSaetzeRegel;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Punctuation;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Token;

import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class IntegrationsTest {

	private Document document;
	
	@BeforeClass
	public void pruefeFunktionalitaet() throws IOException, LibraryException {
		// #FIXME
		LoadedDocument d = DocumentLoader.loadFile(new File(
				"/media/dominik/Data/WorkspaceNew/PapierJosef/"
						+ "JosefsBibliothek/test-document-plain.txt"));
		Filtry f = new PlainTextFiltry();
		f.setInputRawText(d.getText());
		f.execute();

		TextHaeckslerKette k = new TextHaeckslerKette();
		document = k.execute(f.getFilteredOutputText(), f.getResiduals());
	}
	
	@Test
	public void executeSomeOperation() throws HumbugException {
		OperationProcessor.execute(new LangeSaetzeRegel(), document);
		
		
	}
	
	public void printDocument() {
		System.out.println("DOCUMENT");
		printProperties(document);
		
		for (Paragraph p : document.getChildElements()) {
			for (Sentence s : p.getChildElements()) {
				for (Token t : s.getChildElements()) {
					printProperties(t);
				}
			}
		}
	}
	
	public void printProperties(TextElement te) {
		Set<String> keys = te.getPropertyKeys();
		for(String key : keys) {
			System.out.print("[" + key + ":" + te.getProperty(key) + "] ");
		}
		System.out.println();
	}
}