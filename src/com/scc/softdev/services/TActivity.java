
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tActivity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tActivityPure">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ResPercent" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Timesheethrs" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CurrentRemainingHrs" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EnteredID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Attachment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShortID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="UFI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tActivity", propOrder = {
    "status",
    "productID",
    "createdDate",
    "resPercent",
    "timesheethrs",
    "currentRemainingHrs",
    "enteredID",
    "attachment",
    "link",
    "shortID",
    "ufi"
})
public class TActivity
    extends TActivityPure
{

    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "ProductID")
    protected Long productID;
    @XmlElement(name = "CreatedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "ResPercent")
    protected Long resPercent;
    @XmlElement(name = "Timesheethrs")
    protected Long timesheethrs;
    @XmlElement(name = "CurrentRemainingHrs")
    protected Long currentRemainingHrs;
    @XmlElement(name = "EnteredID")
    protected Long enteredID;
    @XmlElement(name = "Attachment")
    protected String attachment;
    @XmlElement(name = "Link")
    protected String link;
    @XmlElement(name = "ShortID")
    protected Long shortID;
    @XmlElement(name = "UFI")
    protected String ufi;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductID(Long value) {
        this.productID = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the resPercent property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResPercent() {
        return resPercent;
    }

    /**
     * Sets the value of the resPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResPercent(Long value) {
        this.resPercent = value;
    }

    /**
     * Gets the value of the timesheethrs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimesheethrs() {
        return timesheethrs;
    }

    /**
     * Sets the value of the timesheethrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimesheethrs(Long value) {
        this.timesheethrs = value;
    }

    /**
     * Gets the value of the currentRemainingHrs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCurrentRemainingHrs() {
        return currentRemainingHrs;
    }

    /**
     * Sets the value of the currentRemainingHrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCurrentRemainingHrs(Long value) {
        this.currentRemainingHrs = value;
    }

    /**
     * Gets the value of the enteredID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnteredID() {
        return enteredID;
    }

    /**
     * Sets the value of the enteredID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnteredID(Long value) {
        this.enteredID = value;
    }

    /**
     * Gets the value of the attachment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Sets the value of the attachment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachment(String value) {
        this.attachment = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the shortID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getShortID() {
        return shortID;
    }

    /**
     * Sets the value of the shortID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setShortID(Long value) {
        this.shortID = value;
    }

    /**
     * Gets the value of the ufi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUFI() {
        return ufi;
    }

    /**
     * Sets the value of the ufi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUFI(String value) {
        this.ufi = value;
    }

}
