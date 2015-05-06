package hardcode.papierjosef.bibliothek;

import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;
import hardcode.papierjosef.bibliothek.filtry.Filtry;
import hardcode.papierjosef.bibliothek.filtry.PlainTextFiltry;
import hardcode.papierjosef.bibliothek.loader.LadeKlasse;
import hardcode.papierjosef.bibliothek.loader.documentloader.DocumentLoader;
import hardcode.papierjosef.bibliothek.loader.documentloader.LoadedDocument;
import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.OperationProcessor;
import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.bibliothek.operation.RuleChain;
import hardcode.papierjosef.bibliothek.operation.TextHaeckslerKette;
import hardcode.papierjosef.bibliothek.operation.rules.quality.KorrelatSatz;
import hardcode.papierjosef.bibliothek.operation.rules.quality.LangeSaetzeRegel;
import hardcode.papierjosef.bibliothek.operation.rules.quality.PassivSatz;
import hardcode.papierjosef.bibliothek.operation.rules.quality.UnpersoenlichesPronomen;
import hardcode.papierjosef.bibliothek.operation.rules.quality.ZuVieleADVProSatz;
import hardcode.papierjosef.bibliothek.statistik.GrundlegendeStatistik;
import hardcode.papierjosef.bibliothek.statistik.Unigrams;
import hardcode.papierjosef.bibliothek.statistik.Statistik;
import hardcode.papierjosef.bibliothek.util.DocumentPrinter;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.TextElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.naming.OperationNotSupportedException;

//TODO: make singleton
/*TODO:	Fassade soll sich merken, welche Operationen, ... schon ausgefuehrt wurden,
 * so dass dieselbe ~ nicht mehrfach ausgefuehrt werden kann - erst, nachdem sie wieder
 * entfernt wurde.
 * 
 * TODO rawtypes bei OperationChain müssen mal angegangen werden!
 */

@SuppressWarnings("rawtypes")
public class PapierJosefFacade {

	private LoadedDocument loadedDocument;
	private Document document;

	public Vector<Regel<? extends TextElement<?>>> getInternalRules() {
		Vector<Regel<? extends TextElement<?>>> list = new Vector<>();
		list.add(new KorrelatSatz());
		list.add(new LangeSaetzeRegel());
		list.add(new PassivSatz());
		list.add(new UnpersoenlichesPronomen());
		list.add(new ZuVieleADVProSatz());
		return list;
	}

	public Vector<OperationChain<?>> getInternalRuleChains() {
		Vector<OperationChain<?>> chains = new Vector<>();
		
		//TODO: TEST-Chain
		RuleChain testRuleChain = new RuleChain();
		testRuleChain.setName("TestRuleChain");
		testRuleChain.addOperation(new LangeSaetzeRegel());
		testRuleChain.addOperation(new UnpersoenlichesPronomen());
		testRuleChain.inferAllProperties();
		
		
		// TODO: add chains
		chains.add(testRuleChain);
		return chains;
	}

	public Vector<Statistik<? extends TextElement<?>>> getInternalStatistics() {
		Vector<Statistik<? extends TextElement<?>>> stats = new Vector<>();
		// TODO: add stats
		stats.add(new GrundlegendeStatistik());
		stats.add(new Unigrams());
		return stats;
	}

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

	public void executeOperation(Operation<?> op) throws HumbugException, ParameterNotSetException {
		OperationProcessor.execute(op, document);
	}

	public void executeOperationChain(OperationChain chain) throws HumbugException, ParameterNotSetException {
		OperationProcessor.execute(chain, document);
	}

	public void executeOperationList(List<Operation<?>> operationList) throws HumbugException, ParameterNotSetException{
		OperationProcessor.execute(operationList, document);
	}

	@SuppressWarnings("unused")
	// TODO Wozu wird das gebraucht?
	private File appDir; // user application execution directory

	public PapierJosefFacade(File appDir) {
		this.appDir = appDir;
	}

	public Operation<?> loadOperationFromFile(File file)
			throws LibraryException {
		LadeKlasse<Operation<?>> loader = new LadeKlasse<Operation<?>>();
		String classname = file.getName().substring(0,
				file.getName().lastIndexOf("."));
		String path = file.getPath().substring(0,
				file.getPath().lastIndexOf(File.separator));
//		System.out.println(classname); // TODO: weg
//		System.out.println(path); // TODO: weg

		Operation<?> op = loader.ladeKlasse(new File(path), classname);
		return op;
	}

	public void printDocument() {
		DocumentPrinter.printDocument(document);
	}

}
