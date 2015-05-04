package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.model.document.TextElement;

import java.util.Map;

/**
 * Statistiken zeichnen sich dadurch aus, dass sie über etwas berichten. Sie
 * geben folglich also einen Report (auch: Bericht) zurück.
 * 
 * @author dominik
 *
 * @param <T>
 */
public abstract class Statistik<T extends TextElement<?>> extends Operation<T> {

	/**
	 * Das bezeichnende Element
	 * 
	 * @return Ein Report (oder Bericht) ist als Schlüssel-Wert-Sammlung
	 *         definiert
	 */
	public abstract Report getReport();

	class Report {
		Map<String, Object> values;

		public void put(String key, Object value) {
			values.put(key, value);
		}
		
		public Object get(String key){
			return values.get(key);
		}
	}

}
