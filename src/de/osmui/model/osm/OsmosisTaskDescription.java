//
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

// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.20 at 06:00:42 PM CET 
//


package de.osmui.model.osm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://uni-stuttgart.de/iev/osmosisTaskDescription}taskGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="formatVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="osmosisVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @see OsmosisTaskDescroptionTest
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "taskGroup"
})
@XmlRootElement(name = "osmosisTaskDescription")
public class OsmosisTaskDescription {

    protected List<TTaskGroup> taskGroup;
    @XmlAttribute(required = true)
    protected String formatVersion;
    @XmlAttribute(required = true)
    protected String osmosisVersion;

    /**
     * Gets the value of the taskGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTaskGroup }
     * 
     * 
     */
    public List<TTaskGroup> getTaskGroup() {
        if (taskGroup == null) {
            taskGroup = new ArrayList<TTaskGroup>();
        }
        return this.taskGroup;
    }

    /**
     * Gets the value of the formatVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatVersion() {
        return formatVersion;
    }

    /**
     * Sets the value of the formatVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatVersion(String value) {
        this.formatVersion = value;
    }

    /**
     * Gets the value of the osmosisVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsmosisVersion() {
        return osmosisVersion;
    }

    /**
     * Sets the value of the osmosisVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsmosisVersion(String value) {
        this.osmosisVersion = value;
    }

}
