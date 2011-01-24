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
package de.osmui.model.pipelinemodel.parameters;

import java.util.Map;

import de.osmui.model.osm.TParameter;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.unistuttgart.iev.osm.bboxchooser.Bounds;

/**
 * This class defines a pseudo parameter for the bounding box, it can't be used
 * for normal value setting/getting (overrides this with methods that do
 * nothing/return null) but knows about the parameters defining a bounding-box
 * so that the GUI can create a renderer/editor for it that can deal with them
 * as a group
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *@see BBoxPseudoParameterTest
 *
 */
public class BBoxPseudoParameter extends AbstractParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 60189229466208682L;

	/*
	 * Used to save the Task this param belongs to so we can manage the
	 * parameters making up this pseudo parameter on our own
	 */
	protected AbstractTask parent;

	/*
	 * This pseudo parameter knows of the following parameters that make up this
	 * parameter group that we want to treat as one in some circumstances
	 * (mainly) GUI:
	 * 
	 * left The longitude of the left edge of the box. -180 to 180 -180 right
	 * The longitude of the right edge of the box. -180 to 180 180 top The
	 * latitude of the top edge of the box. -90 to 90 90 bottom The latitude of
	 * the bottom edge of the box. -90 to 90 -90 x1 Slippy map coordinate of the
	 * left edge of the box y1 Slippy map coordinate of the top edge of the box
	 * x2 Slippy map coordinate of the right edge of the box x1 y2 Slippy map
	 * coordinate of the bottom edge of the box y1 zoom Slippy map zoom
	 */
	protected FloatInRangeParameter left;
	protected FloatInRangeParameter right;
	protected FloatInRangeParameter top;
	protected FloatInRangeParameter bottom;
	// TODO: Test whether Slippy map coordinates are in fact ints
	protected IntParameter x1;
	protected IntParameter y1;
	protected IntParameter x2;
	protected IntParameter y2;
	protected IntParameter zoom;

	/**
	 * Constructor
	 * 
	 * @param desc
	 *            the description for this bbox pseudo parameter
	 * @param value
	 *            the value for this pseudo parameter (ignored)
	 * @param parent
	 *            the parent task for this pseudo parameter
	 * @param withXY
	 *            true if ths is a bbox with x0,x2,..,zoom false if only
	 *            top,left,right..
	 */
	public BBoxPseudoParameter(TParameter desc, String value,
			AbstractTask parent, boolean withXY) {
		super(desc, value);
		Map<String, AbstractParameter> pMap = parent.getParameters();
		TParameter tParam = new TParameter();
		tParam.setDefaultValue("-180.0");
		tParam.setName("left");
		left = new FloatInRangeParameter(tParam, tParam.getDefaultValue(),
				-180, 180);

		tParam = new TParameter();
		tParam.setDefaultValue("180.0");
		tParam.setName("right");
		right = new FloatInRangeParameter(tParam, tParam.getDefaultValue(),
				-180, 180);

		tParam = new TParameter();
		tParam.setDefaultValue("90.0");
		tParam.setName("top");
		top = new FloatInRangeParameter(tParam, tParam.getDefaultValue(), -90,
				90);

		tParam = new TParameter();
		tParam.setDefaultValue("-90.0");
		tParam.setName("bottom");
		bottom = new FloatInRangeParameter(tParam, tParam.getDefaultValue(),
				-90, 90);

		pMap.put("left", left);
		pMap.put("right", right);
		pMap.put("top", top);
		pMap.put("bottom", bottom);

		if (withXY) {
			/*
			 * 0 is not the real default but since we can't map x1->y1 as
			 * osmosis does this will keep it from showing up if it isn't
			 * changed
			 */
			tParam = new TParameter();
			tParam.setDefaultValue("0");
			tParam.setName("x1");
			x1 = new IntParameter(tParam, tParam.getDefaultValue());

			tParam = new TParameter();
			tParam.setDefaultValue("0");
			tParam.setName("y1");
			y1 = new IntParameter(tParam, tParam.getDefaultValue());

			tParam = new TParameter();
			tParam.setDefaultValue("0");
			tParam.setName("x2");
			x2 = new IntParameter(tParam, tParam.getDefaultValue());

			tParam = new TParameter();
			tParam.setDefaultValue("0");
			tParam.setName("y2");
			y2 = new IntParameter(tParam, tParam.getDefaultValue());

			tParam = new TParameter();
			tParam.setDefaultValue("12");
			tParam.setName("zoom");
			zoom = new IntParameter(tParam, tParam.getDefaultValue());

			pMap.put("x1", x1);
			pMap.put("y1", y1);
			pMap.put("x2", x2);
			pMap.put("y2", y2);
			pMap.put("zoom", zoom);
		}
	}

	/*
	 * This is a pseudo parameter so return empty string for safeness
	 * 
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return "";
	}

	/*
	 * This is a pseudo parameter return null
	 * 
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return null;/*OsmUi is a user interface for Osmosis
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
	/**
	 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
	 * 
	 * @see EnumParameter
	 * 
	 */
	}

	/*
	 * This is a pseudo param, do nothing
	 * 
	 * @see
	 * de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
	}

	/**
	 * Sets the managed values to the given BoundingBox
	 * 
	 * @param bbox
	 */
	public void setBoundingBox(Bounds bbox) {
		left.setValue(bbox.getMin().lon());
		bottom.setValue(bbox.getMin().lat());
		right.setValue(bbox.getMax().lon());
		top.setValue(bbox.getMax().lat());
	};

	public Bounds getBoundingBox() {
		Bounds bbox = new Bounds(bottom.getValueDouble(),
				left.getValueDouble(), top.getValueDouble(),
				right.getValueDouble());
		return bbox;
	}

}
