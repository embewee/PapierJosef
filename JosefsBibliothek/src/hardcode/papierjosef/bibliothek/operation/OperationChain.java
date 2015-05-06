package hardcode.papierjosef.bibliothek.operation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Eine Operationenkette enthaelt die Namen mehrer Operationen, die aus aus
 * Klassendateien geladen und in der angegebenen Reihenfolge nacheinander
 * ausgefuehrt werden.
 *
 * TODO: Rawtypes weg!
 * 
 * TODO: Argumente unterstuetzen! Momentan unterstuetzen nur einzelne Operationen
 * Argumente, sobald sie in einer Kette sind, gt das nicht mehr
 * 
 *
 */
@SuppressWarnings("rawtypes")
public abstract class OperationChain<T extends Operation> implements
		Iterable<T>, Iterator<T> {

	private String chainName;
	// private String chainDescription;

	protected int count = 0;

	protected List<T> operations;

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

	public List<Operation<?>> getOperationList() {
		return java.util.Collections.unmodifiableList(operations);
	}

	public String getName() {
		return chainName;
	}

	public Iterator<T> iterator() {
		return this;
	}

	public boolean hasNext() {
		return count < operations.size();
	}

	public T next() {
		if(count==operations.size())
			throw new NoSuchElementException();	
		count++;
		// TODO bei Statistik hier sachen übergeben
		return operations.get(count-1);
	}

	//
	// public String getBeschreibung() {
	// return operationenKetteBeschreibung;
	// }
	
	@Override
	public String toString() {
		return getName();
	}
}
