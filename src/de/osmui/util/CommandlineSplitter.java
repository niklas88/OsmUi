/**
 * 
 */
package de.osmui.util;

import java.text.ParseException;

/**
 * This class is used to split an osmosis command line into tokens, while
 * dealing with shell escapes
 * 
 * @author Niklas Schnelle
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
		return pos < line.length();
	}

	private boolean isQuoteChar(char c) {
		for (char ci : quoteChars) {
			if (ci == c) {
				return true;
			}
		}
		return false;
	}

	private boolean readOn() {
		sb.delete(0, sb.length());
		while (pos < line.length()) {
			currChar = line.charAt(pos);
			switch (state) {
			case normal: {
				if (Character.isWhitespace(currChar)) {
					pos++;
					return true;
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
				// Somehow DOS can escape two at once for line endings,
				// this isn't nice but should work
				if(currChar == '\r' || currChar == '\n'){
					state = State.normal;
					pos++;
					return true;
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
		return true;
	}

	public String next() {//throws ParseException {
		String ret;
		do{
			ret = sb.toString();
			if(!readOn()){
				//throw new ParseException("Error", pos);
			}
		} while (ret.length() == 0);
		return ret;
	}

}
