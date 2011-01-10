package de.osmui.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class I18N {
	
	/**
	 * Singleton attribute to access an instance of this.
	 */
	public static final I18N instance = new I18N();
	
	/**
	 * The base name of the file containing the translations.
	 */
	private static final String MESSAGES_BASE_NAME = "de.osmui.i18n.osmui";
	
	/**
	 * The resource bundle that holds the translations for the UI.
	 */
	private static ResourceBundle bundle;

	I18N() {
		// Load the resource bundle
		bundle = ResourceBundle.getBundle(MESSAGES_BASE_NAME);
	}

	/**
	 * Returns the message that is associated with the given key. Place holders
	 * in the message are replace with the given objects.
	 * 
	 * @param key
	 *            to get the message for
	 * @param values
	 *            to set for place holders in the message.
	 * @return Message with place holders replaced with values
	 */
	public static String getString(String key, Object... values) {
		String msg = bundle.getString(key);
		if (values.length > 0) {
			return MessageFormat.format(msg, values);
		}
		return msg;
	}
	
}
