package hardcode.papierjosef.bibliothek.filtry;

import hardcode.papierjosef.model.document.Residual;

import java.util.List;

/**
 * Vorverarbeitungsschritt, der aus einem Rohtext einen sauberen (plain-) text
 * macht und daneben eine Liste von Residuals ausspuckt. Diese Residuals haben
 * als start den Platz im gefilterten Text.
 * 
 * @author dominik
 *
 */
public interface Filtry {

	public void setInputRawText(String rawText);
	
	public String getFilteredOutputText();
	
	public List<Residual> getResiduals();
	
	public void execute();
		
}
