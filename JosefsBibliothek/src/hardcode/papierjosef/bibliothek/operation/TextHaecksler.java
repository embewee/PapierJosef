package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.sprachen.Language;
import hardcode.papierjosef.model.document.TextElement;

import java.util.List;

public abstract class TextHaecksler<T extends TextElement<?>> extends
		Operation<T> {

	private String rawText;
	private List<String> output;
	private Language sprache;

	public Language getSprache() {
		return sprache;
	}

	public void setSprache(Language sprache) {
		this.sprache = sprache;
	}

	public void setOutput(List<String> outs) {
		this.output = outs;
	}

	public List<String> getOutput() {
		return output;
	}

	public void setRawText(String text) {
		this.rawText = text;
	}

	public String getRawText() {
		return this.rawText;
	}
}
