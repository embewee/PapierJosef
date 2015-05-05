package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.model.document.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HaeufigsteWortgruppen extends Statistik<Document> {

	private Report report;

	public HaeufigsteWortgruppen() {
		super();
		report = new Report();
	}

	@Override
	public Report getReport() {
		return report;
	}

	@Override
	public void fuehreAus(Document d) {
		List<String> unigrams = getUnigrams(d);

		List<List<String>> bigrams = getNGrams(unigrams, 2);
		// Map<String, Integer> freqs = countFreqs(unigrams);
		Map<List<String>, Integer> freqs2 = countFreqs(bigrams);

		freqs2 = freqs2.entrySet().stream().filter(e -> e.getValue() > 1)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

		for (Entry<List<String>, Integer> e : freqs2.entrySet())
			report.put(e.getKey().toString(), e.getValue());

		// for (Entry<String, Integer> e : sortByValues(freqs).entrySet())
		// report.put(e.getKey(), e.getValue());
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

	private List<String> getUnigrams(Document d) {
		return d.getChildElements().stream().map(p -> p.getChildElements())
				.flatMap(List::stream).map(s -> s.getWordsOnly())
				.flatMap(List::stream).map(t -> t.getChildElements())
				.flatMap(List::stream).collect(Collectors.toList()).stream()
				.map(str -> str.toLowerCase()).collect(Collectors.toList());
	}

	public static Map<String, Integer> sortByValues(HashMap<String, Integer> map) {
		Map<String, Integer> sortedHashMap = new HashMap<>();
		map.entrySet().stream()
				.sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
				.forEach(e -> sortedHashMap.put(e.getKey(), e.getValue()));
		return sortedHashMap;
	}
}
