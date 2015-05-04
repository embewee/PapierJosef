package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.model.document.Document;

public class GrundlegendeStatistik extends Statistik<Document> {

	private Report report;

	@Override
	public Report getReport() {
		return report;
	}

	@Override
	public void fuehreAus(Document t) {
		report.put("zahlDerAbsaetze", t.getChildElements().size());
		report.put(
				"zahlDerSaetze",
				t.getChildElements().stream()
						.mapToInt(p -> p.getChildElements().size())
						.reduce(Integer::sum));
		report.put(
				"zahlDerWoerter",
				t.getChildElements().stream().map(p -> p.getChildElements())
						.flatMap((l) -> l.stream())
						.map(s -> s.getWordsOnly().size()).reduce(Integer::sum));

	}
}
