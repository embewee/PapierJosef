package hardcode.papierjosef.rajbo.view;

import java.util.Vector;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.rajbo.PreferencesProvider;

public interface Environment {

	PreferencesProvider getProvider();

	PapierJosefFacade getLibrary();
	
	Vector<String> getLanguages();

}
