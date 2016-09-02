
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEntityLinks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEntityLinks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OutgoungLinks" type="{http://services.softdev.scc.com}tEntityLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IncomingLinks" type="{http://services.softdev.scc.com}tEntityLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StLinks" type="{http://services.softdev.scc.com}tEntityLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TmsLinks" type="{http://services.softdev.scc.com}tEntityLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SystemLinks" type="{http://services.softdev.scc.com}tEntityLink" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEntityLinks", propOrder = {
    "outgoungLinks",
    "incomingLinks",
    "stLinks",
    "tmsLinks",
    "systemLinks"
})
public class TEntityLinks {

    @XmlElement(name = "OutgoungLinks", nillable = true)
    protected List<TEntityLink> outgoungLinks;
    @XmlElement(name = "IncomingLinks", nillable = true)
    protected List<TEntityLink> incomingLinks;
    @XmlElement(name = "StLinks", nillable = true)
    protected List<TEntityLink> stLinks;
    @XmlElement(name = "TmsLinks", nillable = true)
    protected List<TEntityLink> tmsLinks;
    @XmlElement(name = "SystemLinks", nillable = true)
    protected List<TEntityLink> systemLinks;

    /**
     * Gets the value of the outgoungLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outgoungLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutgoungLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEntityLink }
     * 
     * 
     */
    public List<TEntityLink> getOutgoungLinks() {
        if (outgoungLinks == null) {
            outgoungLinks = new ArrayList<TEntityLink>();
        }
        return this.outgoungLinks;
    }

    /**
     * Gets the value of the incomingLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the incomingLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncomingLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEntityLink }
     * 
     * 
     */
    public List<TEntityLink> getIncomingLinks() {
        if (incomingLinks == null) {
            incomingLinks = new ArrayList<TEntityLink>();
        }
        return this.incomingLinks;
    }

    /**
     * Gets the value of the stLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEntityLink }
     * 
     * 
     */
    public List<TEntityLink> getStLinks() {
        if (stLinks == null) {
            stLinks = new ArrayList<TEntityLink>();
        }
        return this.stLinks;
    }

    /**
     * Gets the value of the tmsLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tmsLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTmsLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEntityLink }
     * 
     * 
     */
    public List<TEntityLink> getTmsLinks() {
        if (tmsLinks == null) {
            tmsLinks = new ArrayList<TEntityLink>();
        }
        return this.tmsLinks;
    }

    /**
     * Gets the value of the systemLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the systemLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSystemLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEntityLink }
     * 
     * 
     */
    public List<TEntityLink> getSystemLinks() {
        if (systemLinks == null) {
            systemLinks = new ArrayList<TEntityLink>();
        }
        return this.systemLinks;
    }

}
