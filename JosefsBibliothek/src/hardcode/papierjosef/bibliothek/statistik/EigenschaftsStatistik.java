package hardcode.papierjosef.bibliothek.statistik;

import hardcode.papierjosef.model.document.Document;

public class EigenschaftsStatistik extends Statistik<Document> {

	Report report;

	public EigenschaftsStatistik() {
		super();
		report = new Report();
		arg("TestKey", "TestValue").arg("Argument2", "mitWert");
	}

	@Override
	public void execute(Document t) {

	}

	@Override
	public Report getReport() {
		return report;
	}

	// private String stat;
	//
	//
	// @Override
	// public void errechne(Dokument dokument) {
	// StringBuffer sb = new StringBuffer();
	//
	// sb.append("DOKUMENT\n");
	// for(String eigenschaft : dokument.getEigenschaftenSchluessel()) {
	// sb.append(eigenschaft).append(": ").append(dokument.get(eigenschaft)).append("\n");
	// }
	//
	// for (Absatz absatz : dokument.getAbsaetze()) {
	// int endIndex = Math.min(25, absatz.getText().length()-1); //zeige max.
	// die 25 ersten Zeichen eines Absatzes
	// sb.append("--").append(absatz.getText().substring(0,
	// endIndex)).append("\n");
	// for(String eigenschaft : absatz.getEigenschaftenSchluessel()) {
	// sb.append("--").append(eigenschaft).append(": ").append(absatz.get(eigenschaft)).append("\n");
	// }
	//
	// for(Satz satz : absatz.getSaetze()) {
	// endIndex = Math.min(15, satz.getText().length()-1); //zeige max. die 15
	// ersten Zeichen eines Satzes
	// sb.append("----").append(satz.getText().substring(0,
	// endIndex)).append("\n");
	// for(String eigenschaft : satz.getEigenschaftenSchluessel()) {
	// sb.append("----").append(eigenschaft).append(": ").append(satz.get(eigenschaft)).append("\n");
	//

}
