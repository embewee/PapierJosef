package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.model.document.Document;

import java.util.List;
import java.util.stream.Collectors;

public class Unigrams extends Statistik<Document> {


	public Unigrams() {
		super();
	}

	@Override
	public void execute(Document d) {
		List<String> unigrams = getUnigrams(d);
		for (String e : unigrams)
			report.put(e, 1);
	}
	


	private List<String> getUnigrams(Document d) {
		return d.getChildElements().stream().map(p -> p.getChildElements())
				.flatMap(List::stream).map(s -> s.getWordsOnly())
				.flatMap(List::stream).map(t -> t.getChildElements())
				.flatMap(List::stream).collect(Collectors.toList()).stream()
				.map(str -> str.toLowerCase()).collect(Collectors.toList());
	}
}
