
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tEntityTracking complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEntityTracking">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EntType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RecordID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ProcessID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ActionOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NextAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncomingTransitionID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEntityTracking", propOrder = {
    "productID",
    "entType",
    "recordID",
    "processID",
    "actionOrder",
    "userID",
    "date",
    "status",
    "comment",
    "nextAction",
    "incomingTransitionID"
})
public class TEntityTracking
    extends TObject
{

    @XmlElement(name = "ProductID")
    protected Long productID;
    @XmlElement(name = "EntType")
    protected Long entType;
    @XmlElement(name = "RecordID")
    protected Long recordID;
    @XmlElement(name = "ProcessID")
    protected Long processID;
    @XmlElement(name = "ActionOrder")
    protected Long actionOrder;
    @XmlElement(name = "UserID")
    protected Long userID;
    @XmlElement(name = "Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Comment")
    protected String comment;
    @XmlElement(name = "NextAction")
    protected String nextAction;
    @XmlElement(name = "IncomingTransitionID")
    protected Long incomingTransitionID;

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
     * Gets the value of the entType property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntType() {
        return entType;
    }

    /**
     * Sets the value of the entType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntType(Long value) {
        this.entType = value;
    }

    /**
     * Gets the value of the recordID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecordID() {
        return recordID;
    }

    /**
     * Sets the value of the recordID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecordID(Long value) {
        this.recordID = value;
    }

    /**
     * Gets the value of the processID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProcessID() {
        return processID;
    }

    /**
     * Sets the value of the processID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProcessID(Long value) {
        this.processID = value;
    }

    /**
     * Gets the value of the actionOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActionOrder() {
        return actionOrder;
    }

    /**
     * Sets the value of the actionOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActionOrder(Long value) {
        this.actionOrder = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserID(Long value) {
        this.userID = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

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
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the nextAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextAction() {
        return nextAction;
    }

    /**
     * Sets the value of the nextAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextAction(String value) {
        this.nextAction = value;
    }

    /**
     * Gets the value of the incomingTransitionID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIncomingTransitionID() {
        return incomingTransitionID;
    }

    /**
     * Sets the value of the incomingTransitionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIncomingTransitionID(Long value) {
        this.incomingTransitionID = value;
    }

}
