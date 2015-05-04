package hardcode.papierjosef.bibliothek;

import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.filtry.Filtry;
import hardcode.papierjosef.bibliothek.filtry.PlainTextFiltry;
import hardcode.papierjosef.bibliothek.loader.documentloader.DocumentLoader;
import hardcode.papierjosef.bibliothek.loader.documentloader.LoadedDocument;
import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.OperationProcessor;
import hardcode.papierjosef.bibliothek.operation.TextHaeckslerKette;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.naming.OperationNotSupportedException;

//TODO: make singleton

public class PapierJosefFacade {

	private LoadedDocument loadedDocument;
	private Document document;

	public void readDocument(File file) throws IOException, LibraryException,
			OperationNotSupportedException {
		loadedDocument = DocumentLoader.loadFile(file);
		switch (loadedDocument.getFileType()) {
		case TXT:
			setFilter(new PlainTextFiltry());
			break;
		default:
			// #FIXME: Sollte lokalisiert werden - also Deutsch, Englisch usw.
			throw new OperationNotSupportedException("Unbekannter Dateityp");
		}
	}

	public Document getDocument() {
		return document;
	}

	public boolean isDocument() {
		return document != null;
	}

	// #FIXME
	// Ein Setter sollte nicht so viel Funktionalität enthalten
	public void setFilter(Filtry filter) {
		filter.setInputRawText(loadedDocument.getText());
		filter.execute();
		TextHaeckslerKette k = new TextHaeckslerKette();
		document = k.execute(filter.getFilteredOutputText(),
				filter.getResiduals());
	}

	public void executeOperation(Operation<?> op) throws HumbugException {
		OperationProcessor.execute(op, document);
	}

	public void executeOperationChain(OperationChain chain)
			throws HumbugException {
		OperationProcessor.execute(chain, document);
	}

	public void executeOperationList(List<Operation<?>> operationList)
			throws HumbugException {
		OperationProcessor.execute(operationList, document);
	}

	private File appDir; // user application execution directory

	public PapierJosefFacade(File appDir) {
		this.appDir = appDir;
	}

}
