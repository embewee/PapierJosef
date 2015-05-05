package hardcode.papierjosef.bibliothek.operation;

import java.util.LinkedList;
import java.util.List;

/**
 * Eine Operationenkette enthaelt die Namen mehrer Operationen, die aus aus
 * Klassendateien geladen und in der angegebenen Reihenfolge nacheinander
 * ausgefuehrt werden.
 *
 * TODO: Rawtypes weg!
 *
 */
@SuppressWarnings("rawtypes")
public abstract class OperationChain<T extends Operation> {

	private String chainName;
	// private String chainDescription;

	private List<T> operations;

	public OperationChain() {
		operations = new LinkedList<T>();
		chainName = null;
		// chainDescription = null;
	}

	public void addOperation(T name) {
		operations.add(name);
	}

	public void setName(String name) {
		this.chainName = name;
	}

	// public void setOperationenKetteBeschreibung(String beschreibung) {
	// this.operationenKetteBeschreibung = beschreibung;
	// }

	public List<Operation<?>> getChain() {
		return java.util.Collections.unmodifiableList(operations);
	}

	public String getName() {
		return chainName;
	}

	//
	// public String getBeschreibung() {
	// return operationenKetteBeschreibung;
	// }
}
