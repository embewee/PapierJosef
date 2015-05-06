package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.model.document.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class NGrams extends Statistik<Document> {

	@Override
	public void fuehreAus(Document d) {
		List<String> result = new ArrayList<>();
		result = (List<String>) this.getReport().values.values().stream()
				.map(Object::toString)
				.collect(Collectors.toCollection(ArrayList<String>::new));

		List<List<String>> bigrams = getNGrams(result, 2);
		Map<List<String>, Integer> freqs2 = countFreqs(bigrams);

		freqs2 = freqs2.entrySet().stream().filter(e -> e.getValue() > 1)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

		for (Entry<List<String>, Integer> e : freqs2.entrySet())
			report.put(e.getKey().toString(), e.getValue());

	}

	private <T> Map<T, Integer> countFreqs(List<T> unigrams) {
		Map<T, Integer> freqs = new HashMap<T, Integer>();
		for (T s : unigrams) {
			if (freqs.containsKey(s))
				freqs.put(s, freqs.get(s) + 1);
			else
				freqs.put(s, 1);
		}
		return freqs;
	}

	private List<List<String>> getNGrams(List<String> unigrams, int n) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < unigrams.size() - (n - 1); i++) {
			List<String> temp = new ArrayList<String>();
			for (int j = 0; j < n; j++)
				temp.add(unigrams.get(i + j));
			result.add(temp);
		}
		return result;
	}
}
