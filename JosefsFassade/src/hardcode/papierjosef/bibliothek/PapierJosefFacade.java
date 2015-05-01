package hardcode.papierjosef.bibliothek;


import java.io.File;
import java.io.IOException;

//TODO: make singleton

public class PapierJosefFacade {

	public void readDocument(File file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public String evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void analyze() {
		// TODO Auto-generated method stub
		
	}
	private File appDir; //user application execution directory
	public PapierJosefFacade(File appDir) {
		this.appDir = appDir;
	}
	
	
//	private TextDokumentLader textDokumentLader;
//	private Dokument dokument;
//	private JosefsSekretaerin assistenz;
//		
//
//	
//	@Override
//	public void readDocument(File file) throws IOException {
//		textDokumentLader = new TextDokumentLader();
//		textDokumentLader.ladeDokument(file);
//	}
//
//	public String evaluate() {
//			
//			StringBuffer sb = new StringBuffer();
//			
//			for(Wortart w : dokument.getAnzahlWortarten().keySet()) {
//				int anzahl = dokument.getAnzahlWortarten().get(w);
//				sb.append(w.toString() + ": " + anzahl + "; " + "\n");
//			}
//
//			sb.append("##################");
//			
//			GrundlegendeStatistik grundStat = assistenz.errechneGrundlegendeStatistik();
//			sb.append(grundStat);
//			
//			return sb.toString();
//			
////			d.put("Tolle Eigenschaft", "true");
////			d.getAbsaetze().get(0).put("Toller Satz", "true");
////			d.getAbsaetze().get(0).getSaetze().get(0).put("Schöner Satz", "true");
////			//TODO: fuer woerter!
////			
////			EigenschaftsStatistik eigenStat = assistenz.errechneEigenschaftsStatistik();
////			System.out.println(eigenStat);
////			
////			System.out.println("##################");
////			
////			for(Absatz a : d.getAbsaetze()) {
////				System.out.println("> ABSATZ");
////				for(Satz s : a.getSaetze()) {
////					System.out.println("---> SATZ");
////					for(Wort w : s.getWoerter()) {
////						System.out.println("------> " + w.getWort() + ": " + w.getWortart() + "(" + w.getStart() + ", " + w.getEnde() + ")");
////					}
////				}
////			}
//		}
//
//	@Override
//	public void analyze() {
//		try {
//			assistenz = new JosefsSekretaerin(appDir, textDokumentLader.getDokumentString(), new DeutscheSprache());
//		} catch (IOException e) {
//			e.printStackTrace(); //TODO
//		} catch (Exception e) {
//			e.printStackTrace(); //TODO
//		}
//		
//		dokument = assistenz.getDokument();
//	}
//	
//	
}
