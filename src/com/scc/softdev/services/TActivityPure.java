
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tActivityPure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tActivityPure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="ActivityTypeID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ProjectID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ParentActID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CloseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Class" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstimatedTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="LastActDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="OwnerID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="SubOwnerID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="PerformerID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Called" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Urgency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserField" type="{http://services.softdev.scc.com}userField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RelatedEntity" type="{http://services.softdev.scc.com}tRelatedEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tActivityPure", propOrder = {
    "activityTypeID",
    "projectID",
    "parentActID",
    "startDate",
    "endDate",
    "dueDate",
    "closeDate",
    "clazz",
    "estimatedTime",
    "lastActDate",
    "employeeID",
    "ownerID",
    "subOwnerID",
    "performerID",
    "called",
    "urgency",
    "shortDescription",
    "description",
    "userField",
    "relatedEntity"
})
@XmlSeeAlso({
    TActivity.class
})
public class TActivityPure
    extends TObject
{

    @XmlElement(name = "ActivityTypeID")
    protected Long activityTypeID;
    @XmlElement(name = "ProjectID")
    protected Long projectID;
    @XmlElement(name = "ParentActID")
    protected Long parentActID;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "DueDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "CloseDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar closeDate;
    @XmlElement(name = "Class")
    protected String clazz;
    @XmlElement(name = "EstimatedTime")
    protected Long estimatedTime;
    @XmlElement(name = "LastActDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastActDate;
    @XmlElement(name = "EmployeeID")
    protected Long employeeID;
    @XmlElement(name = "OwnerID")
    protected Long ownerID;
    @XmlElement(name = "SubOwnerID")
    protected Long subOwnerID;
    @XmlElement(name = "PerformerID")
    protected Long performerID;
    @XmlElement(name = "Called")
    protected String called;
    @XmlElement(name = "Urgency")
    protected String urgency;
    @XmlElement(name = "ShortDescription")
    protected String shortDescription;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "UserField", nillable = true)
    protected List<UserField> userField;
    @XmlElement(name = "RelatedEntity", nillable = true)
    protected List<TRelatedEntity> relatedEntity;

    /**
     * Gets the value of the activityTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActivityTypeID() {
        return activityTypeID;
    }

    /**
     * Sets the value of the activityTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActivityTypeID(Long value) {
        this.activityTypeID = value;
    }

    /**
     * Gets the value of the projectID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProjectID() {
        return projectID;
    }

    /**
     * Sets the value of the projectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProjectID(Long value) {
        this.projectID = value;
    }

    /**
     * Gets the value of the parentActID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentActID() {
        return parentActID;
    }

    /**
     * Sets the value of the parentActID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentActID(Long value) {
        this.parentActID = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the closeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCloseDate() {
        return closeDate;
    }

    /**
     * Sets the value of the closeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCloseDate(XMLGregorianCalendar value) {
        this.closeDate = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the estimatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Sets the value of the estimatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedTime(Long value) {
        this.estimatedTime = value;
    }

    /**
     * Gets the value of the lastActDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastActDate() {
        return lastActDate;
    }

    /**
     * Sets the value of the lastActDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastActDate(XMLGregorianCalendar value) {
        this.lastActDate = value;
    }

    /**
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEmployeeID(Long value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the ownerID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwnerID() {
        return ownerID;
    }

    /**
     * Sets the value of the ownerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwnerID(Long value) {
        this.ownerID = value;
    }

    /**
     * Gets the value of the subOwnerID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubOwnerID() {
        return subOwnerID;
    }

    /**
     * Sets the value of the subOwnerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubOwnerID(Long value) {
        this.subOwnerID = value;
    }

    /**
     * Gets the value of the performerID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPerformerID() {
        return performerID;
    }

    /**
     * Sets the value of the performerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPerformerID(Long value) {
        this.performerID = value;
    }

    /**
     * Gets the value of the called property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalled() {
        return called;
    }

    /**
     * Sets the value of the called property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalled(String value) {
        this.called = value;
    }

    /**
     * Gets the value of the urgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Sets the value of the urgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgency(String value) {
        this.urgency = value;
    }

    /**
     * Gets the value of the shortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the value of the shortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the userField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserField }
     * 
     * 
     */
    public List<UserField> getUserField() {
        if (userField == null) {
            userField = new ArrayList<UserField>();
        }
        return this.userField;
    }

    /**
     * Gets the value of the relatedEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRelatedEntity }
     * 
     * 
     */
    public List<TRelatedEntity> getRelatedEntity() {
        if (relatedEntity == null) {
            relatedEntity = new ArrayList<TRelatedEntity>();
        }
        return this.relatedEntity;
    }

}
