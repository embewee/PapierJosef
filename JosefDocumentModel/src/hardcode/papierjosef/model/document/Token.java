package hardcode.papierjosef.model.document;

import java.util.List;

public abstract class Token extends TextElement<String> {

	public Token(List<String> list, long start, long end)
			throws HumbugException {
		super(list, start, end);
	}

}
