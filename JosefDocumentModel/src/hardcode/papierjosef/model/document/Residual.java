package hardcode.papierjosef.model.document;

import java.util.List;

public class Residual extends Token {

	public Residual(List<String> list, long start, long end)
			throws HumbugException {
		super(list, start, end);
	}

	@Override
	public String getText() {
		return getChildElements().get(0);
	}

}
