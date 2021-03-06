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
package de.osmui.util;

import java.text.ParseException;

import de.osmui.i18n.I18N;

/**
 * This class is used to split an osmosis command line into tokens, while
 * dealing with shell escapes
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 */
public class CommandlineSplitter {
	private enum State {
		normal, inQuote, afterEscape, dead
	};

	private String line;
	private StringBuilder sb;
	private char[] quoteChars;
	private char lastQuoteChar;
	private char escapeChar;
	private char currChar;
	private int pos;

	private State state;

	public CommandlineSplitter(String l, char[] qC, char eC) {
		line = l;
		sb = new StringBuilder();
		pos = 0;
		state = State.normal;
		quoteChars = qC;
		escapeChar = eC;
		lastQuoteChar = 0;
		readOn();
	}

	/**
	 * Gets whether there is a new token available
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return pos < line.length() || sb.length() > 0;
	}

	/**
	 * Checks whether the given char is in the quote array
	 * @param c
	 * @return
	 */
	private boolean isQuoteChar(char c) {
		for (char ci : quoteChars) {
			if (ci == c) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Reads the next token 
	 * @return true if all was ok, false if there was a parse error e.g
	 * 			no matching quote
	 */
	private boolean readOn() {
		sb.delete(0, sb.length());
		while (pos < line.length()) {
			currChar = line.charAt(pos);
			switch (state) {
			case normal: {
				if (Character.isWhitespace(currChar)) {
					if (sb.length() > 0) {
						pos++;
						return true;
					}
				} else if (currChar == escapeChar) {
					state = State.afterEscape;
				} else if (isQuoteChar(currChar)) {
					lastQuoteChar = currChar;
					state = State.inQuote;
				} else {
					sb.append(currChar);
				}
			}
				break;
			case inQuote: {
				if (isQuoteChar(currChar) && currChar == lastQuoteChar) {
					lastQuoteChar = 0;
					state = State.normal;
				} else {
					sb.append(currChar);
				}
			}
				break;
			case afterEscape: {
				// At line breaks args are split even when
				// escaped
				if (currChar == '\n') {
					state = State.normal;
					if (sb.length() > 0) {
						pos++;
						return true;
					}
				} else {
					sb.append(currChar);
					state = State.normal;
				}

			}
				break;
			case dead:
				return false;
			}

			pos++;
		}
		return (state != State.normal) ? false : true;
	}
	/**
	 * Gets the next token
	 * @return
	 * @throws ParseException
	 */
	public String next() throws ParseException {
		String ret;
		ret = sb.toString();
		if (!readOn()) {
			throw new ParseException(I18N.getString(
					"CommandlineSplitter.Error", lastQuoteChar), pos);
		}
		return ret;
	}

}
