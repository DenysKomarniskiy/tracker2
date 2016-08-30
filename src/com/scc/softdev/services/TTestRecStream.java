
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tTestRecStream complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTestRecStream">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EntityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="SiteId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RecId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="StreamId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IsLocked" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LockedById" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecParentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RecOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Child" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTestRecStream", propOrder = {
    "entityId",
    "siteId",
    "recId",
    "streamId",
    "isLocked",
    "lockedById",
    "visible",
    "recParentId",
    "recOrder",
    "subject",
    "deleted",
    "child",
    "productId"
})
public class TTestRecStream {

    @XmlElement(name = "EntityId")
    protected Long entityId;
    @XmlElement(name = "SiteId")
    protected Long siteId;
    @XmlElement(name = "RecId")
    protected Long recId;
    @XmlElement(name = "StreamId")
    protected Long streamId;
    @XmlElement(name = "IsLocked")
    protected String isLocked;
    @XmlElement(name = "LockedById")
    protected Long lockedById;
    @XmlElement(name = "Visible")
    protected String visible;
    @XmlElement(name = "RecParentId")
    protected Long recParentId;
    @XmlElement(name = "RecOrder")
    protected Long recOrder;
    @XmlElement(name = "Subject")
    protected Long subject;
    @XmlElement(name = "Deleted")
    protected String deleted;
    @XmlElement(name = "Child")
    protected String child;
    @XmlElement(name = "ProductId")
    protected Long productId;

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntityId(Long value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the siteId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * Sets the value of the siteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSiteId(Long value) {
        this.siteId = value;
    }

    /**
     * Gets the value of the recId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * Sets the value of the recId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecId(Long value) {
        this.recId = value;
    }

    /**
     * Gets the value of the streamId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStreamId() {
        return streamId;
    }

    /**
     * Sets the value of the streamId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStreamId(Long value) {
        this.streamId = value;
    }

    /**
     * Gets the value of the isLocked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     * Sets the value of the isLocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLocked(String value) {
        this.isLocked = value;
    }

    /**
     * Gets the value of the lockedById property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLockedById() {
        return lockedById;
    }

    /**
     * Sets the value of the lockedById property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLockedById(Long value) {
        this.lockedById = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisible(String value) {
        this.visible = value;
    }

    /**
     * Gets the value of the recParentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecParentId() {
        return recParentId;
    }

    /**
     * Sets the value of the recParentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecParentId(Long value) {
        this.recParentId = value;
    }

    /**
     * Gets the value of the recOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecOrder() {
        return recOrder;
    }

    /**
     * Sets the value of the recOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecOrder(Long value) {
        this.recOrder = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubject(Long value) {
        this.subject = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeleted(String value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the child property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChild() {
        return child;
    }

    /**
     * Sets the value of the child property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChild(String value) {
        this.child = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductId(Long value) {
        this.productId = value;
    }

}
