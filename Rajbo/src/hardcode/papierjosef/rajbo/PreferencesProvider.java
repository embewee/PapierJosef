package hardcode.papierjosef.rajbo;

public interface PreferencesProvider {
	public String getProperty(String key);
	public String getPropertiesDirectory();
	public String getLocaleString(String key);
}
