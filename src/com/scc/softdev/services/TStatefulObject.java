
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tStatefulObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tStatefulObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="State" type="{http://services.softdev.scc.com}objectState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tStatefulObject", propOrder = {
    "state"
})
@XmlSeeAlso({
    TTransition.class
})
public abstract class TStatefulObject
    extends TObject
{

    @XmlElement(name = "State")
    @XmlSchemaType(name = "string")
    protected ObjectState state;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectState }
     *     
     */
    public ObjectState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectState }
     *     
     */
    public void setState(ObjectState value) {
        this.state = value;
    }

}
