
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tRelatedEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tRelatedEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RelatedEntityID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RelatedEntityTypeID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RelatedEntityTrackingID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRelatedEntity", propOrder = {
    "relatedEntityID",
    "relatedEntityTypeID",
    "relatedEntityTrackingID"
})
public class TRelatedEntity {

    @XmlElement(name = "RelatedEntityID")
    protected Long relatedEntityID;
    @XmlElement(name = "RelatedEntityTypeID")
    protected Long relatedEntityTypeID;
    @XmlElement(name = "RelatedEntityTrackingID")
    protected Long relatedEntityTrackingID;

    /**
     * Gets the value of the relatedEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRelatedEntityID() {
        return relatedEntityID;
    }

    /**
     * Sets the value of the relatedEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRelatedEntityID(Long value) {
        this.relatedEntityID = value;
    }

    /**
     * Gets the value of the relatedEntityTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRelatedEntityTypeID() {
        return relatedEntityTypeID;
    }

    /**
     * Sets the value of the relatedEntityTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRelatedEntityTypeID(Long value) {
        this.relatedEntityTypeID = value;
    }

    /**
     * Gets the value of the relatedEntityTrackingID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRelatedEntityTrackingID() {
        return relatedEntityTrackingID;
    }

    /**
     * Sets the value of the relatedEntityTrackingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRelatedEntityTrackingID(Long value) {
        this.relatedEntityTrackingID = value;
    }

}
