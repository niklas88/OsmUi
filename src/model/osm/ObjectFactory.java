//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.06 at 04:54:11 PM CET 
//


package model.osm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.osm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Parameter_QNAME = new QName("http://uni-stuttgart.de/iev/osmosisTaskDescription", "parameter");
    private final static QName _TaskGroup_QNAME = new QName("http://uni-stuttgart.de/iev/osmosisTaskDescription", "taskGroup");
    private final static QName _Task_QNAME = new QName("http://uni-stuttgart.de/iev/osmosisTaskDescription", "task");
    private final static QName _InputPipe_QNAME = new QName("http://uni-stuttgart.de/iev/osmosisTaskDescription", "inputPipe");
    private final static QName _OutputPipe_QNAME = new QName("http://uni-stuttgart.de/iev/osmosisTaskDescription", "outputPipe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.osm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OsmosisTaskDescription }
     * 
     */
    public OsmosisTaskDescription createOsmosisTaskDescription() {
        return new OsmosisTaskDescription();
    }

    /**
     * Create an instance of {@link TParameter }
     * 
     */
    public TParameter createTParameter() {
        return new TParameter();
    }

    /**
     * Create an instance of {@link TTaskGroup }
     * 
     */
    public TTaskGroup createTTaskGroup() {
        return new TTaskGroup();
    }

    /**
     * Create an instance of {@link TPipe }
     * 
     */
    public TPipe createTPipe() {
        return new TPipe();
    }

    /**
     * Create an instance of {@link TTask }
     * 
     */
    public TTask createTTask() {
        return new TTask();
    }

    /**
     * Create an instance of {@link TEnumValue }
     * 
     */
    public TEnumValue createTEnumValue() {
        return new TEnumValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uni-stuttgart.de/iev/osmosisTaskDescription", name = "parameter")
    public JAXBElement<TParameter> createParameter(TParameter value) {
        return new JAXBElement<TParameter>(_Parameter_QNAME, TParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTaskGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uni-stuttgart.de/iev/osmosisTaskDescription", name = "taskGroup")
    public JAXBElement<TTaskGroup> createTaskGroup(TTaskGroup value) {
        return new JAXBElement<TTaskGroup>(_TaskGroup_QNAME, TTaskGroup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uni-stuttgart.de/iev/osmosisTaskDescription", name = "task")
    public JAXBElement<TTask> createTask(TTask value) {
        return new JAXBElement<TTask>(_Task_QNAME, TTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TPipe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uni-stuttgart.de/iev/osmosisTaskDescription", name = "inputPipe")
    public JAXBElement<TPipe> createInputPipe(TPipe value) {
        return new JAXBElement<TPipe>(_InputPipe_QNAME, TPipe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TPipe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uni-stuttgart.de/iev/osmosisTaskDescription", name = "outputPipe")
    public JAXBElement<TPipe> createOutputPipe(TPipe value) {
        return new JAXBElement<TPipe>(_OutputPipe_QNAME, TPipe.class, null, value);
    }

}