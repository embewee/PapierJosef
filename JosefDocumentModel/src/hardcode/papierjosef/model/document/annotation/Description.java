package hardcode.papierjosef.model.document.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic, internationalizable description.
 */
public class Description {
	
	public final static String DEFAULT_DESCRIPTION_KEY = "#DEFAULT_DESCRIPTION_KEY__";
	
	private Map<String, String> descriptions;
	
	/**
	 * Creates a new Description. A default description must be given.
	 * @param defaultDescription String: The default description.
	 */
	public Description(String defaultDescription) {
		descriptions = new HashMap<String, String>();
		descriptions.put(DEFAULT_DESCRIPTION_KEY, defaultDescription);
	}
	
	/**
	 * Adds an description in a specific language. A language is represented as a short String according to ISO 639-1.
	 * E.g. the locale for German is "de", for English it is "en".
	 * @param locale String: language code according to ISO 639-1.
	 * @param text String: description in the specific language.
	 * @return
	 */
	public boolean addDescription(String locale, String text) {
		if(locale.equals(DEFAULT_DESCRIPTION_KEY)) {
			return false;
		}
		descriptions.put(locale, text);
		return true;
	}
	
	/**
	 * Returns a description for the given locale or the default description, if no description for the given
	 * locale is present. A language is represented as a short String according to ISO 639-1.
	 * E.g. the locale for German is "de", for English it is "en".
	 * @param locale String: language code according to ISO 639-1.
	 * @return String: The description
	 */
	public String getDescription(String locale) {
		String description = descriptions.get(locale);
		if(description == null) {
			return getDefaultDescription();
		} else {
			return description;
		}
	}
	
	/**
	 * Returns the default description.
	 * @return String: the default description.
	 */
	public String getDefaultDescription() {
		return descriptions.get(DEFAULT_DESCRIPTION_KEY);
	}	
}
