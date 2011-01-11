package de.osmui.i18n;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.JMenu;

/**
* @see I18N
*/
public class I18NTest {
	@Test public void get (){
		I18N inter = new I18N();
		Object values = null;
		JMenu fileMenu = new JMenu(inter.getString("Menu.file", values));
		assertEquals("getString: ", fileMenu.getActionCommand(), "Datei");
		};
}
