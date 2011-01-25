/*OsmUi is a user interface for Osmosis
    Copyright (C) 2011  Verena Käfer, Peter Vollmer, Niklas Schnelle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or 
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package de.osmui.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see I18NTest
*/
public class I18N {
	
	
	/**
	 * The base name of the file containing the translations.
	 */
	private static final String MESSAGES_BASE_NAME = "de.osmui.i18n.osmui";
	
	/**
	 * The resource bundle that holds the translations for the UI.
	 */
	private static ResourceBundle bundle;

	static {
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
		return  msg;
	
	}
	
}
