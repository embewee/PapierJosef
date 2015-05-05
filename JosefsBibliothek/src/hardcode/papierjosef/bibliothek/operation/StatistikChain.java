package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.statistik.Statistik;

import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class StatistikChain extends OperationChain<Statistik> {

	public Statistik next() {
		if (count == operations.size())
			throw new NoSuchElementException();
		count++;
		// TODO bei Statistik hier sachen übergeben
		operations.get(count - 1).setReport(
				operations.get(count - 2).getReport());

		return operations.get(count - 1);
	}
}
