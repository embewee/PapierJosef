package hardcode.papierjosef.rajbo;

import java.util.Vector;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;

public interface Environment {

	public PapierJosefFacade getLibrary();

	public Vector<String> getLanguages();

	public String getProperty(String key);

	public String getPropertiesDirectory();

	public String getLocaleString(String key);

}
