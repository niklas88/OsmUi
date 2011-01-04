/**
 * 
 */
package de.osmui.ui;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

/**
 * This is a testclass to find all available event types because there is no documentation
 * 
 * @author Niklas Schnelle
 *
 */
public class DebugListener implements mxIEventListener {

	/* (non-Javadoc)
	 * @see com.mxgraph.util.mxEventSource.mxIEventListener#invoke(java.lang.Object, com.mxgraph.util.mxEventObject)
	 */
	@Override
	public void invoke(Object sender, mxEventObject evt) {
		System.out.println(sender);
		System.out.println(evt.getName());

	}

}
