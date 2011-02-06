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

/**
 * This parameter is used for the filter mode in the tag-filter which has a special syntax
 * 
 * @author Niklas Schnelle
 *
 */
public class FilterModeParameter extends EnumParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2808225292212314926L;

	private TagFilterParameter filter;
	
	public FilterModeParameter(TParameter desc, String value, AbstractTask newTask) {
		super(desc, value);
		Map<String, AbstractParameter> pMap = newTask.getParameters();
		TParameter tParam = new TParameter();
		tParam.setDefaultValue("");
		tParam.setName("tagfilter");
		filter = new TagFilterParameter(tParam, tParam.getDefaultValue());
		pMap.put(tParam.getName(), filter);
	}
	
	@Override
	public String getCommandlineForm(){
		return this.getValue()+" \""+filter.getValue()+"\"";
	}

}
