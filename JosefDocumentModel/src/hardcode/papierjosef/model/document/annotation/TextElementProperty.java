package hardcode.papierjosef.model.document.annotation;

/**
 * Represents a TextElementProperty which can be added to all elements of the Document hierarchy. We say an element
 * is enriched with TextElementProperties. <p>
 * TextElementProperties need to have an <b>unique key</b>. <p>
 * A internationalizable description may be provided as well.
 */
public class TextElementProperty {

	private String key;
	private Description description;
	
	/**
	 * Creates a new TextElementProperty with the given key and given description object.
	 * @param key String: This TextElementProperty's <b>unique key</b>.
	 * @param description String: The description object.
	 */
	public TextElementProperty(String key, Description description) {
		this.key = key;
		this.description = description;
	}
	
	/**
	 * Creates a new TextElementProperty with the given key. The given description is used as default description
	 * for this TextElementProperty.
	 * @param key String: This TextElementProperty's <b>unique key</b>.
	 * @param description String: The default description.
	 */
	public TextElementProperty(String key, String description) {
		this.key = key;
		this.description = new Description(description);
	}
	
	/**
	 * Returns the key of this TextElementProperty.
	 * @return String: The key of this TextElementProperty.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the description object for this TextElementProperty.
	 * @return Description: The description object.
	 */
	public Description getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "[" + key + ": " + description.getDefaultDescription() + "]";
	}
}
