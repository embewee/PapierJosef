package hardcode.papierjosef.model.document;

import java.util.List;

public class Punctuation extends Token {

	public Punctuation(List<String> list, long start, long end)
			throws HumbugException {
		super(list, start, end);
	}

	@Override
	public String getText() {
		return getChildElements().get(0);
	}

}
