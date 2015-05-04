package hardcode.papierjosef.rajbo;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.rajbo.view.Window;

import java.util.Vector;

public interface Environment {

	public PapierJosefFacade getLibrary();

	public Vector<String> getLanguages();

	public String getProperty(String key);

	public String getPropertiesDirectory();

	public String getLocaleString(String key);

	public Window getWindow();

}
