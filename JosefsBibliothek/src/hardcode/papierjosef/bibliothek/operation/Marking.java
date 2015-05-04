package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.model.document.TextElement;

import java.util.Collection;
import java.util.List;

public interface Marking {
	List<String> getProperties();
	Class<? extends TextElement> getLevel();
}
