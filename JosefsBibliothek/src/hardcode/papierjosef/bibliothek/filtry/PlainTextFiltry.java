package hardcode.papierjosef.bibliothek.filtry;

import hardcode.papierjosef.model.document.Residual;

import java.util.ArrayList;
import java.util.List;

public class PlainTextFiltry implements Filtry {

	String text;

	@Override
	public void setInputRawText(String rawText) {
		this.text = rawText;
	}

	@Override
	public String getFilteredOutputText() {
		return text;
	}

	@Override
	public List<Residual> getResiduals() {
		return new ArrayList<Residual>();
	}

	@Override
	public void execute() {
	}

}
