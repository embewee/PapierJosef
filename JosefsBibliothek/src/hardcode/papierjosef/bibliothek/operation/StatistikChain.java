package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.statistik.Report;
import hardcode.papierjosef.bibliothek.statistik.Statistik;

import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class StatistikChain extends OperationChain<Statistik> {

	@Override
	public Statistik next() {
		if (count == operations.size())
			throw new NoSuchElementException();
		count++;

		if (count > 1) {
			operations.get(count - 1).setReport(
					operations.get(count - 2).getReport());
		}
		return operations.get(count - 1);
	}

	public Report getReport() {
		Operation<?> last = getOperationList().get(getOperationList().size()-1);
		Statistik<?> stat = (Statistik<?>) last;
		return stat.getReport();
	}
}
