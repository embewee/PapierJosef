package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.model.document.TextElement;

/**
 * Statistiken zeichnen sich dadurch aus, dass sie über etwas berichten. Sie
 * geben folglich also einen Report (auch: Bericht) zurück.
 * 
 * @author dominik
 *
 * @param <T>
 */
public abstract class Statistik<T extends TextElement<?>> extends Operation<T> {

	private Report report;
	/**
	 * Das bezeichnende Element
	 * 
	 * @return Ein Report (oder Bericht) ist als Schlüssel-Wert-Sammlung
	 *         definiert
	 */
	public Report getReport(){
		return this.report;
	}
	
	public void setReport(Report report){
		this.report=report;
	}
}
